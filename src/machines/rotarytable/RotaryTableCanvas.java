package machines.rotarytable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class RotaryTableCanvas extends JPanel {

	private static final int TABLE_CENTER_X = 150;
	private static final int TABLE_CENTER_Y = 225;
	private static final int TABLE_RADIUS = 90;
	private static final int SLOT_RADIUS = 16;

	private static final int INDICATOR_X = 10;
	private static final int LABEL_X = 30;
	private static final int ALIGNED_Y = 10;
	private static final int POS5_Y = 35;

	// How long a slot stays highlighted after its bottle id last changed
	// (see RotaryTableState.SLOT_LAST_MOVE_MS) - long enough to catch the
	// eye, short enough that it reads as "just moved" rather than a steady
	// state. Every slot that moved on the same index event lights up
	// together, which is what actually shows true synchronized indexing.
	private static final long MOVE_HIGHLIGHT_MS = 500;

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);  // light blue, matches FillerCanvas
	private static final Color STATION_BLUE = new Color(30, 40, 60);       // matches StationHeader's banner colour

	private static final String[] STATION_NAMES = {
			"Entry (Pos1)", "Filling (Pos2)", "Lid Placing (Pos3)", "Cap Screwing (Pos4)", "Departing (Pos5)"
	};

	@Override
	protected void paintComponent(Graphics gOrig) {
		super.paintComponent(gOrig);
		Graphics2D g = (Graphics2D) gOrig;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(RotaryTableState.TABLE_ALIGNED ? Color.GREEN : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, ALIGNED_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Table Aligned", LABEL_X, ALIGNED_Y + 12);

		g.setColor(RotaryTableState.BOTTLE_AT_POS5 ? LIQUID_A_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS5_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Bottle at Pos 5", LABEL_X, POS5_Y + 12);

		// List every bottle currently on the table and which slot it occupies -
		// with true synchronized indexing there's exactly one bottle per
		// occupied slot, so this is a direct readout of RotaryTableState.SLOT_BOTTLE_ID.
		int row = 0;
		for (int slot = 0; slot < 5; slot++) {
			int bottleId = RotaryTableState.SLOT_BOTTLE_ID[slot];
			if (bottleId <= 0) continue;
			g.setColor(RotaryTableState.colorForBottle(bottleId).darker());
			g.drawString("Bottle #" + bottleId + ": " + STATION_NAMES[slot], LABEL_X, POS5_Y + 30 + row * 15);
			row++;
		}
		if (row == 0) {
			g.setColor(Color.GRAY);
			g.drawString("Table empty", LABEL_X, POS5_Y + 30);
		}

		// the turntable itself, with 6 slots spaced 60 degrees apart (5 active + 1 spare)
		g.setColor(Color.DARK_GRAY);
		g.drawOval(TABLE_CENTER_X - TABLE_RADIUS, TABLE_CENTER_Y - TABLE_RADIUS, TABLE_RADIUS * 2, TABLE_RADIUS * 2);

		final int TOP_POSITION_INDEX = 2; // Pos3 (i == 2) is drawn at the top

		g.setColor(new Color(225, 225, 225));
		for (int i = 0; i < 6; i++) {
			double spokeAngleDeg = -90 + (i - TOP_POSITION_INDEX) * 60;
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

			// Positions 0-4 (i == slot index) are the 5 active physical slots
			// (Entry/Filler/Lid Placer/Capper/Exit, in that real order); Pos 6
			// (i == 5) is the spare slot, always empty for now.
			boolean isActiveSlot = i < 5;
			int bottleId = isActiveSlot ? RotaryTableState.SLOT_BOTTLE_ID[i] : 0;
			boolean occupied = bottleId > 0;

			Color fill = occupied ? RotaryTableState.colorForBottle(bottleId) : Color.WHITE;

			boolean justMoved = isActiveSlot
					&& (System.currentTimeMillis() - RotaryTableState.SLOT_LAST_MOVE_MS[i] < MOVE_HIGHLIGHT_MS);

			Ellipse2D slot = new Ellipse2D.Double(slotX - SLOT_RADIUS, slotY - SLOT_RADIUS, SLOT_RADIUS * 2, SLOT_RADIUS * 2);
			g.setColor(fill);
			g.fill(slot);
			g.setColor(justMoved ? Color.ORANGE : (occupied ? fill.darker() : Color.BLACK));
			g.setStroke(new java.awt.BasicStroke(justMoved ? 5f : (occupied ? 3f : 1f)));
			g.draw(slot);
			g.setStroke(new java.awt.BasicStroke(1f));

			g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 20f));
			g.setColor(occupied ? Color.WHITE : STATION_BLUE);
			String label = String.valueOf(i + 1);
			java.awt.FontMetrics fm = g.getFontMetrics();
			g.drawString(label, slotX - fm.stringWidth(label) / 2, slotY + fm.getAscent() / 2 - 2);
		}
	}
}
