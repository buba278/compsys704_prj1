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
	private static final int POS1_Y = 60;

	// How long a lane's slot stays highlighted after its own stage last changed
	// (see RotaryTableState.LANE_LAST_MOVE_MS) - long enough to catch the eye,
	// short enough that it reads as "just moved" rather than a steady state.
	private static final long MOVE_HIGHLIGHT_MS = 500;

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);  // light blue, matches FillerCanvas
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);  // light orange, matches FillerCanvas
	private static final Color STATION_BLUE = new Color(30, 40, 60);       // matches StationHeader's banner colour
	private static final Color STATION_BLUE = new Color(30, 40, 60);       // matches StationHeader's banner colour

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

		g.setColor(RotaryTableState.CAP_ON_BOTTLE_AT_POS1 ? LIQUID_B_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS1_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Cap on bottle at Pos 1", LABEL_X, POS1_Y + 12);

		// Up to three bottles can be on the table at once now, each shown in
		// its own colour (see RotaryTableState.LANE_COLORS). Labelled "Bottle
		// A/B/C" rather than "Lane N" - the table only has one physical path,
		// so "lane" implies a separate track that doesn't exist; these are
		// just three bottles the table can be working on concurrently.
		for (int lane = 0; lane < 3; lane++) {
			int stage = RotaryTableState.LANE_STAGE[lane];
			g.setColor(stage == 0 ? Color.GRAY : RotaryTableState.LANE_COLORS[lane]);
			g.drawString("Bottle " + (char) ('A' + lane) + ": " + RotaryTableState.STAGE_NAMES[stage],
					LABEL_X, POS1_Y + 30 + lane * 15);
		}

		// the turntable itself, with 6 slots spaced 60 degrees apart (5 active + 1 spare)
		g.setColor(Color.DARK_GRAY);
		g.drawOval(TABLE_CENTER_X - TABLE_RADIUS, TABLE_CENTER_Y - TABLE_RADIUS, TABLE_RADIUS * 2, TABLE_RADIUS * 2);

		final int TOP_POSITION_INDEX = 2; // Pos3 (i == 2) is drawn at the top

		// Static spokes under the fixed stations - the stations themselves
		// never move (each lane independently claims/releases them - see
		// CLAUDE.md's Rotary Table multi-bottle concurrency section), so
		// unlike an earlier version these no longer spin on every new-bottle
		// trigger regardless of which lane (if any) actually moved as a
		// result. Which bottle just moved is shown per-lane below instead.
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

			boolean isPos1 = (i == 0);
			boolean isPos2 = (i == 1);
			boolean isPos3 = (i == 2);
			boolean isPos4 = (i == 3);
			boolean isPos5 = (i == 4);
			Color fill = Color.WHITE;
			if (isPos1 && RotaryTableState.CAP_ON_BOTTLE_AT_POS1) fill = LIQUID_B_COLOR;
			if (isPos5 && RotaryTableState.BOTTLE_AT_POS5) fill = LIQUID_A_COLOR;

			// Which lane(s), if any, currently have a bottle at this station -
			// normally at most one (Filler/Lid Placer/Capper are each a single
			// physical station the lanes take turns claiming - see
			// RotaryTableState.tryClaimFiller() etc.), but two can briefly
			// show here at once: one lane's view-delay hold overlapping with
			// the next lane already having claimed and started at the same
			// station.
			java.util.List<Integer> occupyingLanes = new java.util.ArrayList<Integer>();
			for (int lane = 0; lane < 3; lane++) {
				int stage = RotaryTableState.LANE_STAGE[lane];
				boolean laneHere = (isPos2 && stage == 2) || (isPos3 && stage == 4) || (isPos4 && stage == 1);
				if (laneHere) occupyingLanes.add(lane);
			}
			boolean isCurrentStage = !occupyingLanes.isEmpty();
			if (isCurrentStage) fill = RotaryTableState.LANE_COLORS[occupyingLanes.get(0)];

			// Did any occupying lane's stage change recently? Each bottle moves
			// independently as its own lane claims/releases stations, not in a
			// synchronized "rotation" affecting every bottle at once - this
			// highlight makes that visible: only the slot a bottle actually just
			// arrived at gets the gold ring, not the whole table.
			boolean justMoved = false;
			for (int lane : occupyingLanes) {
				if (System.currentTimeMillis() - RotaryTableState.LANE_LAST_MOVE_MS[lane] < MOVE_HIGHLIGHT_MS) {
					justMoved = true;
				}
			}

			Ellipse2D slot = new Ellipse2D.Double(slotX - SLOT_RADIUS, slotY - SLOT_RADIUS, SLOT_RADIUS * 2, SLOT_RADIUS * 2);
			g.setColor(fill);
			g.fill(slot);
			g.setColor(justMoved ? Color.ORANGE : (isCurrentStage ? fill.darker() : Color.BLACK));
			g.setStroke(new java.awt.BasicStroke(justMoved ? 5f : (isCurrentStage ? 3f : 1f)));
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