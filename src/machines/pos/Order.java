package machines.pos;

import java.io.Serializable;

// Serializable so OrderQueue can mirror it as-is between the individual Pos window
// (the hub) and the Big-Picture window (a client) - see OrderQueue/OrderSyncMessage.
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status { QUEUED, IN_PROGRESS, DONE }

    public int number; // assigned by OrderQueue's hub on enqueue, not by the submitting window
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
