package machines.pos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.compsys704.Ports;

/** The order queue is owned by exactly one process at a time - the individual Pos
 *  window (see startAsHub(), called from PosPanel.main()). That window is the only one
 *  wired to the Coordinator's real batchDone signal (via pos.sysj/PosCD), so it's the
 *  only process that can ever genuinely learn an order finished, and it's the one that
 *  actually drives submissions to the Coordinator (sendOrder() below, unchanged from
 *  before). Every other window showing the queue (the Big-Picture window - see
 *  startAsClient(), called from BigPicture.main()) mirrors this same list over a small
 *  socket protocol (OrderSyncMessage) instead of keeping a separate one, so submitting
 *  an order from either window and seeing the same queue in both just falls out of
 *  there being a single source of truth.
 */
public class OrderQueue {

    private static final List<Order> orders  = new CopyOnWriteArrayList<>();
    private static final LinkedBlockingQueue<Order> pending = new LinkedBlockingQueue<>();
    private static volatile Order inProgress = null;
    private static int orderCounter = 0;

    private static volatile boolean isHub = false;
    private static final List<ObjectOutputStream> hubClients = new CopyOnWriteArrayList<>();
    private static volatile ObjectOutputStream toHub;

    static {
        Thread t = new Thread(OrderQueue::processLoop, "pos-queue-manager");
        t.setDaemon(true);
        t.start();
    }

    /** Call once from the individual Pos window's main() - this process owns the
     *  canonical order list and assigns real order numbers. */
    public static void startAsHub() {
        isHub = true;
        Thread t = new Thread(OrderQueue::hubAcceptLoop, "pos-queue-hub");
        t.setDaemon(true);
        t.start();
    }

    /** Call once from a mirror window's main() (the Big-Picture window) - connects to
     *  the hub (retrying until it's up, in whichever order the two windows start) and
     *  mirrors its queue instead of keeping a separate one. */
    public static void startAsClient() {
        isHub = false;
        Thread t = new Thread(OrderQueue::clientConnectLoop, "pos-queue-client");
        t.setDaemon(true);
        t.start();
    }

    public static void enqueue(Order order) {
        if (isHub) {
            applyNewOrder(order);
        } else {
            OrderSyncMessage m = new OrderSyncMessage(OrderSyncMessage.Type.NEW_ORDER);
            m.order = order;
            sendToHub(m);
        }
    }

    public static List<Order> getOrders() {
        return orders;
    }

    /** Called by PosVizWorker when batchDone arrives - only ever happens in the
     *  process actually running PosCD, i.e. the hub (see class comment). */
    public static void markDone(int completionMs) {
        Order o = inProgress;
        if (o != null) {
            applyMarkDone(o.number, completionMs);
        }
    }

    // ------------------------------------------------------------------
    // Hub-side: canonical state changes, broadcast to mirrors
    // ------------------------------------------------------------------

    private static synchronized void applyNewOrder(Order order) {
        orderCounter++;
        order.number = orderCounter;
        orders.add(order);
        pending.add(order);

        OrderSyncMessage m = new OrderSyncMessage(OrderSyncMessage.Type.NEW_ORDER);
        m.order = order;
        broadcast(m);
    }

    private static synchronized void applyMarkDone(int orderNumber, int completionMs) {
        for (Order o : orders) {
            if (o.number == orderNumber) {
                o.completionMs = completionMs;
                o.status = Order.Status.DONE;
                break;
            }
        }
        if (inProgress != null && inProgress.number == orderNumber) {
            inProgress = null;
            synchronized (OrderQueue.class) {
                OrderQueue.class.notifyAll();
            }
        }

        OrderSyncMessage m = new OrderSyncMessage(OrderSyncMessage.Type.MARK_DONE);
        m.orderNumber = orderNumber;
        m.completionMs = completionMs;
        broadcast(m);
    }

    private static void broadcast(OrderSyncMessage m) {
        for (ObjectOutputStream out : hubClients) {
            try {
                out.reset(); // avoid resending stale cached field values for a mutated Order
                out.writeObject(m);
                out.flush();
            } catch (IOException e) {
                hubClients.remove(out);
            }
        }
    }

    private static void hubAcceptLoop() {
        try {
            ServerSocket ss = new ServerSocket(Ports.PORT_POS_QUEUE_SYNC, 10, InetAddress.getByName("127.0.0.1"));
            while (true) {
                Socket s = ss.accept();
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                hubClients.add(out);

                // catch the new client up on everything queued so far
                OrderSyncMessage snap = new OrderSyncMessage(OrderSyncMessage.Type.SNAPSHOT);
                snap.snapshot = new ArrayList<>(orders);
                out.writeObject(snap);
                out.flush();

                Thread reader = new Thread(() -> hubReadLoop(in, out));
                reader.setDaemon(true);
                reader.start();
            }
        } catch (IOException e) {
            System.err.println("[OrderQueue] hub accept loop failed: " + e);
        }
    }

