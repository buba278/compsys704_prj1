package machines.rotarytable;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class RotaryTableVizWorker extends Worker {

	@Override
	public void setSignal(boolean status) {
		switch (signame) {
		case "tableAlignedWithSensorE": RotaryTableState.TABLE_ALIGNED = status; break;
		case "bottleAtPos5E":           RotaryTableState.BOTTLE_AT_POS5 = status; break;
		case "capOnBottleAtPos1E":      RotaryTableState.CAP_ON_BOTTLE_AT_POS1 = status; break;
		case "rotaryTableTriggerE":     if (status) RotaryTableState.triggerStep(); break;
		case "bottleStage1E": case "bottleStage2E": case "bottleStage3E":
			break; // value arrives via setIntSignal
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		switch (signame) {
		case "bottleStage1E": RotaryTableState.recordLaneStage(0, value); break;
		case "bottleStage2E": RotaryTableState.recordLaneStage(1, value); break;
		case "bottleStage3E": RotaryTableState.recordLaneStage(2, value); break;
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	static final List<String> signames = Arrays.asList(
			"tableAlignedWithSensorE", "bottleAtPos5E", "capOnBottleAtPos1E", "rotaryTableTriggerE",
			"bottleStage1E", "bottleStage2E", "bottleStage3E");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}
