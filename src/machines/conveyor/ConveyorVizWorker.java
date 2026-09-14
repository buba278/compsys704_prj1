package machines.conveyor;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class ConveyorVizWorker extends Worker {

	// NOTE: this class used to also push readyToRotate into the Rotary
	// Table Controller from here, piggy-backing on the bottleAtPos1E event.
	// That's been removed - the handoff is now a direct, acknowledgement-
	// based handshake between conveyorController.sysj and
	// rotaryTableController.sysj (see those files). This class is purely a
	// visualisation event forwarder again.

	@Override
	public void setSignal(boolean status) {
		switch (signame) {
		case "motorOnE":        ConveyorState.MOTOR_ON = status; break;
		case "bottleAtPos1E":   ConveyorState.setBottleAtPos1(status); break;
		case "bottleLeftPos5E": ConveyorState.setBottleLeftPos5(status); break;
		case "bottleEnteredE":  if (status) ConveyorState.startLeftTravel(); break;
		case "bottleReceivedE": if (status) ConveyorState.startRightTravel(); break;
		// each of these fires once per actual model tick spent travelling,
		// so the animation can be driven by real ticks instead of a guessed
		// real-time duration - see ConveyorState/ConveyorCanvas.
		case "leftMovingE":     if (status) ConveyorState.advanceLeftStep(); break;
		case "rightMovingE":    if (status) ConveyorState.advanceRightStep(); break;
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
			"motorOnE", "bottleAtPos1E", "bottleLeftPos5E", "bottleEnteredE", "bottleReceivedE",
			"leftMovingE", "rightMovingE");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}