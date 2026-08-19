# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

COMPSYS704 university coursework project (Auckland). Simulates an automatic bottle-cap loading system built with **SystemJ**, a reactive/synchronous programming language for GALS (Globally Asynchronous, Locally Synchronous) embedded systems. Two SystemJ "clock domains" — a `Controller` and a `Plant` — run as separate Java programs and communicate asynchronously; each domain internally executes multiple parallel synchronous threads that step together in lockstep reactions.

Eclipse is the reference IDE (`.project`/`.classpath`/`.settings` and `.launch` files are checked in); there is no Maven/Gradle build.

## Source of truth: edit `.sysj`, not `.java`

`src/Controller.java` and `src/Plant.java` are **generated output** from `sysj/controller.sysj` and `sysj/plant.sysj` via the SystemJ compiler (`sjc`). They are checked into `src/` only because the Eclipse launch configs run compiled Java directly. Never hand-edit the generated Java files — edit the `.sysj` source and regenerate.

The generated files are dense synchronous-execution state machines (per-thread `switch` statements over integer state variables, `active`/`ends`/`tdone` arrays for parallel-thread rendezvous, `setPresent`/`getprestatus`/`sethook`/`gethook` for signal handshaking). Don't try to hand-trace or "clean up" this generated code — reason about behavior from the `.sysj` source instead.

## Regenerating Java from SystemJ sources

Compile (`sjc`) is configured via `BuildAll.launch` (Eclipse launch config), equivalent to:

```
java -cp lib/sjc-2.2-13-g8ab684c-SNAPSHOT.jar com.systemj.compiler.JavaPrettyPrinter -d src --nojavac --silence sysj/*.sysj
```

This regenerates `Controller.java`/`Plant.java` in `src/` from the two `.sysj` files.

## Running

Two independent runnable programs, launched separately (see `RunController.launch` / `RunPlant.launch`), each via the SystemJ runtime (`sjrt`) `SystemJRunner`, equivalent to:

```
java -cp <all jars in lib/, including javafx22/lib/*.jar>:bin com.systemj.SystemJRunner sysj/controller.xml
java -cp <all jars in lib/, including javafx22/lib/*.jar>:bin com.systemj.SystemJRunner sysj/plant.xml
```

`controller.xml` / `plant.xml` are SystemJ module descriptors paired with the compiled classes. Both must be running simultaneously and communicate over TCP (see Ports below); `Plant` also spawns a Swing GUI (`CapLoader`) on a separate thread.

Target JRE per `.classpath` is JavaSE-1.7, but the project also bundles JavaFX 22 (`lib/javafx22/`) — check actual installed JDK before assuming 1.7 compiles cleanly.

## Java-side infrastructure vs. SystemJ config (common misconceptions)

- **`controller.xml`/`plant.xml` are the real wiring**, not `Ports.java`. Each
  `iSignal`/`oSignal` element declares the actual IP/port/class `SystemJRunner`
  uses for that signal's socket (`Class="com.systemj.ipc.SimpleServer"` for
  inputs it listens on, `SimpleClient` for outputs it connects out to).
  `Ports.java` is a hand-written, hand-maintained Java-side convenience file
  (used only by the Swing GUI classes) that mirrors those same ports/signal
  names as constants — there is no generation link between the two, so adding
  a signal means editing both the `.xml` and `Ports.java` yourself, with
  nothing enforcing they stay in sync.
- **The `...E` viz-mirror signals (e.g. `pusherRetractedE`) are not a separate
  clock domain.** They're extra output signals declared inside the *same*
  clock domain (see generated `Plant.java`: `pusherRetracted` and
  `pusherRetractedE` are both fields on `Plant`), wired via an extra `oSignal`
  entry in the same `.xml` file to a different port that a plain Java
  `SignalServer` (not another SystemJ program) listens on. The duplication is
  required because SystemJ's codegen is 1-signal-name-to-1-socket — each
  `Signal` gets exactly one `sethook()` call tied to one XML destination — so
  sending the same event to two destinations needs two separately-emitted
  signals, not a design choice you can avoid.
- **Manual-override signals/threads (the `mode`/`*M` pattern in
  `controller.sysj`) are a Lab 3 feature, not a required pattern for every new
  station.** Only add a manual-override thread + signals + GUI widgets to a
  new station if that station specifically needs operator override; stations
  that only need visualization don't need any of it.
- The Java `Worker`/`SignalClient` wire format already carries values, not
  just booleans (see `SignalRadioClient.java` sending
  `Object[]{true, Integer.valueOf(...)}` for `mode`) — `Worker.run()` just
  never reads index 1. Visualizing an `int` signal needs a small addition to
  `Worker` (read `o[1]`, add a `setIntSignal` hook), not a redesign.

## Architecture

**Controller** (`sysj/controller.sysj`) — the decision logic. Two parallel synchronous threads:
- Mode thread: reads an integer `mode` signal, emits `auto`/`manual`.
- Main thread: an `abort`/`await`/`sustain` state machine implementing the pick-and-place sequence (arm to source → pusher extends/retracts → arm to dest → vacuum grips workpiece → vacuum releases → arm returns), interruptible into a manual-override mode driven by individual `*M` signals (`pusherExtendM`, `vacOnM`, `armSourceM`, `armDestM`).

**Plant** (`sysj/plant.sysj`) — the physical simulation. Several parallel threads modeling: arm position, pusher position, vacuum/gripper (tracks a simulated cap position `capPos`: 0=at magazine, 1=at pickup), cap magazine refill/empty logic (`capcount`, starts at 5), and an "E"-suffixed signal-mirroring block (`pusherRetractedE`, etc.) that forwards state to the visualization layer. Plant also launches the Swing GUI (`run.GUI` → `org.compsys704.CapLoader`) on startup.

**GUI / visualization** (`src/org/compsys704/`) — separate from the SystemJ logic; a Swing `CapLoader` frame with buttons (enable/request/refill), radio buttons (auto/manual mode), and checkboxes (manual signal states), plus a `Canvas` for animating cap position. Talks to the running Controller/Plant processes over plain TCP sockets (`SignalServer`/`SignalClient`/`SignalRadioClient`/`SignalCheckBoxClient`, ports defined in `org.compsys704.Ports`), not through the SystemJ runtime directly — it's an external observer/actuator of named signals like `ControllerCD.mode` or `PlantCD.enable`.

**Signal naming convention**: signals are addressed as `<ClockDomain>.<signalName>` (e.g. `ControllerCD.request`, `PlantCD.refill`) — see `Ports.java` for the full mapping and TCP ports (10000/10001 for command signals, 20000 for viz).

## Docs

`docs/` contains the assignment briefs (`COMPSYS 704_PRJ1_BRIEF.pdf`, lab PDFs) and the SystemJ language reference (`systemj_bible.pdf`) — consult the bible PDF for SystemJ semantics (`abort`, `await`, `sustain`, `present`, `pause`, parallel `||` composition) if unsure how a construct in the `.sysj` files behaves.
