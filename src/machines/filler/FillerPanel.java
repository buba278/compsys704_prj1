package machines.filler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.compsys704.Ports;
import org.compsys704.SignalClient;
import org.compsys704.SignalLevelClient;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

import run.FillerFaultState;

public class FillerPanel extends JFrame {

      private JRadioButton defaultRatioButton;
      private JRadioButton defaultVolumeButton;
      private final java.util.List<AbstractButton> devButtons = new java.util.ArrayList<AbstractButton>();

      public FillerPanel() {
              FillerCanvas canvas = new FillerCanvas();
              canvas.setPreferredSize(new Dimension(260, 260));
              canvas.setBackground(Color.WHITE);

              final SignalLevelClient bottleControllerClient = new SignalLevelClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_BOTTLE_AT_POS2_CONTROLLER);
              final SignalLevelClient bottlePlantClient = new SignalLevelClient(Ports.PORT_FILLER_PLANT, Ports.FILLER_BOTTLE_AT_POS2_PLANT);

              JRadioButton bottlePresent = new JRadioButton("bottleAtPos2");
              bottlePresent.addActionListener(new java.awt.event.ActionListener() {
                      public void actionPerformed(java.awt.event.ActionEvent e) {
                              bottleControllerClient.send(true);
                              bottlePlantClient.send(true);
                      }
              });
              JRadioButton bottleAbsent = new JRadioButton("no bottle");
              bottleAbsent.addActionListener(new java.awt.event.ActionListener() {
                      public void actionPerformed(java.awt.event.ActionEvent e) {
                              bottleControllerClient.send(false);
                              bottlePlantClient.send(false);
                              FillerState.FILL_DONE = false;
                      }
              });
              bottleAbsent.setSelected(true);
              ButtonGroup bottleGroup = new ButtonGroup();
              bottleGroup.add(bottlePresent);
              bottleGroup.add(bottleAbsent);
              JPanel bottlePanel = new JPanel();
              bottlePanel.add(bottleAbsent);
              bottlePanel.add(bottlePresent);
              devButtons.add(bottlePresent);
              devButtons.add(bottleAbsent);

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
                      devButtons.add(rb);
              }

              SignalRadioClient volumeClient = new SignalRadioClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_TARGET_VOLUME_ML);
              volumeClient.setCheckBoxComponent(new JPanel());
              JPanel volumePanel = new JPanel();
              ButtonGroup volumeGroup = new ButtonGroup();
              String[] volumes = { "200", "330", "500" };
              for (final String v : volumes) {
                      JRadioButton rb = new JRadioButton(v + "ml");
                      rb.setActionCommand(v);
                      rb.addActionListener(volumeClient);
                      rb.addActionListener(new java.awt.event.ActionListener() {
                              public void actionPerformed(java.awt.event.ActionEvent e) {
                                      FillerState.TARGET_VOLUME_ML = Integer.parseInt(v);
                              }
                      });
                      if (v.equals("330")) defaultVolumeButton = rb;
                      volumeGroup.add(rb);
                      volumePanel.add(rb);
                      devButtons.add(rb);
              }

              JPanel devControls = new JPanel();
              devControls.setBorder(BorderFactory.createTitledBorder("RotaryTable / Coordinator emulator + extra testing functionalities)"));
              devControls.add(bottlePanel);
              devControls.add(ratioPanel);
              devControls.add(volumePanel);

              JButton overfillButton = new JButton("Overfill");
              overfillButton.addActionListener(new SignalClient(Ports.PORT_FILLER_PLANT, Ports.FILLER_OVERFILL_M));
              JButton stallButton = new JButton("Stall");
              stallButton.addActionListener(new SignalClient(Ports.PORT_FILLER_PLANT, Ports.FILLER_STALL_M));
              // Clears plant-side state directly (same JVM) and signals the controller
              // separately (a different process). See CLAUDE.md.
              JButton clearFaultButton = new JButton("Clear Fault");
              clearFaultButton.addActionListener(new java.awt.event.ActionListener() {
                      public void actionPerformed(java.awt.event.ActionEvent e) {
                              FillerFaultState.clearOverfill();
                              FillerFaultState.clearStall();
                              FillerState.FAULT = false;
                      }
              });
              clearFaultButton.addActionListener(new SignalClient(Ports.PORT_FILLER_CONTROLLER, Ports.FILLER_CLEAR_FAULT_M));
              JPanel faultPanel = new JPanel();
              faultPanel.setBorder(BorderFactory.createTitledBorder("Fault injection"));
              faultPanel.add(overfillButton);
              faultPanel.add(stallButton);
              faultPanel.add(clearFaultButton);
              devButtons.add(overfillButton);
              devButtons.add(stallButton);
              devButtons.add(clearFaultButton);

              // Auto/Manual just gates whether these DEV stand-ins are allowed to drive
              // bottleAtPos2/liquidARatio/targetVolumeMl by hand, so the same signals can
              // later be fed by the real turntable/orchestrator without the two racing.
              final JRadioButton amode = new JRadioButton("Auto");
              final JRadioButton mmode = new JRadioButton("Manual");
              amode.setSelected(true);
              java.awt.event.ActionListener modeToggle = new java.awt.event.ActionListener() {
                      public void actionPerformed(java.awt.event.ActionEvent e) {
                              boolean manual = mmode.isSelected();
                              for (AbstractButton b : devButtons) b.setEnabled(manual);
                      }
              };
              amode.addActionListener(modeToggle);
              mmode.addActionListener(modeToggle);
              ButtonGroup modeGroup = new ButtonGroup();
              modeGroup.add(amode);
              modeGroup.add(mmode);
              JPanel modePanel = new JPanel();
              modePanel.add(amode);
              modePanel.add(mmode);
              modePanel.setBorder(BorderFactory.createTitledBorder("Mode selector"));

              JPanel bottomRow = new JPanel();
              bottomRow.add(faultPanel);
              bottomRow.add(modePanel);

              this.setLayout(new GridBagLayout());
              GridBagConstraints c = new GridBagConstraints();
              c.gridx = 0; c.gridy = 0;
              this.add(canvas, c);
              c.gridy = 1;
              this.add(devControls, c);
              c.gridy = 2;
              this.add(bottomRow, c);

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
              for (AbstractButton b : panel.devButtons) b.setEnabled(false);

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