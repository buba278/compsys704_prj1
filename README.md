# Automatic Bottling System

COMPSYS704 project. Each intelligent machine is an independent SystemJ clock domain (paired `<name>.sysj` decision logic + `<name>Plant.sysj` physical simulation, same pattern as the Lab 3 lid loader), each a separate Java process communicating over TCP.

## Implemented so far

```
sysj/
  controller.sysj / plant.sysj              # Lab 3 cap loader (pick-and-place cell)
  coordinator.sysj / coordinator.xml        # recipe + batch/bottle-count orchestration, POS-facing
  fillerController.sysj / fillerController.xml   # dual-phase liquid dosing logic, pos 2
  fillerPlant.sysj / fillerPlant.xml        # fill-level simulation for the filler
  pos.sysj / pos.xml                        # accepts orders, hands off to Coordinator, reports completion

src/
  Controller.java, Plant.java                       # generated from controller.sysj / plant.sysj
  Coordinator.java                                  # generated from coordinator.sysj
  FillerController.java, FillerPlant.java           # generated from fillerController.sysj / fillerPlant.sysj
  Pos.java                                          # generated from pos.sysj
  machines/pos/     Order.java, OrderQueue.java, PosPanel.java, PosState.java, PosVizWorker.java
  machines/filler/  FillerCanvas.java, FillerPanel.java, FillerState.java, FillerVizWorker.java
  org/compsys704/   Ports.java, SignalServer.java, SignalClient.java, SignalRadioClient.java,
                    SignalCheckBoxClient.java, SignalLevelClient.java, Canvas.java, Worker.java,
                    States.java, CapLoader.java, LoaderVizWorker.java
  run/              GUI.java, FillerGUI.java, PosGUI.java, FillerRecipe.java
```

Remaining stations from the assignment brief (loader, conveyor, rotary table, cap placer, capper, unloader, labeler) are not yet built.

## Coordination model

- **Local signals** (station to station, no coordinator involved): direct handoff signals between physically adjacent stations, e.g. `bottleAtPos2`. Keeps stations working even if the coordinator stalls.
- **Coordinator signals**: recipe (ratio/volume), bottle quantity, batch progress, `batchDone`. The Coordinator pushes recipe parameters to the filler at batch start; it does not micromanage a station's internal sequencing.
- **POS → Coordinator**: `OrderQueue` (Java side) serializes submitted orders, sending ratio/volume/quantity directly to the Coordinator (not relayed through the POS clock domain — an earlier design that routed data through POS was a source of GALS timing races). POS only handles the submit trigger and reporting batch completion back to the GUI.
- **GALS signal reliability**: every cross-clock-domain signal in the fill pipeline must be held/sustained, never a bare single-tick `emit` — see CLAUDE.md's "Cross-clock-domain signal reliability" section before adding a new signal.

## Conventions

- Each station's `.sysj` file is the source of truth; the paired `.java` file is generated output — regenerate via `sjc`, never hand-edit.
- Signal naming: `<ClockDomain>.<signalName>` (e.g. `FillerControllerCD.bottleAtPos2`, `CoordinatorCD.batchDone`) — see `Ports.java` and the `.xml` module descriptors for the full registry and TCP ports.
- Each station has its own Swing viz panel talking to the running process over plain TCP (`SignalServer`/`SignalClient`/`Worker`), not through the SystemJ runtime directly.

## IP assignments

- **Chris**: Fault tolerance. Detect a fault in simulation and respond to it (reject to bin, or route to a redundant machine).
- **Tania**: Detailed visualisation of each intelligent machine, click into a station from the main system view to see its details.
- **Marie**: Digital twins of products, used to enhance the production process and track a product's after-sales life. Covers both workpiece and workstation twins.
