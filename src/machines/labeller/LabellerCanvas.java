package machines.labeller;
 
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 
public class LabellerCanvas extends JPanel {
 
	private static final long serialVersionUID = 1L;
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int centerX = getWidth() / 2;
        int baseY = 220; 
 
        // 1. Draw Rectangular Bottle
        if (LabellerState.BOTTLE_PRESENT) {
            g.setColor(new Color(200, 230, 255));
            g.fillRect(centerX - 20, baseY - 80, 40, 80);
            g.setColor(Color.BLACK);
            g.drawRect(centerX - 20, baseY - 80, 40, 80);
        }
 
        // 2. Draw Clamps (Long rectangles enclosing the bottle)
        // If clamped, offset is 20 (touching the 40px wide bottle). If not, retracted to 50.
        int clampOffset = LabellerState.CLAMPED ? 20 : 50; 
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
     // 4. Draw Applied Label on Bottle
        if (LabellerState.BOTTLE_PRESENT && LabellerState.LABEL_APPLIED) {
            g.setColor(Color.WHITE);
            g.fillRect(centerX - 15, baseY - 55, 30, 30); // Applied sticker
            g.setColor(Color.BLACK);
            g.drawRect(centerX - 15, baseY - 55, 30, 30);
            g.drawString("ID", centerX - 8, baseY - 35);
        }
	}
 
    /**
     * Draws a small label sticker showing ID / bottle size / liquid ratio.
     * These values are hardcoded placeholders in LabellerState for now, until
     * the real liquidARatio/targetVolumeMl signals are wired through.
     */
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
