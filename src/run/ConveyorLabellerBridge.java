package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Conveyor <-> Labeller collection-point handoff.
 *  Same reasoning as RotaryConveyorBridge: these SignalLevelClients must be owned by
 *  a class in a single-segment package (like this one) rather than declared inline
 *  inside a .sysj reactive body - this build of sjc crashes resolving types from
 *  multi-segment packages (e.g. org.compsys704) when used for a local instance
 *  declaration inside a .sysj reactive body.
 */
public class ConveyorLabellerBridge {
	private static final SignalLevelClient BOTTLE_FROM_CONVEYOR =
			new SignalLevelClient(Ports.PORT_LABELLER_PLANT, Ports.LABELLER_BOTTLE_FROM_CONVEYOR);
	private static final SignalLevelClient LABELLER_TAKEN_ACK =
			new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_LABELLER_TAKEN_ACK);
	// Carries the defective flag on from RotaryConveyorBridge - see
	// rotaryTablePlant.sysj / conveyorPlant.sysj.
	private static final SignalLevelClient BOTTLE_DEFECTIVE_FROM_CONVEYOR =
			new SignalLevelClient(Ports.PORT_LABELLER_PLANT, Ports.LABELLER_BOTTLE_DEFECTIVE_FROM_CONVEYOR);

	public static void setBottleFromConveyor(boolean state) { BOTTLE_FROM_CONVEYOR.send(state); }
	public static void setLabellerTakenAck(boolean state) { LABELLER_TAKEN_ACK.send(state); }
	public static void setBottleDefectiveFromConveyor(boolean state) { BOTTLE_DEFECTIVE_FROM_CONVEYOR.send(state); }
}
