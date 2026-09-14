package machines.labeller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import org.compsys704.SignalServer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LabellerPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LabellerPanel panel = new LabellerPanel();
        panel.pack();
        panel.setVisible(true);

        // Map to port 10020
        SignalServer<LabellerVizWorker> server = new SignalServer<>(10020, LabellerVizWorker.class);
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

	/**
	 * Create the frame.
	 */
	
	public LabellerPanel() {
        LabellerCanvas canvas = new LabellerCanvas();
        canvas.setPreferredSize(new Dimension(300, 300));
        canvas.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        this.add(canvas, c);

        this.setTitle("Labeller Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

}
