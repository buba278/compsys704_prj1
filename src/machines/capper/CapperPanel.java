package machines.capper;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.compsys704.Ports;
import org.compsys704.SignalCheckBoxClient;
import org.compsys704.SignalClient;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

public class CapperPanel extends JFrame {

	public CapperPanel() {
        CapperCanvas canvas = new CapperCanvas();
        canvas.setPreferredSize(new Dimension(300, 300));
        canvas.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL;
        this.add(org.compsys704.StationHeader.make("STATION 4 - CAPPER"), c);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        this.add(canvas, c);

        // Mode selector + manual control, same pattern as the Lid Placer (CapLoader).
        SignalRadioClient src = new SignalRadioClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_MODE);
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

        JCheckBox gripperDown = new JCheckBox("gripperDown");
        gripperDown.setEnabled(false);
        gripperDown.addItemListener(new SignalCheckBoxClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_SEND_GRIPPER_DOWN_M));
        JCheckBox gripperTwist = new JCheckBox("gripperTwist");
        gripperTwist.setEnabled(false);
        gripperTwist.addItemListener(new SignalCheckBoxClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_SEND_GRIPPER_TWIST_M));
        JCheckBox gripperUntwist = new JCheckBox("gripperUntwist");
        gripperUntwist.setEnabled(false);
        gripperUntwist.addItemListener(new SignalCheckBoxClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_SEND_GRIPPER_UNTWIST_M));
        JCheckBox gripCap = new JCheckBox("gripCap");
        gripCap.setEnabled(false);
        gripCap.addItemListener(new SignalCheckBoxClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_SEND_GRIP_CAP_M));
        JCheckBox clamp = new JCheckBox("clamp");
        clamp.setEnabled(false);
        clamp.addItemListener(new SignalCheckBoxClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_SEND_CLAMP_M));

        JPanel manualPanel = new JPanel(new GridLayout(2, 3));
        manualPanel.add(gripperDown);
        manualPanel.add(gripperTwist);
        manualPanel.add(gripperUntwist);
        manualPanel.add(gripCap);
        manualPanel.add(clamp);
        manualPanel.setBorder(BorderFactory.createTitledBorder("Manual control"));
        src.setCheckBoxComponent(manualPanel);

        JPanel controlPanel = new JPanel(new GridLayout(0, 2));
        controlPanel.add(modePanel);
        controlPanel.add(manualPanel);
        c.gridy = 2;
        this.add(controlPanel, c);

        // Fault tolerance testing controls (see the IP report): a manual
        // trigger to demonstrate the detection/escalation path without
        // waiting for a real failure, and a clear button since the
        // controller has no way of confirming a physical repair on its own.
        JButton stallButton = new JButton("Stall");
        stallButton.addActionListener(new SignalClient(Ports.PORT_CAPPER_PLANT, Ports.CAPPER_STALL_M));
        JButton clearFaultButton = new JButton("Clear Fault");
        clearFaultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                CapperState.FAULTED = false;
                CapperState.BACKUP_ACTIVE = false;
            }
        });
        clearFaultButton.addActionListener(new SignalClient(Ports.PORT_CAPPER_CONTROLLER, Ports.CAPPER_CLEAR_FAULT_M));
        JPanel faultPanel = new JPanel();
        faultPanel.setBorder(BorderFactory.createTitledBorder("Fault injection"));
        faultPanel.add(stallButton);
        faultPanel.add(clearFaultButton);
        c.gridy = 3;
        this.add(faultPanel, c);

        this.setTitle("Capper Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        org.compsys704.WindowTile.place(this, "capper");
        this.setResizable(false);

        // "enable" and "simulate bottle" DEV buttons removed - both signals
        // are now driven automatically by RotaryCapperBridge (see
        // rotaryTablePlant.sysj), so manually pressing them would just
        // inject conflicting state into the real handoff.
	}
	
	public static void main(String[] args) {
        CapperPanel panel = new CapperPanel();
        panel.pack();
        panel.setVisible(true);
        

        // NOTE: Replace '10002' with your actual Ports.PORT_CAPPER_VIZ 
        // constant if you have one configured in your org.compsys704.Ports file.
        int vizPort = 10012; 
        
        SignalServer<CapperVizWorker> server = new SignalServer<CapperVizWorker>(vizPort, CapperVizWorker.class);
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
