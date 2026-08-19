package machines.filler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.compsys704.Ports;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

public class FillerPanel extends JFrame {

      private JRadioButton defaultRatioButton;
      private JRadioButton defaultVolumeButton;

      public FillerPanel() {
              FillerCanvas canvas = new FillerCanvas();
              canvas.setPreferredSize(new Dimension(200, 260));
              canvas.setBackground(Color.WHITE);

              SignalRadioClient bottleControllerClient = new SignalRadioClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_BOTTLE_AT_POS2_CONTROLLER);
              SignalRadioClient bottlePlantClient = new SignalRadioClient(Ports.PORT_FILLER_PLANT, Ports.FILLER_BOTTLE_AT_POS2_PLANT);
              bottleControllerClient.setCheckBoxComponent(new JPanel());
              bottlePlantClient.setCheckBoxComponent(new JPanel());

              JRadioButton bottlePresent = new JRadioButton("bottleAtPos2");
              bottlePresent.setActionCommand("1");
              bottlePresent.addActionListener(bottleControllerClient);
              bottlePresent.addActionListener(bottlePlantClient);
              JRadioButton bottleAbsent = new JRadioButton("no bottle");
              bottleAbsent.setActionCommand("0");
              bottleAbsent.addActionListener(bottleControllerClient);
              bottleAbsent.addActionListener(bottlePlantClient);
              bottleAbsent.setSelected(true);
              ButtonGroup bottleGroup = new ButtonGroup();
              bottleGroup.add(bottlePresent);
              bottleGroup.add(bottleAbsent);
              JPanel bottlePanel = new JPanel();
              bottlePanel.add(bottleAbsent);
              bottlePanel.add(bottlePresent);

              SignalRadioClient ratioClient = new SignalRadioClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_LIQUID_A_RATIO);
              ratioClient.setCheckBoxComponent(new JPanel());
              JPanel ratioPanel = new JPanel();
              ButtonGroup ratioGroup = new ButtonGroup();
              String[] ratios = { "30", "50", "70" };
              for (String r : ratios) {
                      JRadioButton rb = new JRadioButton(r + "% A");
                      rb.setActionCommand(r);
                      rb.addActionListener(ratioClient);
                      if (r.equals("50")) defaultRatioButton = rb;
                      ratioGroup.add(rb);
                      ratioPanel.add(rb);
              }

              SignalRadioClient volumeClient = new SignalRadioClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_TARGET_VOLUME_ML);
              volumeClient.setCheckBoxComponent(new JPanel());
              JPanel volumePanel = new JPanel();
              ButtonGroup volumeGroup = new ButtonGroup();
              String[] volumes = { "200", "330", "500" };
              for (String v : volumes) {
                      JRadioButton rb = new JRadioButton(v + "ml");
                      rb.setActionCommand(v);
                      rb.addActionListener(volumeClient);
                      if (v.equals("330")) defaultVolumeButton = rb;
                      volumeGroup.add(rb);
                      volumePanel.add(rb);
              }

              JPanel devControls = new JPanel();
              devControls.setBorder(BorderFactory.createTitledBorder("DEV signals (stand-ins for RotaryTable / Coordinator)"));
              devControls.add(bottlePanel);
              devControls.add(ratioPanel);
              devControls.add(volumePanel);

              this.setLayout(new GridBagLayout());
              GridBagConstraints c = new GridBagConstraints();
              c.gridx = 0; c.gridy = 0;
              this.add(canvas, c);
              c.gridy = 1;
              this.add(devControls, c);

              this.setTitle("Filler");
              this.setDefaultCloseOperation(EXIT_ON_CLOSE);
              this.setLocationRelativeTo(null);
              this.setResizable(false);
      }

      public static void main(String[] args) {
              FillerPanel panel = new FillerPanel();
              panel.pack();
              panel.setVisible(true);
              panel.defaultRatioButton.doClick();
              panel.defaultVolumeButton.doClick();

              SignalServer<FillerVizWorker> server = new SignalServer<FillerVizWorker>(Ports.PORT_FILLER_VIZ, FillerVizWorker.class);
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