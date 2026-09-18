package run;

/** Fault-injection/fault-state flags for the Filler; plain Java fields, not signals, to avoid the abort entry-precondition pitfall.
 *  Imported by both FillerPlantCD and FillerControllerCD as separate processes, so each gets its own independent copy (Java statics are per-JVM).
 */
public class FillerFaultState {
    private static volatile boolean overfillArmed = false;
    private static volatile boolean stallArmed = false;

    public static void armOverfill()   { overfillArmed = true; }
    public static void clearOverfill() { overfillArmed = false; }
    public static boolean isOverfillArmed() { return overfillArmed; }

    public static void armStall()   { stallArmed = true; }
    public static void clearStall() { stallArmed = false; }
    public static boolean isStallArmed() { return stallArmed; }

    private static volatile boolean stationFaulted = false;

    public static void raiseStationFault() { stationFaulted = true; }
    public static void clearStationFault() { stationFaulted = false; }
    public static boolean isStationFaulted() { return stationFaulted; }

    // Set once recovery is exhausted and the physical backup unit takes over; stays true across bottles (only one fault is assumed to happen at a time).
    private static volatile boolean usingBackup = false;

    public static void enterBackupMode() { usingBackup = true; }
    public static void exitBackupMode()  { usingBackup = false; }
    public static boolean isUsingBackup() { return usingBackup; }
}
