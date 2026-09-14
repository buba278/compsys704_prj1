package machines.sorter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.compsys704.SignalLevelClient;
import org.compsys704.SignalServer;

public class SorterPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private final List<AbstractButton> devButtons = new ArrayList<>();

    public SorterPanel() {
        SorterCanvas canvas = new SorterCanvas();
        canvas.setPreferredSize(new Dimension(320, 320));
        canvas.setBackground(Color.WHITE);

        // Network clients sending manual signals to SorterPlant input ports
        final SignalLevelClient manualModeClient = new SignalLevelClient(10031, "SorterPlantCD.manualMode");
        final SignalLevelClient manualBottleClient = new SignalLevelClient(10032, "SorterPlantCD.manualBottleAtSorter");
        final SignalLevelClient manualDefectiveClient = new SignalLevelClient(10033, "SorterPlantCD.manualBottleDefective");

        // --- Manual Controls ---
        final JCheckBox bottlePresentBox = new JCheckBox("Bottle at Sorter");
        bottlePresentBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manualBottleClient.send(bottlePresentBox.isSelected());
            }
        });

        final JCheckBox defectiveBox = new JCheckBox("Defective Bottle");
        defectiveBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manualDefectiveClient.send(defectiveBox.isSelected());
            }
        });

        devButtons.add(bottlePresentBox);
        devButtons.add(defectiveBox);

        JPanel manualControls = new JPanel();
        manualControls.setBorder(BorderFactory.createTitledBorder("Manual Signal Injection"));
        manualControls.add(bottlePresentBox);
        manualControls.add(defectiveBox);

        // --- Auto / Manual Mode Selector ---
        final JRadioButton autoMode = new JRadioButton("Auto");
        final JRadioButton manualMode = new JRadioButton("Manual");
        autoMode.setSelected(true);

        ActionListener modeToggle = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isManual = manualMode.isSelected();
                manualModeClient.send(isManual);
                for (AbstractButton b : devButtons) {
                    b.setEnabled(isManual);
                }
                if (!isManual) {
                    bottlePresentBox.setSelected(false);
                    defectiveBox.setSelected(false);
                    manualBottleClient.send(false);
                    manualDefectiveClient.send(false);
                }
            }
        };

        autoMode.addActionListener(modeToggle);
        manualMode.addActionListener(modeToggle);

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(autoMode);
        modeGroup.add(manualMode);

        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode Selector"));
        modePanel.add(autoMode);
        modePanel.add(manualMode);

        // Disable manual controls by default (Auto Mode active)
        for (AbstractButton b : devButtons) {
            b.setEnabled(false);
        }

        // --- Layout Construction ---
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL;
        this.add(org.compsys704.StationHeader.make("STATION 8 — SORTER"), c);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        this.add(canvas, c);

        c.gridy = 2;
        this.add(manualControls, c);

        c.gridy = 3;
        this.add(modePanel, c);

        this.setTitle("Sorter Visualizer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        org.compsys704.WindowTile.place(this, "sorter");
        this.setResizable(false);
    }

    public static void main(String[] args) {
        SorterPanel panel = new SorterPanel();
        panel.pack();
        panel.setVisible(true);

        SignalServer<SorterVizWorker> server = new SignalServer<>(10030, SorterVizWorker.class);
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