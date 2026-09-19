# Extended Automatic Bottling System and Purchase Order System (EABS + POS)

COMPSYS704 project. Each intelligent machine is an independent SystemJ clock domain (paired `<name>Controller.sysj` decision logic + `<name>Plant.sysj` physical simulation), each a separate Java process communicating over TCP. Stations: cap loader, filler, conveyor, rotary table, capper, labeller, sorter, plus a POS front end and a Coordinator that drives batches.

## Running instructions

IDE used is Eclipse (with `.project`/`.classpath`/`.settings` included).

1. Import the project into Eclipse.
2. Run **`BuildAll`** (`launches/BuildAll.launch`) once to regenerate all `src/*.java` from all `sysj/*.sysj` - top-level `src/*.java` is not tracked.
3. Run **`RunAll`** (`launches/RunAll.launch`) - a launch group that starts every station process plus the digital twin server (`RunTwinServer`) and the big-picture viewer (`RunBigPicture`) together. `RunAll_Live` is the same set but with console logs instead of file logs. Individual `Run<Station>.launch` configs exist if you want to start/restart one process on its own (useful after touching a single `.sysj` file).
4. Use either the POS GUI or the big picture GUI to submit an order and watch the big-picture viewer showing the batch moving through the stations. Click on stations within the big picture GUI to focus on their individual window.

## IP assignments

- **Chris**: Fault tolerance. Detect a fault in simulation and respond to it (reject to bin, or route to a redundant machine).
- **Tania**: Detailed visualisation of each intelligent machine, click into a station from the main system view to see its details.
- **Marie**: Digital twins of products, used to enhance the production process and track a product's after-sales life. Covers both workpiece and workstation twins.
