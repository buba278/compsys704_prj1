package run;

/** Shared per-order counters for the Coordinator's production/sort-outcome
 *  loops. A plain SystemJ local variable declared before a set of `||`
 *  threads is NOT actually shared between them - sjc generates a separate
 *  per-thread copy of it, so writes in one thread are invisible to the
 *  others (confirmed by a compile failure: "cannot find symbol" for such a
 *  variable referenced from a sibling thread). Cross-thread state within
 *  one clock domain has to go through a plain Java class instead, same as
 *  RotaryTableState/FillerFaultState/CapperFaultState - safe because
 *  SystemJ threads within one domain run cooperatively, one at a time per
 *  reaction, not as real concurrent OS threads (see CLAUDE.md).
 */
public class CoordinatorBatchState {
    private static volatile int neededTotal = 0;
    private static volatile int started = 0;
    private static volatile int goodSorted = 0;

    public static void startOrder(int quantity) {
        neededTotal = quantity;
        started = 0;
        goodSorted = 0;
    }

    public static int getNeededTotal() { return neededTotal; }
    public static int getStarted() { return started; }
    public static int getGoodSorted() { return goodSorted; }

    public static int incrementStarted() { return ++started; }
    public static int incrementGoodSorted() { return ++goodSorted; }
    public static int addNeeded(int extra) { return neededTotal += extra; }
}
