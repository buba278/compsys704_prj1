package run;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;

/** Cross-domain sends for the Conveyor <-> Rotary Table Pos 1 / Pos 5 handoffs.
 *  These SignalLevelClients must be owned by a class in a single-segment package
 *  (like this one) rather than declared inline inside a .sysj reactive body: this
 *  build of sjc (com.systemj.compiler.JavaPrettyPrinter) crashes during type
 *  resolution when a .sysj file imports and instantiates a class from a
 *  multi-segment package (e.g. org.compsys704.SignalLevelClient) directly -
 *  confirmed by isolated repro, single-segment packages such as this one's "run"
 *  are unaffected. Static-wrapper indirection, same shape as FillerFaultState,
 *  sidesteps the bug entirely.
 */
public class RotaryConveyorBridge {
	private static final SignalLevelClient READY_TO_ROTATE =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_CONTROLLER, Ports.ROTARYTABLE_READY_TO_ROTATE);
	private static final SignalLevelClient POS1_ACK_TO_CONTROLLER =
			new SignalLevelClient(Ports.PORT_CONVEYOR_CONTROLLER, Ports.CONVEYOR_CONTROLLER_POS1_TAKEN_ACK);
	private static final SignalLevelClient POS1_ACK_TO_PLANT =
			new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_PLANT_POS1_TAKEN_ACK);
	private static final SignalLevelClient BOTTLE_FROM_TABLE =
			new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_BOTTLE_FROM_TABLE);
	private static final SignalLevelClient CONVEYOR_TAKEN_ACK =
			new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_CONVEYOR_TAKEN_ACK);
	// Carries whether THIS bottle was abandoned (unfilled/no lid/uncapped) by
	// any station on its way around the table - set/cleared in lockstep with
	// BOTTLE_FROM_TABLE above so it's always correctly scoped to the one
	// bottle currently being handed off (the claim/release locks in
	// RotaryTableState already guarantee only one lane hands off at a time).
	// Lets the Sorter reject a bottle for a real reason instead of a coin
	// flip - see sorterPlant.sysj.
	private static final SignalLevelClient BOTTLE_DEFECTIVE_FROM_TABLE =
			new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_BOTTLE_DEFECTIVE_FROM_TABLE);

	public static void setReadyToRotate(boolean state) { READY_TO_ROTATE.send(state); }
	public static void setPos1AckToController(boolean state) { POS1_ACK_TO_CONTROLLER.send(state); }
	public static void setPos1AckToPlant(boolean state) { POS1_ACK_TO_PLANT.send(state); }
	public static void setBottleFromTable(boolean state) { BOTTLE_FROM_TABLE.send(state); }
	public static void setConveyorTakenAck(boolean state) { CONVEYOR_TAKEN_ACK.send(state); }
	public static void setBottleDefectiveFromTable(boolean state) { BOTTLE_DEFECTIVE_FROM_TABLE.send(state); }
}
