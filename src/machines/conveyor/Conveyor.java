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

	// loadBottle/bottleFromTable are read with a plain await(...) in the plant,
	// so they just need to be present for one tick, not held - this sends true
	// and then schedules false shortly after so a button click reads as a pulse
	// rather than getting stuck on.
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

		// --- DEV stand-ins for the Loader, Rotary Table and Sorter handoffs ---

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
		// system-ready by default, so the belt works out of the box; the toggle
		// is still there to demonstrate what happens when it's held not-ready
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

		final SignalLevelClient bottleFromTableClient = new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_BOTTLE_FROM_TABLE);
		JButton bottleFromTableButton = new JButton("Bottle From Table (Pos 5)");
		bottleFromTableButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pulse(bottleFromTableClient);
			}
		});

		final SignalLevelClient sortDoneClient = new SignalLevelClient(Ports.PORT_CONVEYOR_PLANT, Ports.CONVEYOR_SORT_DONE);
		JButton sortDoneButton = new JButton("Sort Done (Collection end)");
		sortDoneButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pulse(sortDoneClient);
			}
		});

		JPanel devControls = new JPanel();
		devControls.setBorder(BorderFactory.createTitledBorder("DEV signals (stand-ins for Loader / Rotary Table / Sorter)"));
		devControls.add(enablePanel);
		devControls.add(loadBottleButton);
		devControls.add(bottleFromTableButton);
		devControls.add(sortDoneButton);

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

		// conveyorM is only read by the controller's manual-mode branch, checked
		// every tick, so the motor runs only while held: press sends true, release false.
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
		c.gridx = 0; c.gridy = 0;
		this.add(canvas, c);
		c.gridy = 1;
		this.add(devControls, c);
		c.gridy = 2;
		this.add(modePanel, c);

		this.setTitle("Conveyor Belt");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
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