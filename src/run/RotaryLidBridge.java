package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Rotary Table <-> Lid Placer (Pos 3) handoff.
 *  The Lid Placer is the original Lab-3 pick-and-place cell (ControllerCD/
 *  PlantCD) repurposed as the "Pos3: Lid Placing" station between the Filler
 *  (Pos 2) and the Capper (Pos 4). Same reasoning as RotaryConveyorBridge/
 *  RotaryCapperBridge/RotaryFillerBridge: these SignalLevelClients must be
 *  owned by a class in a single-segment package (like this one) rather than
 *  declared inline inside a .sysj reactive body - this build of sjc crashes
 *  resolving types from multi-segment packages (e.g. org.compsys704) when
 *  used for a local instance declaration inside a .sysj reactive body.
 *
 *  PlantCD's arm/pusher branches gate each individual movement on "enable"
 *  going true, then require it to drop back to false (await(!enable)) before
 *  the next movement will be accepted - a manual step/deadman interlock in
 *  the original GUI-driven lab. There's no way for this bridge (a separate
 *  process) to know exactly when each internal movement becomes pending, so
 *  the Rotary Table drives "enable" as a fast square wave for the whole
 *  request window instead of a single level - see rotaryTablePlant.sysj.
 */
public class RotaryLidBridge {
	private static final SignalLevelClient REQUEST =
			new SignalLevelClient(Ports.PORT_LOADER_CONTROLLER, Ports.REQUEST_SIGNAL);
	private static final SignalLevelClient ENABLE =
			new SignalLevelClient(Ports.PORT_LOADER_PLANT, Ports.ENABLE_SIGNAL);
	private static final SignalLevelClient LID_PLACED_ACK =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.LID_PLACED_ACK);
	private static final SignalLevelClient LID_FAULTED =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_LID_FAULTED);

	public static void setRequest(boolean state) { REQUEST.send(state); }
	public static void setEnable(boolean state) { ENABLE.send(state); }
	public static void setLidPlacedAck(boolean state) { LID_PLACED_ACK.send(state); }
	public static void setLidFaulted(boolean state) { LID_FAULTED.send(state); }
}
