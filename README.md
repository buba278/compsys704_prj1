# Automatic Bottling System

## System Structure

Hybrid architecture: each intelligent machine is an independent SystemJ clock domain
(Controller + Plant pair, same pattern as the Lab 3 Lid Loader), coordinating with its
immediate neighbours via direct local signals. A thin Coordinator clock domain owns
only the things no single station should own, such as current recipe, batch/workpiece
progress, mode, and global stop conditions. The Coordinator is also the integration
point for the Purchase Order System (POS).

```
sysj/
  coordinator.sysj / coordinator.xml        # batch/recipe state, POS-facing, global stop
  pos.sysj / pos.xml                        # accepts orders, launches/schedules batches

  loader.sysj + loaderPlant.sysj            # bottle loader (empty WP onto conveyor), new design
  conveyor.sysj + conveyorPlant.sysj        # single-loop, clockwise index-table conveyor, pos 1 & 5
  rotarytable.sysj + rotarytablePlant.sysj  # 60deg rotation, pos 1/2/3/4/5 alignment
  filler.sysj + fillerPlant.sysj            # dual-liquid filler, pos 2
  capplacer.sysj + capplacerPlant.sysj      # places cap onto bottle (magazine + vacuum pick/place), pos 3
                                            # adapted from Lab 3 controller.sysj/plant.sysj
  capper.sysj + capperPlant.sysj            # separate machine, twists/clamps the placed cap, pos 4
  unloader.sysj + unloaderPlant.sysj        # removes completed bottles + diverter sorting, pos 5
  labeler.sysj + labelerPlant.sysj          # applies label, sits at the end of the conveyor

src/
  coordinator/    Coordinator.java                          # generated from sysj/coordinator.sysj
  pos/            PurchaseOrderSystem.java                  # generated from sysj/pos.sysj
  stations/
    loader/       Loader.java, LoaderPlant.java
    conveyor/     Conveyor.java, ConveyorPlant.java
    rotarytable/  RotaryTable.java, RotaryTablePlant.java
    filler/       Filler.java, FillerPlant.java
    capplacer/    CapPlacer.java, CapPlacerPlant.java        # adapted from current Controller.java/Plant.java
    capper/       Capper.java, CapperPlant.java
    unloader/     Unloader.java, UnloaderPlant.java
    labeler/      Labeler.java, LabelerPlant.java
  org/compsys704/
    common/       Ports.java, SignalServer.java, SignalClient.java, SignalRadioClient.java,
                  SignalCheckBoxClient.java, Canvas.java, Worker.java, States.java
    viz/          StationPanel.java (one per station), DashboardGUI.java (merges all panels)
  run/            GUI.java (per-station launcher)
```

## Coordination model

- **Local signals** (station to station, no coordinator involved): handoff signals
  between physically adjacent stations, e.g. `bottleAtPos1`, `bottleLeftPos5`,
  `capOnBottleAtPos1`, `tableAlignedWithSensor`. These stay direct so each station
  keeps working even if the coordinator stalls.
- **Coordinator signals**: recipe/mix ratio, mode (auto/manual), batch start/stop,
  refill/empty, global halt, and workpiece identity/tracking. The Coordinator pushes
  recipe parameters to the Filler at batch start and aggregates station status for
  the dashboard. It does not micromanage each station's internal sequencing.
- **POS**: submits orders to the Coordinator only, no direct link to individual stations.

## Intelligent Machine conventions

- Each station has an independent UI panel (`StationPanel`, Swing) showing its own
  plant status, same pattern as `CapLoader`/`Canvas` from Lab 3. `DashboardGUI` (IP) will merge
  all station panels plus Coordinator/POS status into one overall UI.
- Signal naming follows `<ClockDomain>.<signalName>` (e.g. `FillerCD.bottleAtPos2`,
  `CoordinatorCD.startBatch`), see `Ports.java` for the full registry.
- Every station's `.sysj` file is the source of truth. The paired `.java` file in
  `src/stations/<station>/` is generated output, regenerate via `sjc`.
- Lab 3's `controller.sysj`/`plant.sysj` was a cap dispenser/placer, not a generic
  lid loader, so its pick-and-place pattern is reused for `capplacer`, not `loader`.

## IP assignments

- **Chris**: Fault tolerance. Detect a fault in simulation and respond to it
  (reject to bin, or route to a redundant machine).
- **Tania**: Detailed visualisation of each intelligent machine, click into a
  station from the main system view to see its details.
- **Marie**: Digital twins of products, used to enhance the production process and
  track a product's after-sales life. Covers both workpiece and workstation twins.