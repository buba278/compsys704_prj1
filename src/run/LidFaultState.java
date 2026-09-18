package run;

/** Fault-injection/fault-state flags for the Lid Placer (mirrors CapperFaultState/FillerFaultState); plain Java fields, not signals, to avoid the abort entry-precondition pitfall.
 *  Only touched from within ControllerCD's own process. beginAttempt/endAttempt let a separate monitor detect one attempt hanging, independent of the retry count.
 */
public class LidFaultState {
    private static volatile boolean stationFaulted = false;
    private static volatile boolean attemptInProgress = false;
    private static volatile long attemptStart = 0;

    public static void beginAttempt() {
        attemptStart = System.currentTimeMillis();
        attemptInProgress = true;
    }

    public static void endAttempt() {
        attemptInProgress = false;
    }

    public static boolean isAttemptTimedOut(long timeoutMs) {
        return attemptInProgress && (System.currentTimeMillis() - attemptStart > timeoutMs);
    }

    public static void raiseStationFault() { stationFaulted = true; }
    public static void clearStationFault() { stationFaulted = false; }
    public static boolean isStationFaulted() { return stationFaulted; }

    // Set once retries are exhausted and the physical backup unit takes over; stays true across bottles (only one fault is assumed to happen at a time).
    private static volatile boolean usingBackup = false;

    public static void enterBackupMode() { usingBackup = true; }
    public static void exitBackupMode()  { usingBackup = false; }
    public static boolean isUsingBackup() { return usingBackup; }

    // Manual fault injection (plant-side, mirrors FillerFaultState/CapperFaultState) - auto-expires rather than needing a cross-process clear signal, since this static lives in PlantCD's own process. 30s comfortably covers all 3 retries at 8s each.
    private static volatile long dropArmedAt = 0;
    private static final long DROP_DURATION_MS = 30000;

    public static void armDrop() { dropArmedAt = System.currentTimeMillis(); }
    public static boolean isDropArmed() {
        return dropArmedAt != 0 && System.currentTimeMillis() - dropArmedAt < DROP_DURATION_MS;
    }
}
