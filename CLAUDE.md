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

**No standalone JDK 8 (with `javac`) may be installed** — only a JDK 8 *runtime* (`jre1.8.0_...`, no compiler) plus newer JDKs (17/24) whose `javac` refuses to target anything as old as 1.7/1.8 by default. The working combination confirmed on this machine: compile with a newer JDK's `javac --release 8` (produces JavaSE-8-compatible bytecode; warns "obsolete" but works), then **run** with the actual JRE 8 binary. Don't assume `java`/`javac` on `PATH` resolve to the same install — check `where java`/`where javac` first, since this machine has several JDKs and MSYS64's own bundled JDK competing on `PATH`.

Running two `SystemJRunner` processes for the same `.xml` at once (e.g. a stray leftover from a previous test alongside a fresh one) fails loudly with `java.net.BindException: Address already in use` on the process that loses the race for the socket port — if you see that, check for and kill leftover `java.exe` processes (`Get-CimInstance Win32_Process -Filter "Name='java.exe'"`, filter out any IDE-owned language-server ones) before relaunching, rather than assuming the code is broken.

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

## `abort(cond){...}` entry-precondition pitfall — a same-domain issue, distinct from the socket race above

Unrelated to the cross-domain socket race above (this happens purely within one clock domain's own reaction, no network involved), but easy to conflate with it and, in practice, more common: `abort(cond){...}` only runs its body for as long as `cond` stays false. If `cond` can *already* be true the instant the block is entered, the body runs for **exactly one tick** and is torn down immediately — this is `abort` behaving correctly (it's documented to preempt once its condition holds, checked from entry), not a bug, but it silently defeats whatever the body was meant to do. Three confirmed real occurrences of this, all found by testing rather than by reading the code (reasoning about it in the abstract is unreliable — verify):

1. **A manual-trigger latch** (fault-tolerance IP work): `await(overfillM); abort(!bottleAtPos2){ sustain overfilling; }` looked reasonable but silently failed whenever the GUI's trigger signal (`overfillM`) fired *before* the bound condition's positive case (`bottleAtPos2`) ever became true — exactly the realistic order a GUI button produces (arm the fault, then start the fill). Fix used here: don't fight the ordering in SystemJ at all — latch it in a plain Java `volatile` field instead (see `run.FillerFaultState`, `run.FillerRecipe`), which has no entry precondition to get wrong.
2. **`fillerPlant.sysj`'s `levelAtTarget` "widening" thread** (turns a one-tick crossing edge into a reliably-delivered hold, per the socket-race section above): originally `await(levelAtTargetPulse); abort(doseTargetMl){ sustain levelAtTarget; }`, on the assumption `doseTargetMl` would be absent between dosing phases. It never is — the controller re-emits it continuously with no gap — so this abort condition was **always** already true at entry, and the hold only ever lasted one tick regardless. It survived on socket-timing luck for a while; adding more traffic on the same connection (a new signal) tipped that luck the other way and made bottles stop dosing partway. Fixed with a flat time-bounded hold instead (200ms, no `abort` at all) — safe because of fix #3 below.
3. **`fillerController.sysj`'s repeated `abort(levelAtTarget){...}` blocks**, one per dosing phase and one per bottle: entering a *fresh* `abort(levelAtTarget)` for the next phase without confirming the previous phase's occurrence of that signal had gone absent meant the next phase could see it still true at entry and die after one tick — skipping that phase's dosing almost entirely (visible as a bottle stopping at roughly half its target volume). Fixed with an explicit `await(!levelAtTarget);` immediately before each `abort(levelAtTarget){...}`, so the controller *waits* for the previous occurrence to actually clear rather than racing it — this is what makes fix #2's flat time-bounded hold safe, regardless of how long it runs.

**The general rule**: before writing `abort(cond){...}` — especially a *repeated* one, entered more than once with the same `cond` across phases/iterations/bottles — either (a) prove `cond` is guaranteed false at every entry (e.g. by preceding it with `await(!cond)`), or (b) don't use `abort` on that condition at all; use a flat time-bounded hold, or latch cross-tick state in a plain Java field instead of a signal.

## `await(cond)` isn't edge-triggered — re-arming a loop on a still-true condition spams

