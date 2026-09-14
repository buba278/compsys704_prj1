package machines.labeller;
 
public class LabellerState {
	public static volatile boolean BOTTLE_PRESENT = false;
    public static volatile boolean CLAMPED = false;
    public static volatile boolean LABEL_PRINTED = false;
    public static volatile boolean LABEL_APPLIED = false;
 
    public static volatile int LABEL_COUNTER = 0;
    public static volatile String CURRENT_LABEL_ID = "----";

    // Driven by the Coordinator's liquidARatioE/targetVolumeMlE (see
    // labellerPlant.sysj) - the same recipe the Filler actually put in this
    // bottle, so the drawing matches what's really inside it.
    public static volatile int BOTTLE_SIZE_ML = 330;
    public static volatile int LIQUID_RATIO = 50;
}

