package run;

/** Holds manual fault-injection flags set by the Filler GUI.
 *  A pure-SystemJ latch (await(overfillM); abort(!bottleAtPos2){ sustain overfilling; })
 *  was tried first and is not just a theoretical race: tested in isolation, pressing the
 *  button before the bottle arrives means !bottleAtPos2 is already true the instant the
 *  abort block is entered, so it tears down after a single tick and the flag never holds
 *  for the fill that follows. Fixable by restructuring so the abort condition is
 *  guaranteed false at entry (as levelAtTarget/fillReady do elsewhere in this codebase),
 *  but that requires controlling the relative order of two independently-timed events
 *  (button press vs. bottle arrival) that this GUI does not control. A plain volatile
 *  field has no entry precondition to get wrong.
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

    // stationFaulted is read/written from FillerControllerCD's own process only (the dosing
    // decision lives there), unlike the manual-override flags above which belong to
    // FillerPlantCD. Java static fields are per-JVM: even though FillerControllerCD.sysj
    // imports this exact same class, each clock domain's process gets its own independent
    // copy of these fields, never shared across the two - there is no cross-process aliasing
    // to worry about here.
    private static volatile boolean stationFaulted = false;

    public static void raiseStationFault() { stationFaulted = true; }
    public static void clearStationFault() { stationFaulted = false; }
    public static boolean isStationFaulted() { return stationFaulted; }
}
