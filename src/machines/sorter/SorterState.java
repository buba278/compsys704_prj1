package machines.sorter;

public class SorterState {
    public static volatile boolean BOTTLE_PRESENT = false;
    public static volatile boolean BOTTLE_DEFECTIVE = false;
    public static volatile boolean PUSHER_EXTENDED = false;
    public static volatile boolean SORTED = false;
    public static volatile boolean REJECTED = false;
}