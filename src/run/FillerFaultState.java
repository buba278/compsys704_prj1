package run;

/** Fault-injection and fault-state flags for the Filler. Plain Java fields rather than
 *  SystemJ signals/latches by design, see CLAUDE.md ("abort entry-precondition pitfall").
 *  This class is imported by both FillerPlantCD and FillerControllerCD, which are separate
 *  processes, so each gets its own independent copy of these fields (Java statics are
 *  per-JVM). overfillArmed/stallArmed (plant-side) and stationFaulted (controller-side)
 *  never collide despite living in the same source file.
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
}
