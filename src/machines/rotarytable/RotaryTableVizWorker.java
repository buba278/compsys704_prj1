package machines.rotarytable;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class RotaryTableVizWorker extends Worker {

	@Override
	public void setSignal(boolean status) {
		switch (signame) {
		// *BigE names are the same events mirrored to the Big-Picture window's own
		// socket (see rotaryTablePlant.sysj/xml) - handled identically to their *E
		// counterpart since both just drive this same RotaryTableState.
		case "tableAlignedWithSensorE": case "tableAlignedWithSensorBigE": RotaryTableState.TABLE_ALIGNED = status; break;
		case "bottleAtPos5E": case "bottleAtPos5BigE":                     RotaryTableState.BOTTLE_AT_POS5 = status; break;
		case "capOnBottleAtPos1E": case "capOnBottleAtPos1BigE":           RotaryTableState.CAP_ON_BOTTLE_AT_POS1 = status; break;
		case "rotaryTableTriggerE": case "rotaryTableTriggerBigE":         if (status) RotaryTableState.triggerStep(); break;
		case "bottleStage1E": case "bottleStage2E": case "bottleStage3E":
		case "bottleStage1BigE": case "bottleStage2BigE": case "bottleStage3BigE":
			break; // value arrives via setIntSignal
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		switch (signame) {
		case "bottleStage1E": case "bottleStage1BigE": RotaryTableState.recordLaneStage(0, value); break;
		case "bottleStage2E": case "bottleStage2BigE": RotaryTableState.recordLaneStage(1, value); break;
		case "bottleStage3E": case "bottleStage3BigE": RotaryTableState.recordLaneStage(2, value); break;
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	static final List<String> signames = Arrays.asList(
			"tableAlignedWithSensorE", "bottleAtPos5E", "capOnBottleAtPos1E", "rotaryTableTriggerE",
			"bottleStage1E", "bottleStage2E", "bottleStage3E",
			"tableAlignedWithSensorBigE", "bottleAtPos5BigE", "capOnBottleAtPos1BigE", "rotaryTableTriggerBigE",
			"bottleStage1BigE", "bottleStage2BigE", "bottleStage3BigE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}
