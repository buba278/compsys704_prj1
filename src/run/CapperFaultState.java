package run;

/** Fault-injection and fault-state flags for the Capper, mirroring
 *  FillerFaultState - plain Java fields rather than SystemJ signals/latches
 *  by design (see CLAUDE.md, "abort entry-precondition pitfall"). Only
 *  touched from within CapperControllerCD's own process.
 *
 *  Unlike the Filler (a continuous stall/overfill condition), the Capper
 *  acts on a discrete physical object that can be knocked loose mid-action,
 *  so a single failed attempt isn't necessarily a real fault - the
 *  controller retries a fixed number of times first. beginAttempt/endAttempt
 *  let a separate monitor thread detect a single attempt hanging (a
 *  per-attempt timeout), independent of the overall retry count the
 *  controller's own main thread tracks in a plain local variable.
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

    // Manual fault injection (plant-side, mirrors FillerFaultState's
    // overfillArmed/stallArmed): "Jam Twist" simulates a cap knocked loose
    // before gripperFullTwist is ever reached, so a real capping attempt
    // genuinely times out and the controller's own retry/detection logic
    // does the work, rather than the GUI just faking the end state. Auto-
    // expires rather than needing an explicit cross-process clear signal
    // (this flag lives in CapperPlantCD's own copy, a different process
    // from CapperControllerCD's stationFaulted above - Java statics don't
    // cross clock-domain processes, see CLAUDE.md) - 30s comfortably
    // covers all 3 retry attempts at an 8s timeout each.
    private static volatile long jamArmedAt = 0;
    private static final long JAM_DURATION_MS = 30000;

    public static void armJam() { jamArmedAt = System.currentTimeMillis(); }
    public static boolean isJamArmed() {
        return jamArmedAt != 0 && System.currentTimeMillis() - jamArmedAt < JAM_DURATION_MS;
    }
}
