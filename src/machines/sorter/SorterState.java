package machines.sorter;

public class SorterState {
    public static volatile boolean BOTTLE_PRESENT = false;
    public static volatile boolean BOTTLE_DEFECTIVE = false;
    public static volatile boolean PUSHER_EXTENDED = false;
    public static volatile boolean SORTED = false;
    public static volatile boolean REJECTED = false;
    public static volatile boolean MANUAL_MODE = false;
    public static volatile int SORTED_COUNT = 0;
    public static volatile int REJECTED_COUNT = 0;

    // bottleAtSorterE/BigE clears the instant the Sorter acks the Labeller's handoff
    // (see sorterController.sysj), well before the sort/reject decision and pusher
    // animation actually finish - clearing BOTTLE_PRESENT that fast made the bottle
    // vanish from the GUI mid-decision. This lingers it visible a bit longer instead,
    // cancelling the pending clear if a new bottle arrives first.
    private static volatile long pendingClearId = 0;
    private static final long LINGER_MS = 600;

    public static void setBottlePresent(boolean present) {
        if (present) {
            pendingClearId++;
            BOTTLE_PRESENT = true;
        } else {
            final long id = ++pendingClearId;
            Thread t = new Thread(() -> {
                try { Thread.sleep(LINGER_MS); } catch (InterruptedException ignored) {}
                if (pendingClearId == id) BOTTLE_PRESENT = false;
            });
            t.setDaemon(true);
            t.start();
        }
    }
}