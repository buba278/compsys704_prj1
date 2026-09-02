package machines.pos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.compsys704.Ports;
import org.compsys704.SignalClient;
import org.compsys704.SignalRadioClient;
import org.compsys704.SignalServer;

public class PosPanel extends JFrame {

      private JRadioButton defaultRatioButton;
      private JRadioButton defaultVolumeButton;
      private JRadioButton defaultQuantityButton;
      private final JLabel statusLabel = new JLabel("Waiting for order");

      // one titled radio group wired to a SignalRadioClient (sends {true, Integer} on select)
      private JPanel radioGroup(String title, int[] values, String suffix, String dest,
                                JRadioButton[] defaultOut, int defaultVal) {
              SignalRadioClient client = new SignalRadioClient(Ports.PORT_POS, dest);
              client.setCheckBoxComponent(new JPanel());
              JPanel panel = new JPanel();
              panel.setBorder(BorderFactory.createTitledBorder(title));
              ButtonGroup group = new ButtonGroup();
              for (int v : values) {
                      JRadioButton rb = new JRadioButton(v + suffix);
                      rb.setActionCommand(String.valueOf(v));
                      rb.addActionListener(client);
                      if (v == defaultVal) defaultOut[0] = rb;
                      group.add(rb);
                      panel.add(rb);
              }
              return panel;
      }

      public PosPanel() {
              JRadioButton[] rHolder = new JRadioButton[1];
              JRadioButton[] vHolder = new JRadioButton[1];
              JRadioButton[] qHolder = new JRadioButton[1];

              JPanel ratioPanel    = radioGroup("Liquid A ratio", new int[]{30, 50, 70}, "% A",
                                                Ports.POS_LIQUID_A, rHolder, 50);
              JPanel volumePanel   = radioGroup("Bottle size", new int[]{200, 330, 500}, " ml",
                                                Ports.POS_TARGET_VOLUME, vHolder, 330);
              JPanel quantityPanel = radioGroup("Batch quantity", new int[]{1, 2, 3, 5}, " bottles",
                                                Ports.POS_QUANTITY, qHolder, 3);
              defaultRatioButton = rHolder[0];
              defaultVolumeButton = vHolder[0];
              defaultQuantityButton = qHolder[0];

              final SignalClient submitClient = new SignalClient(Ports.PORT_POS, Ports.POS_SUBMIT);
              JButton submit = new JButton("Submit order");
              submit.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                              PosState.ORDER_ACTIVE = true;
                              PosState.BATCH_DONE = false;
                              PosState.LAST_COMPLETION_MS = -1;
                      }
              });
              submit.addActionListener(submitClient); // momentary pulse -> PosCD.submit

              JPanel form = new JPanel(new GridBagLayout());
              GridBagConstraints c = new GridBagConstraints();
              c.gridx = 0; c.fill = GridBagConstraints.HORIZONTAL;
              c.gridy = 0; form.add(ratioPanel, c);
              c.gridy = 1; form.add(volumePanel, c);
              c.gridy = 2; form.add(quantityPanel, c);
              c.gridy = 3; form.add(submit, c);
              c.gridy = 4; form.add(statusLabel, c);
              form.setBorder(BorderFactory.createTitledBorder("Purchase Order"));

              this.add(form);
              this.setTitle("Purchase Order System");
              this.setDefaultCloseOperation(EXIT_ON_CLOSE);
              this.setLocationRelativeTo(null);
              this.setResizable(false);
      }

      private void refreshStatus() {
              if (!PosState.ORDER_ACTIVE) {
                      statusLabel.setText("Waiting for order");
              } else if (PosState.LAST_COMPLETION_MS >= 0) {
                      statusLabel.setText("Batch complete in " + PosState.LAST_COMPLETION_MS + " ms");
              } else {
                      statusLabel.setText("Order in progress...");
              }
      }

      public static void main(String[] args) {
              PosPanel panel = new PosPanel();
              panel.pack();
              panel.setVisible(true);
              panel.defaultRatioButton.doClick();
              panel.defaultVolumeButton.doClick();
              panel.defaultQuantityButton.doClick();

              SignalServer<PosVizWorker> server = new SignalServer<PosVizWorker>(Ports.PORT_POS_VIZ, PosVizWorker.class);
              new Thread(server).start();
              while (true) {
                      try {
                              panel.refreshStatus();
                              panel.repaint();
                              Thread.sleep(50);
                      } catch (InterruptedException e) {
                              e.printStackTrace();
                      }
              }
      }
}
