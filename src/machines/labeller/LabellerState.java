package machines.labeller;
 
public class LabellerState {
	public static volatile boolean BOTTLE_PRESENT = false;
    public static volatile boolean CLAMPED = false;
    public static volatile boolean LABEL_PRINTED = false;
    public static volatile boolean LABEL_APPLIED = false;
 
    // --- Temporary/placeholder label data ---
    // Real values should eventually come from the (currently unused/commented-out)
    // liquidARatio / targetVolumeMl int signals. Hardcoded for now per request.
    public static volatile int LABEL_COUNTER = 0;
    public static volatile String CURRENT_LABEL_ID = "----";
    public static volatile int BOTTLE_SIZE_ML = 500;
    public static volatile int LIQUID_RATIO = 100;
}

