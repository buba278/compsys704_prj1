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
	private static final Color STATION_BLUE = new Color(30, 40, 60);       // matches StationHeader's banner colour

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

		// Current bottle stage readout - what the user actually asked to see:
		// where the bottle on the table is right now.
		g.setColor(RotaryTableState.BOTTLE_STAGE == 0 ? Color.GRAY : Color.RED.darker());
		g.drawString("Bottle stage: " + RotaryTableState.STAGE_NAMES[RotaryTableState.BOTTLE_STAGE],
				LABEL_X, POS1_Y + 30);

		// the turntable itself, with 6 slots spaced 60 degrees apart (5 active + 1 spare)
		g.setColor(Color.DARK_GRAY);
		g.drawOval(TABLE_CENTER_X - TABLE_RADIUS, TABLE_CENTER_Y - TABLE_RADIUS, TABLE_RADIUS * 2, TABLE_RADIUS * 2);

		final int TOP_POSITION_INDEX = 2; // Pos3 (i == 2) is drawn at the top

		// the physical disc underneath the fixed stations - these spokes are
		// the only thing that actually rotates with CURRENT_ANGLE_DEG, so the
		// table visibly spins while the station positions themselves stay put
		g.setColor(new Color(225, 225, 225));
		for (int i = 0; i < 6; i++) {
			double spokeAngleDeg = -90 + (i - TOP_POSITION_INDEX) * 60 + RotaryTableState.CURRENT_ANGLE_DEG;
			double spokeAngleRad = Math.toRadians(spokeAngleDeg);
			int spokeX = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(spokeAngleRad));
			int spokeY = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(spokeAngleRad));
			g.drawLine(TABLE_CENTER_X, TABLE_CENTER_Y, spokeX, spokeY);
		}

		for (int i = 0; i < 6; i++) {
			double angleDeg = -90 + (i - TOP_POSITION_INDEX) * 60;
			double angleRad = Math.toRadians(angleDeg);
			int slotX = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(angleRad));
			int slotY = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(angleRad));

			boolean isPos1 = (i == 0);
			boolean isPos2 = (i == 1);
			boolean isPos3 = (i == 2);
			boolean isPos4 = (i == 3);
			boolean isPos5 = (i == 4);
			Color fill = Color.WHITE;
			if (isPos1 && RotaryTableState.CAP_ON_BOTTLE_AT_POS1) fill = LIQUID_B_COLOR;
			if (isPos5 && RotaryTableState.BOTTLE_AT_POS5) fill = LIQUID_A_COLOR;

			// Highlight whichever station the current bottle's stage says
			// it's at (see RotaryTableState.BOTTLE_STAGE / STAGE_NAMES).
			boolean isCurrentStage = (isPos2 && RotaryTableState.BOTTLE_STAGE == 2)
					|| (isPos3 && RotaryTableState.BOTTLE_STAGE == 4)
					|| (isPos4 && RotaryTableState.BOTTLE_STAGE == 1);
			if (isCurrentStage) fill = Color.RED;

			Ellipse2D slot = new Ellipse2D.Double(slotX - SLOT_RADIUS, slotY - SLOT_RADIUS, SLOT_RADIUS * 2, SLOT_RADIUS * 2);
			g.setColor(fill);
			g.fill(slot);
			g.setColor(isCurrentStage ? Color.RED.darker() : Color.BLACK);
			g.setStroke(new java.awt.BasicStroke(isCurrentStage ? 3f : 1f));
			g.draw(slot);
			g.setStroke(new java.awt.BasicStroke(1f));

			g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 20f));
			g.setColor(isCurrentStage ? Color.WHITE : STATION_BLUE);
			String label = String.valueOf(i + 1);
			java.awt.FontMetrics fm = g.getFontMetrics();
			g.drawString(label, slotX - fm.stringWidth(label) / 2, slotY + fm.getAscent() / 2 - 2);
		}
	}
}