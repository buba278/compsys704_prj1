package org.compsys704;

public class Ports {
	public static final String REQUEST_SIGNAL = "ControllerCD.request";
	public static final String REFILL_SIGNAL = "PlantCD.refill";
	public static final String ENABLE_SIGNAL = "PlantCD.enable";
	public static final int PORT_LOADER_PLANT = 10001;
	public static final int PORT_LOADER_CONTROLLER = 10000;
	public static final int PORT_LOADER_VIZ = 20000;
	
	// Manual 
	public static final String SIGNAL_PUSHER_EXTEND   = "ControllerCD.pusherExtendM";
//	public static final String SIGNAL_VACOFF          = "ControllerCD.vacOffM";
	public static final String SIGNAL_VACON           = "ControllerCD.vacOnM";
	public static final String SIGNAL_ARM_SOURCE      = "ControllerCD.armSourceM";
	public static final String SIGNAL_ARM_DEST        = "ControllerCD.armDestM";
	public static final String SIGNAL_Mode            = "ControllerCD.mode";
	
	// === FILLER ===
	public static final int PORT_FILLER_CONTROLLER = 10002;
	public static final int PORT_FILLER_PLANT      = 10003;
	public static final int PORT_FILLER_VIZ        = 20001;

	// for debug: stand in for signals that will come from RotaryTable/Coordinator once those exist
	public static final String FILLER_BOTTLE_AT_POS2_CONTROLLER = "FillerControllerCD.bottleAtPos2";
	public static final String FILLER_BOTTLE_AT_POS2_PLANT      = "FillerPlantCD.bottleAtPos2";
	public static final String FILLER_LIQUID_A_RATIO            = "FillerControllerCD.liquidARatio";
	public static final String FILLER_TARGET_VOLUME_ML          = "FillerControllerCD.targetVolumeMl";

	// === ROTARY TABLE ===
	// Reassigned off their original ports (10004/10005 collided with
	// PORT_COORDINATOR/PORT_POS) now that the Rotary Table genuinely needs to
	// run alongside the Coordinator for the Filler link below.
	public static final int PORT_ROTARYTABLE_CONTROLLER = 10016;
	public static final int PORT_ROTARYTABLE_PLANT      = 10017;
	public static final int PORT_ROTARYTABLE_VIZ        = 20002;

	// real controller inputs
	public static final String ROTARYTABLE_MODE             = "RotaryTableControllerCD.mode";
	public static final String ROTARYTABLE_ROTATE_M         = "RotaryTableControllerCD.rotateM";

	// for debug: stand in for signals that will come from Conveyor once it exists
	public static final String ROTARYTABLE_READY_TO_ROTATE              = "RotaryTableControllerCD.readyToRotate";

	// kicks the plant's sensor loop off (it sits at await(start) until this fires)
	public static final String ROTARYTABLE_START = "RotaryTablePlantCD.start";

	// Rotary Table <-> Filler (Coordinator) handoff, mirroring the Capper
	// handoff below: the table offers a bottle once it's capped, and the
	// Coordinator acks once it's genuinely filled.
	public static final String ROTARYTABLE_FILLER_TAKEN_ACK       = "RotaryTablePlantCD.fillerTakenAck";
	public static final String COORDINATOR_BOTTLE_READY_FOR_FILLER = "CoordinatorCD.bottleReadyForFiller";
	// Carries the real outcome alongside each station's ack - the ack alone only means
	// "this station is done with the bottle", not "it succeeded", so the Rotary Table
	// needs this to know whether to mark the slot defective for downstream sorting.
	public static final String ROTARYTABLE_FILLER_FAULTED = "RotaryTablePlantCD.fillerFaulted";
	public static final String ROTARYTABLE_LID_FAULTED    = "RotaryTablePlantCD.lidFaulted";
	public static final String ROTARYTABLE_CAPPER_FAULTED = "RotaryTablePlantCD.capperFaulted";

	// Conveyor acks the Coordinator's per-order bottlesNeeded broadcast once
	// it has genuinely latched the count into LoaderState, turning what used
	// to be a fire-and-forget widened hold into a proper ack-or-timeout (see
	// coordinator.sysj / conveyorPlant.sysj) - the only recipe-broadcast
	// signal that previously had no receipt confirmation at all.
	public static final String COORDINATOR_BOTTLES_NEEDED_ACK = "CoordinatorCD.bottlesNeededAck";

	// Conveyor acks the Rotary Table's Pos 5 handoff immediately on receipt,
	// rather than the Rotary Table guessing a fixed wait - the Conveyor's own
	// downstream handoff to the Labeller alone can take up to 30s, far too
	// long to hold a fixed timer for.
	public static final String ROTARYTABLE_CONVEYOR_TAKEN_ACK      = "RotaryTablePlantCD.conveyorTakenAck";

	// Pos 1 handoff handshake with the Conveyor
	public static final String CONVEYOR_CONTROLLER_POS1_TAKEN_ACK = "ConveyorControllerCD.pos1TakenAck";
	public static final String CONVEYOR_PLANT_POS1_TAKEN_ACK      = "ConveyorPlantCD.pos1TakenAck";

	// === CONVEYOR ===
	public static final int PORT_CONVEYOR_CONTROLLER = 10006;
	public static final int PORT_CONVEYOR_PLANT      = 10007;
	public static final int PORT_CONVEYOR_VIZ        = 20003;
 
	// real controller inputs
	public static final String CONVEYOR_MODE       = "ConveyorControllerCD.mode";
	public static final String CONVEYOR_CONVEYOR_M = "ConveyorControllerCD.conveyorM";
 
