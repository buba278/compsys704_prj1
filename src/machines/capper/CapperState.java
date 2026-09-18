package machines.capper;

public class CapperState {
	
	public static volatile boolean BOTTLE_PRESENT = true;
    
    // Clamp state (true = squares enclose bottle, false = retracted)
    public static volatile boolean CLAMPED = false;
    
    // Grip vertical position (0 = top resting position, 40 = lowered to cap)
    public static volatile int GRIP_HEIGHT = 0; 
    
    // Indicator for cap gripped
    public static volatile boolean GRIPPED = false;
    
    // Twist angle (0 to 270 degrees)
    public static volatile int TWIST_ANGLE = 0;

    // Fault indicator (see run.CapperFaultState / the IP report)
    public static volatile boolean FAULTED = false;

    // Persistent backup-unit-active indicator (see run.CapperFaultState)
    public static volatile boolean BACKUP_ACTIVE = false;

}
