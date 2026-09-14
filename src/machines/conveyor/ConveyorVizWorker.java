package machines.conveyor;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;
import org.compsys704.Worker;

public class ConveyorVizWorker extends Worker {

	// A bottle reaching Pos 1 on the conveyor is the Rotary Table's cue
	// to accept it, so forward this event straight on as readyToRotate 
	private static final SignalLevelClient readyToRotateClient =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_CONTROLLER, Ports.ROTARYTABLE_READY_TO_ROTATE);

	@Override
	public void setSignal(boolean status) {
		switch (signame) {
		case "motorOnE":        ConveyorState.MOTOR_ON = status; break;
		case "bottleAtPos1E":
			ConveyorState.BOTTLE_AT_POS1 = status;
			readyToRotateClient.send(status);
			break;
		case "bottleLeftPos5E": ConveyorState.BOTTLE_LEFT_POS5 = status; break;
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

	static final List<String> signames = Arrays.asList("motorOnE", "bottleAtPos1E", "bottleLeftPos5E");

	@Override
	public boolean hasSignal(String sn) {
		return signames.contains(sn);
	}
}