	// for debug: stand in for a signal that will come from a Loader once it exists
	public static final String CONVEYOR_ENABLE            = "ConveyorPlantCD.enable";
	public static final String CONVEYOR_LOAD_BOTTLE       = "ConveyorPlantCD.loadBottle";
	// real: driven automatically by RotaryConveyorBridge, not a GUI button
	public static final String CONVEYOR_BOTTLE_FROM_TABLE = "ConveyorPlantCD.bottleFromTable";
	public static final String CONVEYOR_BOTTLE_DEFECTIVE_FROM_TABLE = "ConveyorPlantCD.bottleDefectiveFromTable";

	// fault tolerance: manual fault injection from the Filler GUI
	public static final String FILLER_OVERFILL_M                = "FillerPlantCD.overfillM";
	public static final String FILLER_STALL_M                   = "FillerPlantCD.stallM";
	// fault tolerance: operator clears an active fault - goes to the controller (where the
	// dosing/abandon-bottle decision lives), not the plant
	public static final String FILLER_CLEAR_FAULT_M             = "FillerControllerCD.clearFaultM";

	// === CAPPER ===
	// Reassigned off their original ports (CapperControllerCD was 10005, colliding with
	// PORT_ROTARYTABLE_PLANT and PORT_POS; CapperPlantCD was split across 10003, colliding
	// with PORT_FILLER_PLANT, and 10015) now that Capper genuinely needs to run alongside
	// those stations for the RotaryTable<->Capper link below.
	public static final int PORT_CAPPER_CONTROLLER = 10010;
	public static final int PORT_CAPPER_PLANT      = 10011;

	// RotaryTable <-> Capper handoff (bottle capped at the Rotary Table's capper position)
	public static final String CAPPER_BOTTLE_AT_POS4            = "CapperPlantCD.bottleAtPos4";
	public static final String CAPPER_BOTTLE_AT_POS4_CONTROLLER = "CapperControllerCD.bottleAtPos4";
	public static final String ROTARYTABLE_CAPPER_TAKEN_ACK     = "RotaryTablePlantCD.capperTakenAck";

	// RotaryTable <-> Lid Placer (Pos 3, the original Lab-3 Controller/Plant) handoff.
	// Reuses REQUEST_SIGNAL/ENABLE_SIGNAL/PORT_LOADER_* above (this IS that station).
	public static final String LID_PLACED_ACK = "RotaryTablePlantCD.lidPlacedAck";

	// fault tolerance: manual fault injection / clear from the Capper GUI.
	// "Stall" (like the Filler's Overfill/Stall) goes to the PLANT - it
	// simulates the physical cause (the twist motor stalling before
	// completion) so the controller's own timeout detection is what
	// actually raises the fault, not the GUI faking the end state.
	public static final String CAPPER_STALL_M       = "CapperPlantCD.capperStallM";
	public static final String CAPPER_CLEAR_FAULT_M = "CapperControllerCD.clearCapperFaultM";

	// fault tolerance: manual fault injection / clear from the Lid Placer GUI.
	// "Drop Lid" (like the Filler's Overfill/Stall) goes to the PLANT - it
	// simulates the physical cause (a lid slipping out of the grip before
	// WPgripped ever asserts) so the controller's own retry/timeout
	// detection is what actually raises the fault, not the GUI faking the
	// end state.
	public static final String LOADER_DROP_LID_M    = "PlantCD.dropLidM";
	public static final String LOADER_CLEAR_FAULT_M = "ControllerCD.clearLidFaultM";

	// === LABELLER ===
	public static final int PORT_LABELLER_CONTROLLER = 10008;
	public static final int PORT_LABELLER_PLANT      = 10009;

	// Conveyor <-> Labeller handoff (collection point)
	public static final String LABELLER_BOTTLE_FROM_CONVEYOR = "LabellerPlantCD.bottleFromConveyor";
	public static final String CONVEYOR_LABELLER_TAKEN_ACK   = "ConveyorPlantCD.labellerTakenAck";
	// Carries whether this bottle was abandoned upstream (unfilled/no lid/
	// uncapped) - see rotaryTablePlant.sysj/conveyorPlant.sysj. Lets the
	// Sorter reject for a real reason instead of a coin flip.
	public static final String LABELLER_BOTTLE_DEFECTIVE_FROM_CONVEYOR = "LabellerPlantCD.bottleDefectiveFromConveyor";

	// Labeller <-> Sorter handoff
	public static final String SORTER_BOTTLE_FROM_LABELLER = "SorterPlantCD.bottleFromLabeller";
	public static final String LABELLER_SORTER_TAKEN_ACK   = "LabellerPlantCD.sorterTakenAck";
	public static final String SORTER_BOTTLE_DEFECTIVE_FROM_LABELLER = "SorterPlantCD.bottleDefectiveFromLabeller";

	// === SORTER ===
	public static final int PORT_SORTER_CONTROLLER = 10025;
	public static final int PORT_SORTER_PLANT      = 10023;

	// === COORDINATOR + POS ===
	public static final int PORT_COORDINATOR = 10004;
	public static final int PORT_POS         = 10005;
	// Reassigned off 20002 - that was colliding with PORT_ROTARYTABLE_VIZ,
	// so whichever GUI started second would fail to bind its live-event
	// port (BindException) and its display just wouldn't update.
	public static final int PORT_POS_VIZ     = 20004;

	// GUI order-form -> Pos clock domain
	public static final String POS_SUBMIT        = "PosCD.submit";

	// GUI order-form -> Coordinator clock domain (sent directly to avoid GALS timing races)
	public static final String COORD_LIQUID_A      = "CoordinatorCD.orderLiquidARatio";
	public static final String COORD_TARGET_VOLUME = "CoordinatorCD.orderTargetVolume";
	public static final String COORD_QUANTITY      = "CoordinatorCD.orderQuantity";
	
}