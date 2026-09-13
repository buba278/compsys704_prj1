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
	public static final int PORT_ROTARYTABLE_CONTROLLER = 10004;
	public static final int PORT_ROTARYTABLE_PLANT      = 10005;
	public static final int PORT_ROTARYTABLE_VIZ        = 20002;
 
	// real controller inputs
	public static final String ROTARYTABLE_MODE             = "RotaryTableControllerCD.mode";
	public static final String ROTARYTABLE_ROTATE_M         = "RotaryTableControllerCD.rotateM";
 
	// for debug: stand in for signals that will come from Conveyor/Sorting once those exist
	public static final String ROTARYTABLE_READY_TO_ROTATE              = "RotaryTableControllerCD.readyToRotate";
	public static final String ROTARYTABLE_BOTTLE_AT_POS5_TOGGLE        = "RotaryTablePlantCD.bottleAtPos5Toggle";
	public static final String ROTARYTABLE_CAP_ON_BOTTLE_AT_POS1_TOGGLE = "RotaryTablePlantCD.capOnBottleAtPos1Toggle";
 
	// kicks the plant's sensor loop off (it sits at await(start) until this fires)
	public static final String ROTARYTABLE_START = "RotaryTablePlantCD.start";
}
