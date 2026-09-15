package machines.pos;

import javax.swing.JFrame;

import org.compsys704.Ports;
import org.compsys704.SignalServer;

public class PosPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    private final PosFormPanel formPanel = new PosFormPanel();

    public PosPanel() {
        this.add(formPanel);
        this.setTitle("Purchase Order System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        org.compsys704.WindowTile.place(this, "pos");
        this.setResizable(false);
    }

    public static void main(String[] args) {
        // This window is the order-queue hub - see OrderQueue's class comment. It's the
        // only process wired to the Coordinator's real batchDone signal, and the only
        // one that actually dispatches submitted orders to the Coordinator.
        OrderQueue.startAsHub();

        PosPanel panel = new PosPanel();
        panel.pack();
        panel.setVisible(true);

        SignalServer<PosVizWorker> server =
                new SignalServer<PosVizWorker>(Ports.PORT_POS_VIZ, PosVizWorker.class);
        new Thread(server).start();

        while (true) {
            try {
                panel.formPanel.refresh();
                panel.repaint();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
