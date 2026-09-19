package machines.rotarytable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

public class RotaryTable extends JFrame {

	public RotaryTable() {
		RotaryTableCanvas canvas = new RotaryTableCanvas();
		canvas.setPreferredSize(new Dimension(300, 400));
		canvas.setBackground(Color.WHITE);

		// --- controller inputs: mode + manual jog ---
		
		final SignalRadioClient modeClient = new SignalRadioClient(Ports.PORT_ROTARYTABLE_CONTROLLER, Ports.ROTARYTABLE_MODE);
		modeClient.setCheckBoxComponent(new JPanel());
		final JRadioButton autoMode = new JRadioButton("Auto");
		autoMode.setActionCommand("0");
		final JRadioButton manualMode = new JRadioButton("Manual");
		manualMode.setActionCommand("1");
		autoMode.addActionListener(modeClient);
		manualMode.addActionListener(modeClient);
		autoMode.setSelected(true); // matches the controller's own default (currentMode = 0)
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(autoMode);
		modeGroup.add(manualMode);

		// rotateM: jog only while held down, press sends true, release sends false
		final SignalLevelClient rotateMClient = new SignalLevelClient(Ports.PORT_ROTARYTABLE_CONTROLLER, Ports.ROTARYTABLE_ROTATE_M);
		final JButton rotateButton = new JButton("Hold to jog rotate");
		rotateButton.setEnabled(false);
		rotateButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				rotateMClient.send(true);
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				rotateMClient.send(false);
			}
		});
		java.awt.event.ActionListener jogGate = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				rotateButton.setEnabled(manualMode.isSelected());
			}
		};
		autoMode.addActionListener(jogGate);
		manualMode.addActionListener(jogGate);

		JPanel modePanel = new JPanel();
		modePanel.setBorder(BorderFactory.createTitledBorder("Mode selector"));
		modePanel.add(autoMode);
		modePanel.add(manualMode);
		modePanel.add(rotateButton);

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		this.add(canvas, c);
		c.gridy = 1;
		this.add(modePanel, c);

		this.setTitle("Rotary Table");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		org.compsys704.WindowTile.place(this, "rotarytable");
		this.setResizable(false);
	}

	public static void main(String[] args) {
		RotaryTable frame = new RotaryTable();
		frame.pack();
		frame.setVisible(true);

		// the plant sits at await(start) until this fires, so kick it off once at launch
		new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_START).send(true);

		SignalServer<RotaryTableVizWorker> server = new SignalServer<RotaryTableVizWorker>(Ports.PORT_ROTARYTABLE_VIZ, RotaryTableVizWorker.class);
		new Thread(server).start();

		while (true) {
			try {
				frame.repaint();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}