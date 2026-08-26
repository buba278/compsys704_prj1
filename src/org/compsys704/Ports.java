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
}
