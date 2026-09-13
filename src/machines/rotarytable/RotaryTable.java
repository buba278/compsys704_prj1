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

		// --- DEV stand-ins for stations not yet wired in (Conveyor / Sorting) ---
		// readyToRotate, bottleAtPos5 and capOnBottleAtPos1 are real controller/plant
		// inputs that will eventually be driven by the Conveyor and Sorting stations;
		// until those exist these radio pairs let us drive them by hand.

		final SignalLevelClient readyClient = new SignalLevelClient(Ports.PORT_ROTARYTABLE_CONTROLLER, Ports.ROTARYTABLE_READY_TO_ROTATE);
		JRadioButton notReady = new JRadioButton("not ready");
		notReady.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				readyClient.send(false);
			}
		});
		JRadioButton ready = new JRadioButton("ready to rotate");
		ready.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				readyClient.send(true);
			}
		});
		notReady.setSelected(true);
		ButtonGroup readyGroup = new ButtonGroup();
		readyGroup.add(notReady);
		readyGroup.add(ready);
		JPanel readyPanel = new JPanel();
		readyPanel.add(notReady);
		readyPanel.add(ready);

		final SignalLevelClient pos5Client = new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_BOTTLE_AT_POS5_TOGGLE);
		JRadioButton noBottlePos5 = new JRadioButton("no bottle");
		noBottlePos5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pos5Client.send(false);
			}
		});
		JRadioButton bottlePos5 = new JRadioButton("bottle at Pos5");
		bottlePos5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pos5Client.send(true);
			}
		});
		noBottlePos5.setSelected(true);
		ButtonGroup pos5Group = new ButtonGroup();
		pos5Group.add(noBottlePos5);
		pos5Group.add(bottlePos5);
		JPanel pos5Panel = new JPanel();
		pos5Panel.add(noBottlePos5);
		pos5Panel.add(bottlePos5);

		final SignalLevelClient capPos1Client = new SignalLevelClient(Ports.PORT_ROTARYTABLE_PLANT, Ports.ROTARYTABLE_CAP_ON_BOTTLE_AT_POS1_TOGGLE);
		JRadioButton noCapPos1 = new JRadioButton("no cap");
		noCapPos1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				capPos1Client.send(false);
			}
		});
		JRadioButton capPos1 = new JRadioButton("cap on bottle at Pos1");
		capPos1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				capPos1Client.send(true);
			}
		});
		noCapPos1.setSelected(true);
		ButtonGroup capGroup = new ButtonGroup();
		capGroup.add(noCapPos1);
		capGroup.add(capPos1);
		JPanel capPanel = new JPanel();
		capPanel.add(noCapPos1);
		capPanel.add(capPos1);

		JPanel devControls = new JPanel();
		devControls.setBorder(BorderFactory.createTitledBorder("DEV signals (stand-ins for Conveyor / Sorting)"));
		devControls.add(readyPanel);
		devControls.add(pos5Panel);
		devControls.add(capPanel);

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

		// rotateM is only read by the controller's manual-mode branch, so jog only
		// while held down: press sends true, release sends false.
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
		this.add(devControls, c);
		c.gridy = 2;
		this.add(modePanel, c);

		this.setTitle("Rotary Table");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
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
