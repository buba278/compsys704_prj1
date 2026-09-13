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
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		System.err.println("Wrong sig name : " + signame);
		System.exit(1);
	}

	static final List<String> signames = Arrays.asList(
			"tableAlignedWithSensorE", "bottleAtPos5E", "capOnBottleAtPos1E", "rotaryTableTriggerE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}
