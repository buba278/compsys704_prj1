package machines.conveyor;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

// translates the controller/plant's viz signals into ConveyorState, which the swing canvas polls
public class ConveyorVizWorker extends Worker {

	@Override
	public void setSignal(boolean status) {
		switch (signame) {
		case "motorOnE": case "motorOnBigE":               ConveyorState.MOTOR_ON = status; break;
		case "bottleAtPos1E": case "bottleAtPos1BigE":     ConveyorState.setBottleAtPos1(status); break;
		case "bottleLeftPos5E": case "bottleLeftPos5BigE": ConveyorState.setBottleLeftPos5(status); break;
		case "bottleEnteredE": case "bottleEnteredBigE":   if (status) ConveyorState.startLeftTravel(); break;
		case "bottleReceivedE": case "bottleReceivedBigE": if (status) ConveyorState.startRightTravel(); break;
		case "leftMovingE": case "leftMovingBigE":     if (status) ConveyorState.advanceLeftStep(); break;
		case "rightMovingE": case "rightMovingBigE":   if (status) ConveyorState.advanceRightStep(); break;
		case "bottlesOnTableE": break; // value arrives via setIntSignal
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	@Override
	public void setIntSignal(int value) {
		switch (signame) {
		case "bottlesOnTableE":                             ConveyorState.BOTTLES_ON_TABLE = value; break;
		case "bottleReceivedE": case "bottleReceivedBigE":  ConveyorState.RIGHT_RATIO_A = value; break;
		default:
			System.err.println("Wrong sig name : " + signame);
			System.exit(1);
		}
	}

	static final List<String> signames = Arrays.asList(
			"motorOnE", "bottleAtPos1E", "bottleLeftPos5E", "bottleEnteredE", "bottleReceivedE",
			"leftMovingE", "rightMovingE", "bottlesOnTableE",
			"motorOnBigE", "bottleAtPos1BigE", "bottleLeftPos5BigE", "bottleEnteredBigE",
			"bottleReceivedBigE", "leftMovingBigE", "rightMovingBigE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}