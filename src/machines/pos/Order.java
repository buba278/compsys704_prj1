package machines.pos;

public class Order {
    public enum Status { QUEUED, IN_PROGRESS, DONE }

    public int number; // set by PosPanel on enqueue
    public final int ratioA;
    public final int volume;
    public final int quantity;
    public volatile Status status = Status.QUEUED;
    public volatile int completionMs = -1;

    public Order(int ratioA, int volume, int quantity) {
        this.ratioA   = ratioA;
        this.volume   = volume;
        this.quantity = quantity;
    }
}
