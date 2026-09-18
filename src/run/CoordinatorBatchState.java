package run;

/** Shared per-order counters for the Coordinator's production/sort-outcome loops.
 *  A SystemJ local declared before a `||` split gets a separate per-thread copy, not one shared variable - cross-thread state needs a plain Java class instead.
 */
public class CoordinatorBatchState {
    private static volatile int neededTotal = 0;
    private static volatile int started = 0;
    private static volatile int goodSorted = 0;

    // How many bottles have actually been requested from the Conveyor's loader vs. neededTotal - addNeeded() can push neededTotal ahead of this; a coordinator.sysj thread polls takePendingConveyorRequest() to catch it up.
    private static volatile int requestedFromConveyor = 0;

    public static void startOrder(int quantity) {
        neededTotal = quantity;
        started = 0;
        goodSorted = 0;
        requestedFromConveyor = quantity;
    }

    public static int getNeededTotal() { return neededTotal; }
    public static int getStarted() { return started; }
    public static int getGoodSorted() { return goodSorted; }

    public static int incrementStarted() { return ++started; }
    public static int incrementGoodSorted() { return ++goodSorted; }
    public static int addNeeded(int extra) { return neededTotal += extra; }

    /** Returns how many bottles are needed but not yet requested from the
     *  Conveyor, and marks them as requested in the same call (so a caller
     *  that gets a positive count is the one committed to actually
     *  requesting them). */
    public static int takePendingConveyorRequest() {
        int pending = neededTotal - requestedFromConveyor;
        if (pending > 0) {
            requestedFromConveyor = neededTotal;
        }
        return pending;
    }
}
