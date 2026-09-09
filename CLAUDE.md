# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

COMPSYS704 university coursework project (Auckland). Simulates an automatic bottling system built with **SystemJ**, a reactive/synchronous programming language for GALS (Globally Asynchronous, Locally Synchronous) embedded systems. Each intelligent machine is a SystemJ "clock domain" — a separate Java process — communicating with others asynchronously over TCP; each domain internally runs multiple parallel synchronous threads that step together in lockstep reactions.

Eclipse is the reference IDE (`.project`/`.classpath`/`.settings` and `.launch` files are checked in); there is no Maven/Gradle build.

## Source of truth: edit `.sysj`, not `.java`

Every `src/*.java` clock-domain file (`Controller.java`, `Plant.java`, `Coordinator.java`, `FillerController.java`, `FillerPlant.java`, `Pos.java`) is **generated output** from the matching `sysj/*.sysj` file via the SystemJ compiler (`sjc`). Checked into `src/` only because the Eclipse launch configs run compiled Java directly. Never hand-edit generated Java — edit the `.sysj` source and regenerate.

Generated files are dense synchronous-execution state machines (per-thread `switch` over integer state variables, `active`/`ends`/`tdone` arrays for parallel-thread rendezvous, `setPresent`/`getprestatus`/`sethook`/`gethook` for signal handshaking). Don't hand-trace or "clean up" this code — reason about behavior from the `.sysj` source instead.

## Regenerating Java from SystemJ sources

```
java -cp lib/sjc-2.2-13-g8ab684c-SNAPSHOT.jar com.systemj.compiler.JavaPrettyPrinter -d src --nojavac --silence sysj/*.sysj
```
(needs ALL jars in `lib/*.jar` on the classpath, not just the sjc jar, or it fails with `NoClassDefFoundError`). Then compile normally (`javac`, classpath = all of `lib/*.jar` + `lib/javafx22/lib/*.jar`).

Note: regenerating ALL `.sysj` files together shifts internal state-variable/thread numbering in files you didn't touch (a global naming counter across the whole compile batch) — purely cosmetic, but check `git diff` before committing to avoid noise in unrelated files.

## Running

Each clock domain is an independent runnable process via the SystemJ runtime (`sjrt`) `SystemJRunner`:
```
java -cp <all jars in lib/, including javafx22/lib/*.jar>:bin com.systemj.SystemJRunner sysj/<name>.xml
```
`<name>.xml` is the SystemJ module descriptor pairing the compiled class with its socket wiring. All domains that talk to each other must be running simultaneously. `Plant`, `FillerPlant`, and `Pos` also spawn a Swing GUI on startup.

Target JRE per `.classpath` is JavaSE-1.7, but the project also bundles JavaFX 22 (`lib/javafx22/`) — check the actual installed JDK (JDK 8 has worked in practice) before assuming 1.7 compiles cleanly.

## Java-side infrastructure vs. SystemJ config (common misconceptions)

- **`<name>.xml` is the real wiring**, not `Ports.java`. Each `iSignal`/`oSignal` element declares the actual IP/port/class `SystemJRunner` uses for that signal's socket (`SimpleServer` for inputs it listens on, `SimpleClient` for outputs it connects out to). `Ports.java` is a hand-maintained Java-side convenience file (used by the Swing GUI/Java glue code) mirroring those same ports/signal names as constants — no generation link between the two, so adding a signal means editing both the `.xml` and `Ports.java` yourself.
- **`...E`-suffixed viz-mirror signals** (e.g. `fillDoneE`, `pusherRetractedE`) are not a separate clock domain — extra output signals in the *same* domain, wired via an extra `oSignal` to a port a plain Java `SignalServer` listens on. Needed because SystemJ codegen is 1-signal-name-to-1-socket, so sending one event to two destinations needs two separately-emitted signals.
- **Manual-override signals (`mode`/`*M` pattern in `controller.sysj`)** are a feature of that one station, not a required pattern for every new station.
- The Java `Worker`/`SignalClient` wire format carries values, not just booleans (`Object[]{true, value}}`) — `Worker.run()` just needs to read index 1 and add a typed hook to visualize an `int`/value signal.

## Cross-clock-domain signal reliability — CRITICAL, read before adding any new signal

SystemJ clock domains tick at **unthrottled full CPU speed** (`DefaultTickFinisher.finishTick()` is a no-op — confirmed by decompiling `lib/sjrt-*-desktop.jar`, `com.systemj.ipc.*`, with CFR). The receive side (`GenericSignalReceiver.buffer`) is a **single slot, not a queue**, and the per-connection reader thread has no pacing. Net effect: **a plain `emit X;` held for exactly one tick is a race** — the sender can stream `{TRUE}` then `{FALSE}` before the receiving clock domain's own tick loop ever polls in between, silently dropping the signal. This caused real, hard-to-diagnose stalls (see `coordinator.sysj`, `fillerController.sysj`, `fillerPlant.sysj`, `pos.sysj`).

`SimpleClient` is edge-triggered/deduplicated (only sends when presence or value actually changes), so holding a signal present for longer costs at most one extra packet — sustaining is cheap. Use one of:

- **Time-bounded hold** — for handshake pulses reused with real gaps between occurrences (once per order, etc.):
  ```
  long t0 = System.currentTimeMillis();
  while (System.currentTimeMillis() - t0 < 50) { emit X; pause; }
  ```
- **Hybrid ack-or-timeout** — for signals reused rapidly in a tight loop (once per bottle/iteration), where a flat hold can go stale before the *next* reuse's check runs and falsely short-circuit it:
  ```
  abort (naturalAckCondition) {
      long t0 = System.currentTimeMillis();
      while (System.currentTimeMillis() - t0 < 200) { emit X; pause; }
  }
  ```
  The ack condition should be something the *receiver's own reaction* causes to become true/false quickly (e.g. the sender stops/starts another signal once it's seen X) — the timeout is only a safety net for the case the ack never comes (see `fillReady`/`fillDoneCoordE` in `fillerController.sysj`).
- **Duration-matched sustain** — if the signal naturally coincides with an already-long-running phase, just fold it into that phase's own sustain loop instead of inventing a timer.

Never use a bare single-tick `emit` for anything that crosses a clock-domain boundary.

## Architecture

Current implementation: an original Lab-3-style pick-and-place cell (`Controller`/`Plant`) plus a POS-driven liquid filling line (`Coordinator`/`FillerController`/`FillerPlant`/`Pos`). See `README.md` for the file map and signal-flow summary.

## Docs

`docs/` contains the assignment briefs and the SystemJ language reference (`systemj_bible.pdf`) — consult it for SystemJ semantics (`abort`, `await`, `sustain`, `present`, `pause`, parallel `||`) if unsure how a construct behaves.
