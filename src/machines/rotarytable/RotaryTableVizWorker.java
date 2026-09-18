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
		case "rotaryTableTriggerE": case "rotaryTableTriggerBigE":         break; // no visual effect of its own - see per-slot highlights
		case "slot0BottleIdE": case "slot1BottleIdE": case "slot2BottleIdE": case "slot3BottleIdE": case "slot4BottleIdE":
		case "slot0BottleIdBigE": case "slot1BottleIdBigE": case "slot2BottleIdBigE": case "slot3BottleIdBigE": case "slot4BottleIdBigE":
			break; // value arrives via setIntSignal
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		switch (signame) {
		case "slot0BottleIdE": case "slot0BottleIdBigE": RotaryTableState.recordSlotBottleId(0, value); break;
		case "slot1BottleIdE": case "slot1BottleIdBigE": RotaryTableState.recordSlotBottleId(1, value); break;
		case "slot2BottleIdE": case "slot2BottleIdBigE": RotaryTableState.recordSlotBottleId(2, value); break;
		case "slot3BottleIdE": case "slot3BottleIdBigE": RotaryTableState.recordSlotBottleId(3, value); break;
		case "slot4BottleIdE": case "slot4BottleIdBigE": RotaryTableState.recordSlotBottleId(4, value); break;
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	static final List<String> signames = Arrays.asList(
			"tableAlignedWithSensorE", "bottleAtPos5E", "rotaryTableTriggerE",
			"slot0BottleIdE", "slot1BottleIdE", "slot2BottleIdE", "slot3BottleIdE", "slot4BottleIdE",
			"tableAlignedWithSensorBigE", "bottleAtPos5BigE", "rotaryTableTriggerBigE",
			"slot0BottleIdBigE", "slot1BottleIdBigE", "slot2BottleIdBigE", "slot3BottleIdBigE", "slot4BottleIdBigE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}
