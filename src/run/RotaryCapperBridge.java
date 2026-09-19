package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Rotary Table <-> Capper Pos1/Pos4 handoff.
 *  Same reasoning as RotaryConveyorBridge: these SignalLevelClients must be owned by
 *  a class in a single-segment package (like this one) rather than declared inline
 *  inside a .sysj reactive body - this build of sjc crashes resolving types from
 *  multi-segment packages (e.g. org.compsys704) when used for a local instance
 *  declaration inside a .sysj reactive body.
 */
public class RotaryCapperBridge {
	private static final SignalLevelClient BOTTLE_AT_POS4_PLANT =
			new SignalLevelClient(Ports.PORT_CAPPER_PLANT, Ports.CAPPER_BOTTLE_AT_POS4);
	// CapperControllerCD listens for bottleAtPos4 on its OWN port (separate
	// process from CapperPlantCD) - CapperPlant never forwards it, so this
	// must be sent directly to the Controller too, same dual-send shape as
	// Coordinator's bottleAtPos2/bottleAtPos2P for the Filler. Without this,
	// CapperController never sees the bottle and the Rotary Table always
	// burns its full 10s ack-or-timeout window waiting for capperTakenAck.
	private static final SignalLevelClient BOTTLE_AT_POS4_CONTROLLER =
			new SignalLevelClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_BOTTLE_AT_POS4_CONTROLLER);
	// Carries RotaryIndexState.isDefective(3) alongside BOTTLE_AT_POS4_CONTROLLER so the Controller
	// can see a bottle already abandoned upstream (e.g. by the Lid Placer) before it attempts to cap it.
	private static final SignalLevelClient BOTTLE_DEFECTIVE_AT_POS4_CONTROLLER =
			new SignalLevelClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_BOTTLE_DEFECTIVE_AT_POS4_CONTROLLER);
	private static final SignalLevelClient CAPPER_TAKEN_ACK =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_CAPPER_TAKEN_ACK);
	private static final SignalLevelClient CAPPER_FAULTED =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_CAPPER_FAULTED);

	public static void setBottleAtPos4(boolean state) {
		BOTTLE_AT_POS4_PLANT.send(state);
		BOTTLE_AT_POS4_CONTROLLER.send(state);
	}
	public static void setBottleDefectiveAtPos4(boolean state) { BOTTLE_DEFECTIVE_AT_POS4_CONTROLLER.send(state); }
	public static void setCapperTakenAck(boolean state) { CAPPER_TAKEN_ACK.send(state); }
	public static void setCapperFaulted(boolean state) { CAPPER_FAULTED.send(state); }
}
