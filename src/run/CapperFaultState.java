package run;

/** Fault-injection/fault-state flags for the Capper (mirrors FillerFaultState); plain Java fields, not signals, to avoid the abort entry-precondition pitfall.
 *  Only touched from CapperControllerCD's own process. beginAttempt/endAttempt let a separate monitor detect one attempt hanging, independent of the retry count.
 */
public class CapperFaultState {
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

    // Manual fault injection (plant-side, mirrors FillerFaultState) - auto-expires rather than needing a cross-process clear signal, since this static lives in CapperPlantCD's own process. A stall now faults after a single attempt (no retries), so this just needs to comfortably cover the one 8s attempt timeout.
    private static volatile long stallArmedAt = 0;
    private static final long STALL_DURATION_MS = 10000;

    public static void armStall() { stallArmedAt = System.currentTimeMillis(); }
    public static boolean isStallArmed() {
        return stallArmedAt != 0 && System.currentTimeMillis() - stallArmedAt < STALL_DURATION_MS;
    }
}