A cousin of the `abort` pitfall above, same root cause, different symptom: `await(cond)` just blocks until `cond` holds *at that check* — it doesn't care whether this is the first time or the tenth. If a `loop { await(cond); ...bounded action...; }` re-enters `await(cond)` while `cond` is *still* true (e.g. a persistent fault signal that stays present until explicitly cleared), the loop doesn't wait for a fresh occurrence — it re-fires immediately, over and over, for as long as `cond` stays true. Confirmed directly: the fault-escalation thread in `fillerController.sysj` (`loop { await(stationFault); ...200ms widened hold of fillFaultOut...; }`) reprinted and re-escalated every ~200ms for the entire duration of a fault, since `stationFault` is deliberately held present the whole time (needed elsewhere for the dosing thread's `abort`). Fixed by adding `await(!stationFault);` at the end of the loop body, so it only re-arms once the condition has actually gone false and come back — the same "confirm it cleared before reacting to it again" principle as the `abort` pitfall, just applied to `await`. Watch for this anywhere a loop reacts to a signal that's designed to stay present for a while (a persistent fault/status flag, not a momentary pulse) — this is exactly the shape a Lid Placement/Capper retry loop watching a fault signal would have too.

## Don't count reactions across clock domains as a proxy for elapsed time

A clock domain's reaction rate is its own — nothing paces it to match any other domain's (see "unthrottled full CPU speed" above), so **counting one domain's own reactions to measure how long something is taking in a different domain does not work**. Confirmed directly: an early stall-detection design in `fillerController.sysj` counted `FillerControllerCD`'s own reactions while a valve was held open, on the assumption a legitimate fill takes a bounded, predictable reaction count. `FillerControllerCD` and `FillerPlantCD` are separate processes ticking independently; the controller's own idle-spin rate while waiting has no relationship to how many reactions the plant needs to actually dose, and the reaction-counted version false-tripped on a completely normal fill almost instantly. Fixed by switching to `System.currentTimeMillis()`-based wall-clock timing, the only measure that means the same thing regardless of either domain's tick speed. This applies to *any* timeout/threshold that spans two domains, not just this one case — a reaction count is only meaningful as a proxy for elapsed time within the single domain that's actually doing the incrementing (e.g. `fillerPlant.sysj`'s own `fillLevel += 1` per reaction, which is fine, since it's the plant measuring the plant's own progress).

## Manual GUI-triggered actions: does the GUI live in the same JVM as what it's controlling?

`FillerPlant`, `Plant`, and `Pos` each spawn their GUI as a plain Java `Thread` (`new Thread(new FillerGUI()).start();` etc.) **inside their own clock-domain process**, not as a separate JVM. That means a GUI button controlling state that only that same process needs to see (e.g. `run.FillerFaultState`, `run.FillerRecipe`) can just call the Java method directly from the button's `ActionListener` — no `SimpleClient`/iSignal/XML socket wiring needed at all, since there's no other process to reach and no reactive `.sysj` code needs to *observe* the click as a signal (see `Clear Fault` in `FillerPanel.java`). Reach for the signal/socket path (`SignalClient`/`SignalLevelClient` + an `iSignal` in the `.xml`, matching `overfillM`/`stallM`/`bottleAtPos2`) only when either (a) the click needs to reach a signal in a *different* clock-domain process, or (b) the reactive `.sysj` code in the *same* process genuinely needs to see it as a signal mid-reaction (e.g. to gate it with `present(...)` alongside other signals in that tick) rather than just read a plain field. Don't reach for the signal path by default out of habit/consistency with existing buttons — check which case actually applies first.

## Filling Unit fault-tolerance architecture

The full detect → react → escalate pipeline, spread across `fillerController.sysj`, `fillerPlant.sysj`, and `coordinator.sysj` — read this before touching any of it, and use it as the template for Lid Placement/Capper fault detection later rather than re-deriving the pattern:

1. Two detection threads in `fillerController.sysj` (overfill: plant's reported level vs. the live dose target; stall: wall-clock timeout on a valve held open, per the reaction-counting pitfall above) call `FillerFaultState.raiseStationFault()` when they trigger.
2. A bridging thread turns that Java flag into the `stationFault` *signal* — continuously re-emitted every tick it's true, never a pulse, so nothing that `abort`s on it has an entry-precondition risk — and latches the GUI's `clearFaultM` (a cross-domain signal, since Clear Fault lives in a different process) into clearing it.
3. The main dosing thread's `abort(levelAtTarget || stationFault || !bottleAtPos2)` distinguishes three outcomes with a `present()` check afterward: normal completion; station fault (valves closed, waits for `await(!stationFault)` then `await(!bottleAtPos2)` before returning to rotation); and bottle lost (the bottle disappeared mid-phase — not a Filling Unit fault, no lamp, no clear needed, since there's nothing to acknowledge — just closes the valves and falls through). Bottle-lost is currently only reachable via the dev "no bottle" GUI override standing in for the Rotary Table; a real turntable fault causing this is out of this IP's scope (Section 5), but leaving the valve open regardless is a physical-safety gap worth closing either way.
4. **A faulted bottle is deliberately never resumed once cleared** — group decision: the station returns to rotation for the *next* bottle, the faulted one is abandoned (its digital twin records the fault). Don't reintroduce "resume where it left off" semantics for Clear Fault.
5. `fillFaultOut` escalates the fault to the Coordinator, widened the same way as `fillDoneCoordE` (bounded by `!bottleAtPos2`). The Coordinator's `abort(fillDoneE || fillFaultOut)` stops presenting a faulted bottle instead of holding it forever waiting for a `fillDoneE` that will never come, then still waits for `fillReady` (which fires either way once the station resets) before retrying — the attempt isn't counted toward the batch, since there's no redundant line to reroute a faulted bottle to yet.

## Architecture

Current implementation: an original Lab-3-style pick-and-place cell (`Controller`/`Plant`) plus a POS-driven liquid filling line (`Coordinator`/`FillerController`/`FillerPlant`/`Pos`). See `README.md` for the file map and signal-flow summary.

## Docs

`docs/` contains the assignment briefs and the SystemJ language reference (`systemj_bible.pdf`) — consult it for SystemJ semantics (`abort`, `await`, `sustain`, `present`, `pause`, parallel `||`) if unsure how a construct behaves.
