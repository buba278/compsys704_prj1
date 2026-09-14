package machines.sorter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SorterCanvas extends JPanel {

    private static final long serialVersionUID = 1L;

    private void resetState() {
        SorterState.BOTTLE_PRESENT = false;
        SorterState.BOTTLE_DEFECTIVE = false;
        SorterState.PUSHER_EXTENDED = false;
        SorterState.SORTED = false;
        SorterState.REJECTED = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int platformY = 40;
        int platformW = 60;
        int platformH = 170;

        // 1. Draw Rectangle Conveyor Platform (Top-Down Track)
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(centerX - (platformW / 2), platformY, platformW, platformH);
        g.setColor(Color.BLACK);
        g.drawRect(centerX - (platformW / 2), platformY, platformW, platformH);

        // 2. Draw Brown Square Box (Rejection Bin to the Right)
        Color brownColor = new Color(139, 69, 19);
        int rejectBoxX = centerX + 50;
        int rejectBoxY = platformY + 45;
        int boxSize = 60;

        g.setColor(brownColor);
        g.fillRect(rejectBoxX, rejectBoxY, boxSize, boxSize);
        g.setColor(Color.BLACK);
        g.drawRect(rejectBoxX, rejectBoxY, boxSize, boxSize);
        g.setColor(Color.WHITE);
        g.drawString("REJECT", rejectBoxX + 8, rejectBoxY + 35);

        // 3. Draw Box Directly in Front of Platform (Sorted Bin at Bottom)
        int sortedBoxX = centerX - 35;
        int sortedBoxY = platformY + platformH + 15;
        int sortedBoxW = 70;
        int sortedBoxH = 50;

        g.setColor(new Color(100, 110, 120));
        g.fillRect(sortedBoxX, sortedBoxY, sortedBoxW, sortedBoxH);
        g.setColor(Color.BLACK);
        g.drawRect(sortedBoxX, sortedBoxY, sortedBoxW, sortedBoxH);
        g.setColor(Color.WHITE);
        g.drawString("SORTED", sortedBoxX + 12, sortedBoxY + 30);

        // 4. Draw Rectangle Pusher Arm
        // Extends laterally from the left across the platform when active
        int armY = platformY + 55;
        int armH = 20;
        int armW = 70;
        int armX = SorterState.PUSHER_EXTENDED ? (centerX - 30) : (centerX - 95);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(armX, armY, armW, armH);
        g.setColor(Color.BLACK);
        g.drawRect(armX, armY, armW, armH);

        // 5. Draw Circle Bottle
        if (SorterState.BOTTLE_PRESENT || SorterState.SORTED || SorterState.REJECTED) {
            int bottleDiameter = 30;
            int bottleX = centerX - (bottleDiameter / 2);
            int bottleY = armY - 5; // Default position on platform

            if (SorterState.REJECTED || (SorterState.PUSHER_EXTENDED && SorterState.BOTTLE_DEFECTIVE)) {
                // Pushed right into the brown rejection box
                bottleX = rejectBoxX + 15;
                bottleY = rejectBoxY + 15;
            } else if (SorterState.SORTED) {
                // Moved forward into the sorted box
                bottleX = sortedBoxX + 20;
                bottleY = sortedBoxY + 10;
            }

            // Red if defective, cyan if good bottle
            g.setColor(SorterState.BOTTLE_DEFECTIVE ? new Color(255, 90, 90) : new Color(135, 206, 250));
            g.fillOval(bottleX, bottleY, bottleDiameter, bottleDiameter);
            g.setColor(Color.BLACK);
            g.drawOval(bottleX, bottleY, bottleDiameter, bottleDiameter);
        }

        // Status Indicators
        g.setColor(Color.BLACK);
        g.drawString("Status: " + (SorterState.BOTTLE_DEFECTIVE ? "DEFECTIVE" : "OK"), 10, 20);
    }
}