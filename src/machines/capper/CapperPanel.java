package machines.capper;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.compsys704.Ports;
import org.compsys704.SignalClient;
import org.compsys704.SignalServer;

public class CapperPanel extends JFrame {

	public CapperPanel() {
        CapperCanvas canvas = new CapperCanvas();
        canvas.setPreferredSize(new Dimension(300, 300));
        canvas.setBackground(Color.WHITE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        this.add(canvas, c);

        this.setTitle("Capper Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        JButton enable = new JButton("enable");
		enable.addActionListener(new org.compsys704.SignalClient(10015, "CapperPlantCD.enable"));
		JButton simBottle = new JButton("simulate bottle");
		simBottle.addActionListener(new org.compsys704.SignalClient(10005, "CapperControllerCD.bottleAtPos4"));
		simBottle.addActionListener(new org.compsys704.SignalClient(10003, "CapperplantCD.bottleAtPos4"));
		this.add(enable);
		this.add(simBottle);
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
