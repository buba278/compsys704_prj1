package machines.sorter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import org.compsys704.SignalServer;

public class SorterPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    public SorterPanel() {
        SorterCanvas canvas = new SorterCanvas();
        canvas.setPreferredSize(new Dimension(320, 320));
        canvas.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(canvas, c);

        this.setTitle("Sorter Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        SorterPanel panel = new SorterPanel();
        panel.pack();
        panel.setVisible(true);

        // Listens for Sorter Plant viz signals on Port 10030
        SignalServer<SorterVizWorker> server = new SignalServer<>(10030, SorterVizWorker.class);
        new Thread(server).start();

        while (true) {
            try {
                panel.repaint();
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}