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
}