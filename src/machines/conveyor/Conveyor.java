package machines.conveyor;

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
import javax.swing.Timer;

import org.compsys704.Ports;
import org.compsys704.SignalLevelClient;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

public class Conveyor extends JFrame {

	// press-then-release pulse, for one-shot signals like a manual bottle load
	private static void pulse(final SignalLevelClient client) {
		client.send(true);
		Timer resetTimer = new Timer(50, new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				client.send(false);
			}
		});
		resetTimer.setRepeats(false);
		resetTimer.start();
	}

	public Conveyor() {
		ConveyorCanvas canvas = new ConveyorCanvas();
		canvas.setPreferredSize(new Dimension(650, 220));
		canvas.setBackground(Color.WHITE);

		final SignalLevelClient enableClient = new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_ENABLE);
		JRadioButton disabled = new JRadioButton("disabled");
		disabled.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				enableClient.send(false);
			}
		});
		JRadioButton enabled = new JRadioButton("enabled");
		enabled.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				enableClient.send(true);
			}
		});

		enabled.setSelected(true);
		enableClient.send(true);
		ButtonGroup enableGroup = new ButtonGroup();
		enableGroup.add(disabled);
		enableGroup.add(enabled);
		JPanel enablePanel = new JPanel();
		enablePanel.add(disabled);
		enablePanel.add(enabled);

		final SignalLevelClient loadBottleClient = new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_LOAD_BOTTLE);
		JButton loadBottleButton = new JButton("Load Bottle (Pos 1)");
		loadBottleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pulse(loadBottleClient);
			}
		});

		JPanel devControls = new JPanel();
		devControls.setLayout(new javax.swing.BoxLayout(devControls, javax.swing.BoxLayout.LINE_AXIS));
		devControls.setBorder(BorderFactory.createTitledBorder("DEV signals (stand-in for Loader)"));
		devControls.add(enablePanel);
		devControls.add(loadBottleButton);

		// --- real controller inputs: mode + manual jog ---

		final SignalRadioClient modeClient = new SignalRadioClient(Ports.PORT_CONVEYOR_CONTROLLER, Ports.CONVEYOR_MODE);
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

		// conveyorM: motor runs only while held, press sends true, release sends false
		final SignalLevelClient conveyorMClient = new SignalLevelClient(Ports.PORT_CONVEYOR_CONTROLLER, Ports.CONVEYOR_CONVEYOR_M);
		final JButton jogButton = new JButton("Hold to run motor");
		jogButton.setEnabled(false);
		jogButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				conveyorMClient.send(true);
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				conveyorMClient.send(false);
			}
		});
		java.awt.event.ActionListener jogGate = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jogButton.setEnabled(manualMode.isSelected());
			}
		};
		autoMode.addActionListener(jogGate);
		manualMode.addActionListener(jogGate);

		JPanel modePanel = new JPanel();
		modePanel.setBorder(BorderFactory.createTitledBorder("Mode selector"));
		modePanel.add(autoMode);
		modePanel.add(manualMode);
		modePanel.add(jogButton);

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL;
		this.add(org.compsys704.StationHeader.make("STATIONS 1 & 5 - CONVEYOR (LOAD / COLLECT)"), c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		this.add(canvas, c);
		c.gridy = 2;
		this.add(devControls, c);
		c.gridy = 3;
		this.add(modePanel, c);

		this.setTitle("Conveyor Belt");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		org.compsys704.WindowTile.place(this, "conveyor");
		this.setResizable(false);
	}

	public static void main(String[] args) {
		Conveyor frame = new Conveyor();
		frame.pack();
		frame.setVisible(true);

		SignalServer<ConveyorVizWorker> server = new SignalServer<ConveyorVizWorker>(Ports.PORT_CONVEYOR_VIZ, ConveyorVizWorker.class);
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