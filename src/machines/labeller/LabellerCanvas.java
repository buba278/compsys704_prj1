package machines.labeller;
 
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 
public class LabellerCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	// Matches FillerCanvas's scale so the same bottle reads as the same size
	// across both windows.
	private static final double PIXELS_PER_ML = 0.3;
	private static final int BASE_VOLUME_ML = 200;
	private static final double WIDTH_PER_ML = 30.0 / BASE_VOLUME_ML;
	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);  // matches FillerCanvas
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int baseY = 220;

        // 1. Draw the bottle, sized and filled to match what the Filler
        // actually put in it (LabellerState.BOTTLE_SIZE_ML/LIQUID_RATIO,
        // driven by the Coordinator's recipe - see labellerPlant.sysj). By
        // the time a bottle reaches here it's already full, so the whole
        // height is coloured rather than showing a live fill level.
        int bottleHeight = (int) (LabellerState.BOTTLE_SIZE_ML * PIXELS_PER_ML);
        int bottleWidth = (int) (LabellerState.BOTTLE_SIZE_ML * WIDTH_PER_ML);
        int bottleTop = baseY - bottleHeight;
        if (LabellerState.BOTTLE_PRESENT) {
            int aHeight = (int) (bottleHeight * (LabellerState.LIQUID_RATIO / 100.0));
            g.setColor(LIQUID_A_COLOR);
            g.fillRect(centerX - bottleWidth / 2, baseY - aHeight, bottleWidth, aHeight);
            if (aHeight < bottleHeight) {
                g.setColor(LIQUID_B_COLOR);
                g.fillRect(centerX - bottleWidth / 2, bottleTop, bottleWidth, bottleHeight - aHeight);
            }
            g.setColor(Color.BLACK);
            g.drawRect(centerX - bottleWidth / 2, bottleTop, bottleWidth, bottleHeight);
            g.drawString(LabellerState.BOTTLE_SIZE_ML + "ml", centerX - bottleWidth / 2, bottleTop - 6);
        }

        // 2. Draw Clamps (Long rectangles enclosing the bottle)
        // If clamped, offset is 20 (touching the bottle). If not, retracted to 50.
        int clampOffset = LabellerState.CLAMPED ? bottleWidth / 2 : 50;
        g.setColor(Color.GRAY);
        // Left Clamp
        g.fillRect(centerX - clampOffset - 20, baseY - 70, 20, 60);
        // Right Clamp
        g.fillRect(centerX + clampOffset, baseY - 70, 20, 60);
 
        // 3. Draw Printer & Hovering Label
        g.setColor(Color.DARK_GRAY);
        g.fillRect(centerX - 40, 20, 80, 30); // Printer body
        g.setColor(Color.WHITE);
        g.drawString("PRINTER", centerX - 25, 40);
 
        if (LabellerState.LABEL_PRINTED && !LabellerState.LABEL_APPLIED) {
            // Label hanging from printer, with (temporary/placeholder) parameters
            drawLabel(g, centerX - 30, 50, 60, 46);
        }
 
        // 4. Draw Applied Label on Bottle
        if (LabellerState.BOTTLE_PRESENT && LabellerState.LABEL_APPLIED) {
            g.setColor(Color.WHITE);
            g.fillRect(centerX - 15, baseY - 55, 30, 30); // Applied sticker
            g.setColor(Color.BLACK);
            g.drawRect(centerX - 15, baseY - 55, 30, 30);
            g.drawString("ID", centerX - 8, baseY - 35);
        }
	}
 
    // Draws a small label sticker showing ID / bottle size / liquid ratio.
    private void drawLabel(Graphics g, int x, int y, int w, int h) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
 
        Font original = g.getFont();
        g.setFont(original.deriveFont(9f));
        g.drawString("ID: " + LabellerState.CURRENT_LABEL_ID, x + 4, y + 14);
        g.drawString("Size: " + LabellerState.BOTTLE_SIZE_ML + "ml", x + 4, y + 27);
        g.drawString("Ratio: " + LabellerState.LIQUID_RATIO + "%", x + 4, y + 40);
        g.setFont(original);
    }
    


	public LabellerCanvas() {
 
	}
 
}
