package machines.capper;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CapperCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int centerX = getWidth() / 2;
        int baseY = 220; // Bottom of the bottle

        // 1. Draw Rectangular Bottle
        if (CapperState.BOTTLE_PRESENT) {
            g.setColor(new Color(200, 230, 255));
            g.fillRect(centerX - 20, baseY - 80, 40, 80);
            g.setColor(Color.BLACK);
            g.drawRect(centerX - 20, baseY - 80, 40, 80);
            
            // Bottle Neck/Cap Area
            g.fillRect(centerX - 10, baseY - 95, 20, 15);
        }

        // 2. Draw Clamps (2 Squares)
        // If clamped, offset is 20 (touching the 40px wide bottle). If not, retracted to 40.
        int clampOffset = CapperState.CLAMPED ? 20 : 40; 
        g.setColor(Color.GRAY);
        g.fillRect(centerX - clampOffset - 30, baseY - 40, 30, 30); // Left clamp
        g.fillRect(centerX + clampOffset, baseY - 40, 30, 30);      // Right clamp

        // 3. Draw Grip (Rectangle that lowers and lifts)
        int gripBaseY = baseY - 130;
        int currentGripY = gripBaseY + CapperState.GRIP_HEIGHT; 
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(centerX - 25, currentGripY, 50, 20);

        // Grip Indicator (Changes color when it grabs the cap)
        g.setColor(CapperState.GRIPPED ? Color.GREEN : Color.RED);
        g.fillOval(centerX - 6, currentGripY + 4, 12, 12);

        // 4. Draw 270-Degree Twist Arc and Slider
        int arcRadius = 30;
        int arcCenterX = centerX;
        int arcCenterY = currentGripY - 35; // Arc sits just above the grip
        
        g2.setStroke(new BasicStroke(3));
        g.setColor(Color.LIGHT_GRAY);
        // Draw the 270-degree track (starting at -45 degrees, sweeping 270)
        g.drawArc(arcCenterX - arcRadius, arcCenterY - arcRadius, arcRadius * 2, arcRadius * 2, -45, 270);

        // Calculate slider position on the arc using trigonometry
        // Subtracting TWIST_ANGLE because standard drawArc goes counter-clockwise
        double angleRad = Math.toRadians(-45 + CapperState.TWIST_ANGLE);
        int sliderX = arcCenterX + (int) (arcRadius * Math.cos(angleRad));
        int sliderY = arcCenterY - (int) (arcRadius * Math.sin(angleRad)); // Y is inverted in Java 2D

        // Draw the twist indicator (slider)
        g.setColor(Color.BLUE);
        g.fillOval(sliderX - 8, sliderY - 8, 16, 16);
        }
 
	public CapperCanvas() {

	}

}
