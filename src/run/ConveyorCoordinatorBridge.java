package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain ack for the Coordinator -> Conveyor bottlesNeeded handoff.
 *  Same reasoning as the other Rotary*Bridge classes: this SignalLevelClient
 *  must be owned by a class in a single-segment package (like this one)
 *  rather than declared inline inside a .sysj reactive body - this build of
 *  sjc crashes resolving types from multi-segment packages such as
 *  org.compsys704 when used for a local instance declaration inside a .sysj
 *  reactive body.
 */
public class ConveyorCoordinatorBridge {
	private static final SignalLevelClient BOTTLES_NEEDED_ACK =
			new SignalLevelClient(Ports.PORT_COORDINATOR, Ports.COORDINATOR_BOTTLES_NEEDED_ACK);

	public static void setBottlesNeededAck(boolean state) { BOTTLES_NEEDED_ACK.send(state); }
}
