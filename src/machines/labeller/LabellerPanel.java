package machines.labeller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import org.compsys704.Ports;
import org.compsys704.SignalCheckBoxClient;
import org.compsys704.SignalRadioClient;
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
        c.gridx = 0; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL;
        this.add(org.compsys704.StationHeader.make("STATION 7 - LABELLER"), c);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        this.add(canvas, c);

        // Mode selector + manual control, same pattern as the Lid Placer (CapLoader).
        SignalRadioClient src = new SignalRadioClient(Ports.PORT_LABELLER_CONTROLLER, Ports.LABELLER_MODE);
        JRadioButton mmode = new JRadioButton("Manual");
        mmode.setActionCommand("1");
        mmode.addActionListener(src);
        JRadioButton amode = new JRadioButton("Auto");
        amode.setActionCommand("0");
        amode.addActionListener(src);
        amode.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(mmode);
        bg.add(amode);

        JPanel modePanel = new JPanel(new GridLayout(1, 0));
        modePanel.add(amode);
        modePanel.add(mmode);
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode selector"));

        JCheckBox printLabel = new JCheckBox("printLabel");
        printLabel.setEnabled(false);
        printLabel.addItemListener(new SignalCheckBoxClient(Ports.PORT_LABELLER_CONTROLLER, Ports.LABELLER_PRINT_LABEL_M));
        JCheckBox clampBottle = new JCheckBox("clampBottle");
        clampBottle.setEnabled(false);
        clampBottle.addItemListener(new SignalCheckBoxClient(Ports.PORT_LABELLER_CONTROLLER, Ports.LABELLER_CLAMP_BOTTLE_M));
        JCheckBox applyLabel = new JCheckBox("applyLabel");
        applyLabel.setEnabled(false);
        applyLabel.addItemListener(new SignalCheckBoxClient(Ports.PORT_LABELLER_CONTROLLER, Ports.LABELLER_APPLY_LABEL_M));

        JPanel manualPanel = new JPanel(new GridLayout(1, 3));
        manualPanel.add(printLabel);
        manualPanel.add(clampBottle);
        manualPanel.add(applyLabel);
        manualPanel.setBorder(BorderFactory.createTitledBorder("Manual control"));
        src.setCheckBoxComponent(manualPanel);

        JPanel controlPanel = new JPanel(new GridLayout(0, 2));
        controlPanel.add(modePanel);
        controlPanel.add(manualPanel);
        c.gridy = 2;
        this.add(controlPanel, c);

        this.setTitle("Labeller Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        org.compsys704.WindowTile.place(this, "labeller");
        this.setResizable(false);
    }

}
