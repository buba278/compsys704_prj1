package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Rotary Table <-> Coordinator/Filler handoff.
 *  Same reasoning as RotaryConveyorBridge/RotaryCapperBridge: these
 *  SignalLevelClients must be owned by a class in a single-segment package
 *  (like this one) rather than declared inline inside a .sysj reactive body -
 *  this build of sjc crashes resolving types from multi-segment packages
 *  (e.g. org.compsys704) when used for a local instance declaration inside a
 *  .sysj reactive body.
 */
public class RotaryFillerBridge {
	private static final SignalLevelClient BOTTLE_READY_FOR_FILLER =
			new SignalLevelClient(Ports.PORT_COORDINATOR, Ports.COORDINATOR_BOTTLE_READY_FOR_FILLER);
	private static final SignalLevelClient FILLER_TAKEN_ACK =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_FILLER_TAKEN_ACK);
	private static final SignalLevelClient FILLER_FAULTED =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_FILLER_FAULTED);

	public static void setBottleReadyForFiller(boolean state) { BOTTLE_READY_FOR_FILLER.send(state); }
	public static void setFillerTakenAck(boolean state) { FILLER_TAKEN_ACK.send(state); }
	public static void setFillerFaulted(boolean state) { FILLER_FAULTED.send(state); }
}
