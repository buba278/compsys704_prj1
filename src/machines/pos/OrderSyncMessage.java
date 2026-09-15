package machines.pos;

import java.io.Serializable;
import java.util.List;

// Wire format for OrderQueue's hub/client sync socket - see OrderQueue for the protocol.
class OrderSyncMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    enum Type { NEW_ORDER, MARK_DONE, SNAPSHOT }

    final Type type;
    Order order;             // NEW_ORDER
    int orderNumber;         // MARK_DONE
    int completionMs;        // MARK_DONE
    List<Order> snapshot;    // SNAPSHOT - sent once when a client first connects

    OrderSyncMessage(Type type) {
        this.type = type;
    }
}
