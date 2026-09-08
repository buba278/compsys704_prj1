package machines.pos;

import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.compsys704.Ports;

public class OrderQueue {

    private static final List<Order> orders  = new CopyOnWriteArrayList<>();
    private static final LinkedBlockingQueue<Order> pending = new LinkedBlockingQueue<>();
    private static volatile Order inProgress = null;

    static {
        Thread t = new Thread(OrderQueue::processLoop, "pos-queue-manager");
        t.setDaemon(true);
        t.start();
    }

    public static void enqueue(Order order) {
        orders.add(order);
        pending.add(order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    /** Called by PosVizWorker when batchDone arrives. */
    public static void markDone(int completionMs) {
        synchronized (OrderQueue.class) {
            Order o = inProgress;
            if (o != null) {
                o.completionMs = completionMs;
                o.status = Order.Status.DONE;
                inProgress = null;
            }
            OrderQueue.class.notifyAll();
        }
    }

    private static void processLoop() {
        while (true) {
            try {
                Order order = pending.take();
                inProgress = order;
                order.status = Order.Status.IN_PROGRESS;
                sendOrder(order);
                // wait until markDone clears inProgress
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
