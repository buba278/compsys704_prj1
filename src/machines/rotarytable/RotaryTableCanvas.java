package machines.rotarytable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class RotaryTableCanvas extends JPanel {

	private static final int TABLE_CENTER_X = 150;
	private static final int TABLE_CENTER_Y = 190;
	private static final int TABLE_RADIUS = 90;
	private static final int SLOT_RADIUS = 16;

	private static final int INDICATOR_X = 10;
	private static final int LABEL_X = 30;
	private static final int ALIGNED_Y = 10;
	private static final int POS5_Y = 35;
	private static final int POS1_Y = 60;

	// Purely a visual easing factor for how fast CURRENT_ANGLE_DEG catches up to
	// TARGET_ANGLE_DEG each repaint - has no bearing on the actual signals.
	private static final double EASE_FACTOR = 0.12;

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);  // light blue, matches FillerCanvas
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);  // light orange, matches FillerCanvas

	private static final String[] SLOT_NAMES = {
			"Pos1: Load/Unload", "Pos2: Filling", "Pos3: Lid Placing",
			"Pos4: Cap Screwing", "Pos5: Collection", "Spare (fault reroute)"
	};

	@Override
	protected void paintComponent(Graphics gOrig) {
		super.paintComponent(gOrig);
		Graphics2D g = (Graphics2D) gOrig;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// ease the displayed angle toward the latest target so a step reads as a turn
		double diff = RotaryTableState.TARGET_ANGLE_DEG - RotaryTableState.CURRENT_ANGLE_DEG;
		RotaryTableState.CURRENT_ANGLE_DEG += diff * EASE_FACTOR;

		g.setColor(RotaryTableState.TABLE_ALIGNED ? Color.GREEN : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, ALIGNED_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Table Aligned", LABEL_X, ALIGNED_Y + 12);

		g.setColor(RotaryTableState.BOTTLE_AT_POS5 ? LIQUID_A_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS5_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Bottle at Pos 5", LABEL_X, POS5_Y + 12);

		g.setColor(RotaryTableState.CAP_ON_BOTTLE_AT_POS1 ? LIQUID_B_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS1_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Cap on bottle at Pos 1", LABEL_X, POS1_Y + 12);

		// the turntable itself, with 6 slots spaced 60 degrees apart (5 active + 1 spare)
		g.setColor(Color.DARK_GRAY);
		g.drawOval(TABLE_CENTER_X - TABLE_RADIUS, TABLE_CENTER_Y - TABLE_RADIUS, TABLE_RADIUS * 2, TABLE_RADIUS * 2);

		for (int i = 0; i < 6; i++) {
			double angleDeg = -90 + i * 60 + RotaryTableState.CURRENT_ANGLE_DEG;
			double angleRad = Math.toRadians(angleDeg);
			int slotX = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(angleRad));
			int slotY = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(angleRad));

			boolean isPos1 = (i == 0);
			boolean isPos5 = (i == 4);
			Color fill = Color.WHITE;
			if (isPos1 && RotaryTableState.CAP_ON_BOTTLE_AT_POS1) fill = LIQUID_B_COLOR;
			if (isPos5 && RotaryTableState.BOTTLE_AT_POS5) fill = LIQUID_A_COLOR;

			Ellipse2D slot = new Ellipse2D.Double(slotX - SLOT_RADIUS, slotY - SLOT_RADIUS, SLOT_RADIUS * 2, SLOT_RADIUS * 2);
			g.setColor(fill);
			g.fill(slot);
			g.setColor(Color.BLACK);
			g.draw(slot);
			g.drawString(String.valueOf(i + 1), slotX - 4, slotY + 4);
		}

		// static legend mapping slot numbers to station names
		int legendY = getHeight() - (SLOT_NAMES.length * 12) - 4;
		for (int i = 0; i < SLOT_NAMES.length; i++) {
			g.drawString((i + 1) + " - " + SLOT_NAMES[i], 10, legendY + i * 12);
		}
	}
}
