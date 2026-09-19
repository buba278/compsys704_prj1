package org.compsys704;

public class Ports {
	public static final String REQUEST_SIGNAL = "ControllerCD.request";
	public static final String REFILL_SIGNAL = "PlantCD.refill";
	public static final String ENABLE_SIGNAL = "PlantCD.enable";
	public static final int PORT_LOADER_PLANT = 10001;
	public static final int PORT_LOADER_CONTROLLER = 10000;
	public static final int PORT_LOADER_VIZ = 20000;
	public static final int PORT_LOADER_BIGPICTURE_VIZ = 20013;

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
	public static final int PORT_FILLER_BIGPICTURE_VIZ = 20011;

	public static final String FILLER_BOTTLE_AT_POS2_CONTROLLER = "FillerControllerCD.bottleAtPos2";
	public static final String FILLER_BOTTLE_AT_POS2_PLANT      = "FillerPlantCD.bottleAtPos2";
	public static final String FILLER_LIQUID_A_RATIO            = "FillerControllerCD.liquidARatio";
	public static final String FILLER_TARGET_VOLUME_ML          = "FillerControllerCD.targetVolumeMl";

	// === ROTARY TABLE ===
	public static final int PORT_ROTARYTABLE_CONTROLLER = 10016;
	public static final int PORT_ROTARYTABLE_PLANT      = 10017;
	public static final int PORT_ROTARYTABLE_VIZ        = 20002;
	public static final int PORT_ROTARYTABLE_BIGPICTURE_VIZ = 20007;

	public static final String ROTARYTABLE_MODE             = "RotaryTableControllerCD.mode";
	public static final String ROTARYTABLE_ROTATE_M         = "RotaryTableControllerCD.rotateM";

	public static final String ROTARYTABLE_READY_TO_ROTATE              = "RotaryTableControllerCD.readyToRotate";

	public static final String ROTARYTABLE_START = "RotaryTablePlantCD.start";

	public static final String ROTARYTABLE_FILLER_TAKEN_ACK       = "RotaryTablePlantCD.fillerTakenAck";
	public static final String COORDINATOR_BOTTLE_READY_FOR_FILLER = "CoordinatorCD.bottleReadyForFiller";

	public static final String ROTARYTABLE_FILLER_FAULTED = "RotaryTablePlantCD.fillerFaulted";
	public static final String ROTARYTABLE_LID_FAULTED    = "RotaryTablePlantCD.lidFaulted";
	public static final String ROTARYTABLE_CAPPER_FAULTED = "RotaryTablePlantCD.capperFaulted";

	public static final String COORDINATOR_BOTTLES_NEEDED_ACK = "CoordinatorCD.bottlesNeededAck";

	public static final String ROTARYTABLE_CONVEYOR_TAKEN_ACK      = "RotaryTablePlantCD.conveyorTakenAck";

	public static final String CONVEYOR_CONTROLLER_POS1_TAKEN_ACK = "ConveyorControllerCD.pos1TakenAck";
	public static final String CONVEYOR_PLANT_POS1_TAKEN_ACK      = "ConveyorPlantCD.pos1TakenAck";

	// === CONVEYOR ===
	public static final int PORT_CONVEYOR_CONTROLLER = 10006;
	public static final int PORT_CONVEYOR_PLANT      = 10007;
	public static final int PORT_CONVEYOR_VIZ        = 20003;
	public static final int PORT_CONVEYOR_BIGPICTURE_VIZ = 20006;
 
	public static final String CONVEYOR_MODE       = "ConveyorControllerCD.mode";
	public static final String CONVEYOR_CONVEYOR_M = "ConveyorControllerCD.conveyorM";
 
	public static final String CONVEYOR_ENABLE            = "ConveyorPlantCD.enable";
	public static final String CONVEYOR_LOAD_BOTTLE       = "ConveyorPlantCD.loadBottle";
	public static final String CONVEYOR_BOTTLE_FROM_TABLE = "ConveyorPlantCD.bottleFromTable";
	public static final String CONVEYOR_BOTTLE_DEFECTIVE_FROM_TABLE = "ConveyorPlantCD.bottleDefectiveFromTable";

	// fault tolerance: manual fault injection from the Filler GUI
	public static final String FILLER_OVERFILL_M                = "FillerPlantCD.overfillM";
	public static final String FILLER_STALL_M                   = "FillerPlantCD.stallM";
	// fault tolerance: operator clears an active fault - goes to the controller (where the
	// dosing/abandon-bottle decision lives), not the plant
	public static final String FILLER_CLEAR_FAULT_M             = "FillerControllerCD.clearFaultM";

