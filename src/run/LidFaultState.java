package run;

/** Fault-injection and fault-state flags for the Lid Placer, mirroring
 *  CapperFaultState/FillerFaultState - plain Java fields rather than
 *  SystemJ signals/latches by design (see CLAUDE.md, "abort
 *  entry-precondition pitfall"). Only touched from within ControllerCD's
 *  own process (the original Lab-3 pick-and-place cell, repurposed as the
 *  Lid Placer).
 *
 *  Like the Capper, the Lid Placer acts on a discrete physical object (a
 *  lid) that can be knocked loose mid-transfer, so a single failed grip
 *  attempt isn't necessarily a real fault - the controller retries a fixed
 *  number of times first. beginAttempt/endAttempt let a separate monitor
 *  thread detect a single attempt hanging (a per-attempt timeout),
 *  independent of the overall retry count the controller's own main
 *  thread tracks in a plain local variable.
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

    // Manual fault injection (plant-side, mirrors FillerFaultState's
    // overfillArmed/stallArmed and CapperFaultState's jam): "Drop Lid"
    // simulates a lid slipping out of the vacuum grip before it's ever
    // achieved, so a real placement attempt genuinely times out and the
    // controller's own retry/detection logic does the work, rather than
    // the GUI just faking the end state. Auto-expires rather than needing
    // an explicit cross-process clear signal (this flag lives in PlantCD's
    // own copy, a different process from ControllerCD's stationFaulted
    // above - Java statics don't cross clock-domain processes, see
    // CLAUDE.md) - 30s comfortably covers all 3 retry attempts at an 8s
    // timeout each.
    private static volatile long dropArmedAt = 0;
    private static final long DROP_DURATION_MS = 30000;

    public static void armDrop() { dropArmedAt = System.currentTimeMillis(); }
    public static boolean isDropArmed() {
        return dropArmedAt != 0 && System.currentTimeMillis() - dropArmedAt < DROP_DURATION_MS;
    }
}