    private static void hubReadLoop(ObjectInputStream in, ObjectOutputStream out) {
        try {
            while (true) {
                OrderSyncMessage m = (OrderSyncMessage) in.readObject();
                switch (m.type) {
                    case NEW_ORDER: applyNewOrder(m.order); break;
                    case MARK_DONE: applyMarkDone(m.orderNumber, m.completionMs); break;
                    default: break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            hubClients.remove(out);
        }
    }

    // ------------------------------------------------------------------
    // Client-side (Big Picture): forward local submissions, apply mirrored updates
    // ------------------------------------------------------------------

    private static void clientConnectLoop() {
        while (true) {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress("127.0.0.1", Ports.PORT_POS_QUEUE_SYNC), 1000);
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                toHub = out;
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                while (true) {
                    OrderSyncMessage m = (OrderSyncMessage) in.readObject();
                    applyMirrored(m);
                }
            } catch (Exception e) {
                toHub = null;
                try { Thread.sleep(1000); } catch (InterruptedException ignored) { return; }
            }
        }
    }

    private static void applyMirrored(OrderSyncMessage m) {
        switch (m.type) {
            case SNAPSHOT:
                orders.clear();
                orders.addAll(m.snapshot);
                break;
            case NEW_ORDER:
                orders.add(m.order);
                break;
            case MARK_DONE:
                for (Order o : orders) {
                    if (o.number == m.orderNumber) {
                        o.completionMs = m.completionMs;
                        o.status = Order.Status.DONE;
                        break;
                    }
                }
                break;
        }
    }

    private static void sendToHub(OrderSyncMessage m) {
        ObjectOutputStream out = toHub;
        if (out == null) {
            System.err.println("[OrderQueue] not connected to hub yet, dropping a " + m.type + " message");
            return;
        }
        try {
            out.reset();
            out.writeObject(m);
            out.flush();
        } catch (IOException e) {
            toHub = null;
        }
    }

    // ------------------------------------------------------------------
    // Hub-only: dispatch queued orders to the Coordinator (unchanged raw-socket sends -
    // these already work from any process, they just only ever run on the hub)
    // ------------------------------------------------------------------

    private static void processLoop() {
        while (true) {
            try {
                Order order = pending.take();
                inProgress = order;
                order.status = Order.Status.IN_PROGRESS;
                sendOrder(order);
                synchronized (OrderQueue.class) {
                    while (inProgress != null) {
                        OrderQueue.class.wait(200);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void sendOrder(Order order) {
        // Send each value signal to coordinator sequentially, closing each socket (with {false})
        // before opening the next. Holding multiple sockets open simultaneously blocks the
        // coordinator's SimpleServer from accepting the orderReady connection from pos.sysj.
        sendIntPulse(Ports.PORT_COORDINATOR, Ports.COORD_LIQUID_A,      order.ratioA);
        sendIntPulse(Ports.PORT_COORDINATOR, Ports.COORD_TARGET_VOLUME, order.volume);
        sendIntPulse(Ports.PORT_COORDINATOR, Ports.COORD_QUANTITY,      order.quantity);

        // All values delivered; now trigger submit so pos.sysj emits orderReady to coordinator.
        sendPulse(Ports.PORT_POS, Ports.POS_SUBMIT);
        System.out.printf("[OrderQueue] submitted ratioA=%d vol=%d qty=%d%n",
                order.ratioA, order.volume, order.quantity);
    }

    /** Sends an integer-valued signal pulse: {true, value} then {false}, then closes. */
    private static void sendIntPulse(int port, String dest, int value) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress("127.0.0.1", port), 500);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(dest);
            if (s.getInputStream().read() < 0) return;
            oos.writeObject(new Object[]{Boolean.TRUE, Integer.valueOf(value)});
            Thread.sleep(60);
            oos.writeObject(new Object[]{Boolean.FALSE});
        } catch (Exception e) {
            System.err.println("[OrderQueue] sendIntPulse failed (" + dest + "): " + e);
        }
    }

    private static void sendPulse(int port, String dest) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress("127.0.0.1", port), 500);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(dest);
            if (s.getInputStream().read() < 0) return;
            oos.writeObject(new Object[]{Boolean.TRUE});
            Thread.sleep(60);
            oos.writeObject(new Object[]{Boolean.FALSE});
        } catch (Exception e) {
            System.err.println("[OrderQueue] sendPulse failed (" + dest + "): " + e);
        }
    }
}
