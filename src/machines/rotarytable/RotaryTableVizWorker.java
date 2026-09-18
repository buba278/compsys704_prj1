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
		case "rotaryTableTriggerE":     break; // no visual effect of its own - see per-slot highlights
		case "slot0BottleIdE": case "slot1BottleIdE": case "slot2BottleIdE": case "slot3BottleIdE": case "slot4BottleIdE":
			break; // value arrives via setIntSignal
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		switch (signame) {
		case "slot0BottleIdE": RotaryTableState.recordSlotBottleId(0, value); break;
		case "slot1BottleIdE": RotaryTableState.recordSlotBottleId(1, value); break;
		case "slot2BottleIdE": RotaryTableState.recordSlotBottleId(2, value); break;
		case "slot3BottleIdE": RotaryTableState.recordSlotBottleId(3, value); break;
		case "slot4BottleIdE": RotaryTableState.recordSlotBottleId(4, value); break;
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	static final List<String> signames = Arrays.asList(
			"tableAlignedWithSensorE", "bottleAtPos5E", "rotaryTableTriggerE",
			"slot0BottleIdE", "slot1BottleIdE", "slot2BottleIdE", "slot3BottleIdE", "slot4BottleIdE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}