	// === CAPPER ===
	public static final int PORT_CAPPER_CONTROLLER = 10010;
	public static final int PORT_CAPPER_PLANT      = 10011;
	
	public static final int PORT_CAPPER_BIGPICTURE_VIZ = 20012;

	public static final String CAPPER_BOTTLE_AT_POS4            = "CapperPlantCD.bottleAtPos4";
	public static final String CAPPER_BOTTLE_AT_POS4_CONTROLLER = "CapperControllerCD.bottleAtPos4";
	public static final String ROTARYTABLE_CAPPER_TAKEN_ACK     = "RotaryTablePlantCD.capperTakenAck";

	public static final String LID_PLACED_ACK = "RotaryTablePlantCD.lidPlacedAck";

	public static final String CAPPER_STALL_M       = "CapperPlantCD.capperStallM";
	public static final String CAPPER_CLEAR_FAULT_M = "CapperControllerCD.clearCapperFaultM";

	// Manual mode
	public static final String CAPPER_MODE                   = "CapperControllerCD.mode";
	public static final String CAPPER_SEND_GRIPPER_DOWN_M    = "CapperControllerCD.sendGripperDownM";
	public static final String CAPPER_SEND_GRIPPER_TWIST_M   = "CapperControllerCD.sendGripperTwistM";
	public static final String CAPPER_SEND_GRIPPER_UNTWIST_M = "CapperControllerCD.sendGripperUntwistM";
	public static final String CAPPER_SEND_GRIP_CAP_M        = "CapperControllerCD.sendGripCapM";
	public static final String CAPPER_SEND_CLAMP_M           = "CapperControllerCD.sendClampM";

	// fault tolerance: manual fault injection / clear from the Lid Placer GUI.
	public static final String LOADER_DROP_LID_M    = "PlantCD.dropLidM";
	public static final String LOADER_CLEAR_FAULT_M = "ControllerCD.clearLidFaultM";

	// === LABELLER ===
	public static final int PORT_LABELLER_CONTROLLER = 10008;
	public static final int PORT_LABELLER_PLANT      = 10009;
	public static final int PORT_LABELLER_BIGPICTURE_VIZ = 20008;

	// Conveyor <-> Labeller handoff (collection point)
	public static final String LABELLER_BOTTLE_FROM_CONVEYOR = "LabellerPlantCD.bottleFromConveyor";
	public static final String CONVEYOR_LABELLER_TAKEN_ACK   = "ConveyorPlantCD.labellerTakenAck";
	// Carries whether this bottle was abandoned upstream (unfilled/no lid/
	// uncapped) - see rotaryTablePlant.sysj/conveyorPlant.sysj.
	public static final String LABELLER_BOTTLE_DEFECTIVE_FROM_CONVEYOR = "LabellerPlantCD.bottleDefectiveFromConveyor";

	// Labeller <-> Sorter handoff
	public static final String SORTER_BOTTLE_FROM_LABELLER = "SorterPlantCD.bottleFromLabeller";
	public static final String LABELLER_SORTER_TAKEN_ACK   = "LabellerPlantCD.sorterTakenAck";
	public static final String SORTER_BOTTLE_DEFECTIVE_FROM_LABELLER = "SorterPlantCD.bottleDefectiveFromLabeller";

	// Manual mode
	public static final String LABELLER_MODE            = "LabellerControllerCD.mode";
	public static final String LABELLER_PRINT_LABEL_M   = "LabellerControllerCD.printLabelM";
	public static final String LABELLER_CLAMP_BOTTLE_M  = "LabellerControllerCD.clampBottleM";
	public static final String LABELLER_APPLY_LABEL_M   = "LabellerControllerCD.applyLabelM";

	// === SORTER ===
	public static final int PORT_SORTER_CONTROLLER = 10025;
	public static final int PORT_SORTER_PLANT      = 10023;
	public static final int PORT_SORTER_BIGPICTURE_VIZ = 20009;

	// === COORDINATOR + POS ===
	public static final int PORT_COORDINATOR = 10004;
	public static final int PORT_POS         = 10005;
	public static final int PORT_POS_VIZ     = 20004;

	public static final int PORT_POS_QUEUE_SYNC = 20010;
	public static final int PORT_COORDINATOR_VIZ = 20005;

	// GUI order-form -> Pos clock domain
	public static final String POS_SUBMIT        = "PosCD.submit";

	// GUI order-form -> Coordinator clock domain (sent directly to avoid GALS timing races)
	public static final String COORD_LIQUID_A      = "CoordinatorCD.orderLiquidARatio";
	public static final String COORD_TARGET_VOLUME = "CoordinatorCD.orderTargetVolume";
	public static final String COORD_QUANTITY      = "CoordinatorCD.orderQuantity";
	
}