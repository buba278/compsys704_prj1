package machines.bigpicture;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.swing.JPanel;

import machines.capper.CapperState;
import machines.conveyor.ConveyorState;
import machines.coordinator.CoordinatorState;
import machines.filler.FillerState;
import machines.labeller.LabellerState;
import machines.pos.Order;
import machines.pos.OrderQueue;
import machines.rotarytable.RotaryTableState;
import machines.sorter.SorterState;
import org.compsys704.WindowFocuser;

// One-page merge of every machine's status into the single system-level view described
// in the IP report (docs/CS704-GP1-IP-Report-Tania.pdf, Fig. 1 panel B), replacing that
// report's simplified mockup with a real rendering built from each station's actual
// shared State classes. The Rotary Table's own five working positions set the whole
// layout: Pos1/Pos5 sit symmetrically at the bottom-left/bottom-right of the dial (see
// the angle math in RotaryTableCanvas), so the conveyor naturally plugs in there,
// extending left from Pos1 (loading side) and right from Pos5 (collection side), exactly
// as asked. Each station only reads shared State fields (no direct socket wiring here),
// so this panel is a passive observer, same as the report's Methodology section
// describes - it becomes fully live once the group's Coordinator telemetry contract
// (report Section 5, steps 1-2) lets it run in the same process as every clock domain,
// same as RotaryTableCanvas/ConveyorCanvas already do for their own single-station views.
public class BigPictureCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	static final int CANVAS_W = 1220;
	static final int CANVAS_H = 780;

	private static final int TABLE_CENTER_X = 440;
	private static final int TABLE_CENTER_Y = 430;
	private static final int TABLE_RADIUS = 130;
	private static final int SLOT_RADIUS = 18;
	private static final int TOP_POSITION_INDEX = 2; // Pos3 drawn at the top, matches RotaryTableCanvas

	private static final double EASE_FACTOR = 0.12; // same easing RotaryTableCanvas uses for its own angle

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);
	private static final Color STATION_BLUE = new Color(30, 40, 60);
	private static final Color IDLE_GREEN = new Color(60, 170, 90);
	private static final Color WAITING_YELLOW = new Color(230, 180, 40);
	private static final Color FAULT_RED = new Color(210, 60, 60);
	private static final Color OFFLINE_GRAY = new Color(170, 170, 170);

	// Window titles as each station sets them with setTitle(...) (see WindowTile's key
	// list) - WindowFocuser matches on this exact text to bring that station's already-
	// open window (a separate JVM process - see CLAUDE.md) to the foreground.
	private static final String TITLE_CONVEYOR = "Conveyor Belt";
	private static final String TITLE_ROTARY_TABLE = "Rotary Table";
	private static final String TITLE_FILLER = "Filler";
	private static final String TITLE_LID_PLACER = "Lid Placer";
	private static final String TITLE_CAPPER = "Capper Visualizer";
	private static final String TITLE_LABELLER = "Labeller Visualizer";
	private static final String TITLE_SORTER = "Sorter Visualizer";

	public BigPictureCanvas() {
		MouseAdapter clickHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = stationTitleAt(e.getX(), e.getY());
				if (title != null) WindowFocuser.focus(title);
			}
		};
		addMouseListener(clickHandler);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				boolean overStation = stationTitleAt(e.getX(), e.getY()) != null;
				setCursor(Cursor.getPredefinedCursor(overStation ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
			}
		});
	}

	// Which station's window a click at (x, y) should bring to the foreground, or null
	// if it's not over any clickable region - hit-tests the same geometry the draw
	// methods below use, rather than tracking separate bounds recorded during paint.
	private String stationTitleAt(int x, int y) {
		int[] pos1 = slotPos(0);
		int[] pos5 = slotPos(4);
		int beltY = pos1[1];

		for (int i = 0; i < 6; i++) {
			int[] p = slotPos(i);
			if (Math.hypot(x - p[0], y - p[1]) <= SLOT_RADIUS + 4) {
				switch (i) {
					case 1: return TITLE_FILLER;      // Pos 2
					case 2: return TITLE_LID_PLACER;  // Pos 3
					case 3: return TITLE_CAPPER;      // Pos 4
					default: return TITLE_ROTARY_TABLE; // Pos 1, 5, 6 - part of the table itself
				}
			}
		}

		int loadingLeft = pos1[0] - 300, loadingRight = pos1[0];
		if (x >= loadingLeft && x <= loadingRight && Math.abs(y - beltY) <= 15) return TITLE_CONVEYOR;

		int exitLeft = pos5[0], exitRight = 700;
		if (x >= exitLeft && x <= exitRight && Math.abs(y - beltY) <= 15) return TITLE_CONVEYOR;

		int labellerCx = 770;
		if (x >= labellerCx - 55 && x <= labellerCx + 55 && y >= beltY - 45 && y <= beltY + 45) return TITLE_LABELLER;

		int sorterCx = 980;
		if (x >= sorterCx - 90 && x <= sorterCx + 90 && y >= TABLE_CENTER_Y - 90 && y <= TABLE_CENTER_Y + 90) return TITLE_SORTER;

		return null;
	}

	@Override
	protected void paintComponent(Graphics gOrig) {
		super.paintComponent(gOrig);
		Graphics2D g = (Graphics2D) gOrig;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawHeader(g);
		drawLegend(g);

		// ConveyorCanvas normally advances BELT_OFFSET itself on every repaint - it
		// doesn't run in this process, so this window advances the same shared field
		// once per frame (same speed/spacing) to keep the belt-marks animation moving
		// on both segments here too.
		if (ConveyorState.MOTOR_ON) {
			ConveyorState.BELT_OFFSET = (ConveyorState.BELT_OFFSET + 1.5) % 24;
		}

		int[] pos1 = slotPos(0);
		int[] pos5 = slotPos(4);
		int beltY = pos1[1]; // Pos1 and Pos5 land on the same Y by construction - see class comment

		drawLoadingSide(g, pos1, beltY);
		drawCollectionSide(g, pos5, beltY);
		drawRotaryTable(g);
		drawLabeller(g, pos5[0], beltY);
		drawSorter(g);
	}

	// angle math mirrors RotaryTableCanvas exactly, so this dial lines up with the one
	// drawn in that station's own window
	private static double angleDeg(int i) {
		return -90 + (i - TOP_POSITION_INDEX) * 60;
	}

	private static int[] slotPos(int i) {
		double rad = Math.toRadians(angleDeg(i));
		int x = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(rad));
		int y = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(rad));
		return new int[] { x, y };
	}

	private void drawHeader(Graphics2D g) {
		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 18f));
		g.drawString("Big-Picture Visualisation — EABS Overview", 20, 28);

		// The recipe/quantity actually being run is the Coordinator's own liquidARatioE/
		// targetVolumeMlE/bottlesNeededE (see coordinator.sysj) - the source of truth for
		// what's in production, rather than the POS order queue's submitted-but-not-yet-
		// dispatched request. Falls back to the latest queued order before any batch has
		// reported in (e.g. right after launch, before the Coordinator's first burst).
		Order active = latestOrder();
		boolean coordinatorHasRecipe = CoordinatorState.BOTTLES_NEEDED > 0;
		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 13f));
		g.setColor(Color.DARK_GRAY);
		String orderLine;
		if (coordinatorHasRecipe) {
			int ratioA = CoordinatorState.LIQUID_A_RATIO;
			orderLine = String.format("Coordinator  |  Liquid A:B ratio %d:%d  |  %d ml bottle  |  batch of %d",
					ratioA, 100 - ratioA, CoordinatorState.TARGET_VOLUME_ML, CoordinatorState.BOTTLES_NEEDED);
		} else if (active != null) {
			orderLine = String.format("Order #%d  |  Liquid A:B ratio %d:%d  |  %d ml bottle  |  batch of %d",
					active.number, active.ratioA, 100 - active.ratioA, active.volume, active.quantity);
		} else {
			orderLine = "No active order";
		}
		g.drawString(orderLine, 20, 50);

		// progress bar: prefer the Coordinator's own bottlesFilledE/bottlesNeededE (counts a
		// bottle only once fillReady confirms it, faulted attempts excluded - see
		// coordinator.sysj), falling back to sorted+rejected out of the queued order's
		// quantity before the Coordinator has reported anything for this batch.
		int barX = 420, barY = 20, barW = 300, barH = 16;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(barX, barY, barW, barH);
		int done, total;
		if (coordinatorHasRecipe) {
			done = CoordinatorState.BOTTLES_FILLED;
			total = CoordinatorState.BOTTLES_NEEDED;
		} else {
			done = SorterState.SORTED_COUNT + SorterState.REJECTED_COUNT;
			total = active == null ? 0 : active.quantity;
		}
		double frac = total > 0 ? Math.min(1.0, done / (double) total) : 0;
		g.setColor(IDLE_GREEN);
		g.fillRect(barX + 1, barY + 1, (int) ((barW - 2) * frac), barH - 2);
		g.setColor(Color.BLACK);
		g.drawString(done + " / " + total + " bottles", barX + barW + 10, barY + 13);

		// Coordinator fault flash: bottleFaultedE (see coordinator.sysj) is a momentary
		// pulse, latched in CoordinatorState with a timestamp so it stays visible for a
		// short window instead of vanishing between repaints.
		boolean faultFlash = System.currentTimeMillis() - CoordinatorState.LAST_FAULT_MS < 1500;
		if (faultFlash) {
			g.setColor(FAULT_RED);
			g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
			g.drawString("Coordinator: bottle faulted, retrying", barX, barY + barH + 16);
		} else if (CoordinatorState.LAST_BATCH_ELAPSED_MS >= 0) {
			g.setColor(Color.GRAY);
			g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
			g.drawString("Last batch: " + CoordinatorState.LAST_BATCH_ELAPSED_MS + " ms", barX, barY + barH + 14);
		}
	}

	private void drawLegend(Graphics2D g) {
		int x = 20, y = CANVAS_H - 50;
		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
		legendDot(g, x, y, IDLE_GREEN, "Idle / OK");
		legendDot(g, x + 110, y, WAITING_YELLOW, "Waiting");
		legendDot(g, x + 210, y, FAULT_RED, "Fault");
		legendDot(g, x + 300, y, OFFLINE_GRAY, "Offline");
		g.setColor(Color.GRAY);
		g.drawString("Click a station above to bring its own detailed window to the front.", x, y + 20);
	}

	private void legendDot(Graphics2D g, int x, int y, Color c, String label) {
		g.setColor(c);
		g.fillOval(x, y - 10, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString(label, x + 16, y - 1);
	}

	// --- loading side: bottle loader + left conveyor segment feeding Pos 1 ------------

	private void drawLoadingSide(Graphics2D g, int[] pos1, int beltY) {
		int beltLeft = pos1[0] - 300; // shorter than the full run to the canvas edge
		int beltRight = pos1[0];
		int beltHeight = 30;

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		g.drawString("Bottle Loader", beltLeft, beltY - beltHeight / 2 - 30);
		drawStatusDot(g, beltLeft + 90, beltY - beltHeight / 2 - 38, true);

		drawBelt(g, beltLeft, beltRight, beltY, beltHeight, "Loading Conveyor");

		if (ConveyorState.LEFT_BOTTLE_ACTIVE) {
			double target = ConveyorState.LEFT_STEP / (double) ConveyorState.TRAVEL_STEPS;
			if (ConveyorState.MOTOR_ON) {
				ConveyorState.LEFT_PROGRESS += (target - ConveyorState.LEFT_PROGRESS) * EASE_FACTOR;
			}
			int bx = (int) (beltLeft + (beltRight - beltLeft) * ConveyorState.LEFT_PROGRESS);
			drawBottleOnBelt(g, bx, beltY, Color.WHITE);
		}
	}

	// --- collection side: right conveyor segment from Pos 5 toward the Labeller --------

	private void drawCollectionSide(Graphics2D g, int[] pos5, int beltY) {
		int beltLeft = pos5[0];
		int beltRight = 700;
		int beltHeight = 30;

		drawBelt(g, beltLeft, beltRight, beltY, beltHeight, "Exit Conveyor");

		if (ConveyorState.RIGHT_BOTTLE_ACTIVE) {
			double target = ConveyorState.RIGHT_STEP / (double) ConveyorState.TRAVEL_STEPS;
			if (ConveyorState.MOTOR_ON) {
				ConveyorState.RIGHT_PROGRESS += (target - ConveyorState.RIGHT_PROGRESS) * EASE_FACTOR;
			}
			int bx = (int) (beltLeft + (beltRight - beltLeft) * ConveyorState.RIGHT_PROGRESS);
			drawFilledBottleOnBelt(g, bx, beltY, ConveyorState.RIGHT_RATIO_A);
		}
	}

	private void drawBelt(Graphics2D g, int left, int right, int y, int height, String label) {
		g.setColor(Color.DARK_GRAY);
		g.drawRect(left, y - height / 2, right - left, height);

		g.setColor(Color.LIGHT_GRAY);
		double offset = ConveyorState.MOTOR_ON ? ConveyorState.BELT_OFFSET : 0;
		for (int x = (int) (left + offset - 24); x < right; x += 24) {
			if (x > left) g.drawLine(x, y - height / 2, x, y + height / 2);
		}

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(label, left + (right - left - fm.stringWidth(label)) / 2, y - height / 2 - 8);
		drawStatusDot(g, left - 14, y, ConveyorState.MOTOR_ON);
	}

	private void drawBottleOnBelt(Graphics2D g, int centerX, int beltY, Color color) {
		int w = 14, h = 26;
		int top = beltY - h / 2 - h + 13;
		g.setColor(color);
		g.fillRect(centerX - w / 2, beltY - h / 2, w, h - 6);
		g.setColor(Color.BLACK);
		g.drawRect(centerX - w / 2, beltY - h / 2, w, h - 6);
	}

	// exiting bottle only: bottom-Liquid-A/top-Liquid-B colour split by ratioA (0-100),
	// same convention as FillerCanvas/ConveyorCanvas's own drawFilledBottle
	private void drawFilledBottleOnBelt(Graphics2D g, int centerX, int beltY, int ratioA) {
		int w = 14, h = 20;
		int left = centerX - w / 2;
		int top = beltY - h / 2;

		int aHeight = (int) Math.round(h * (ratioA / 100.0));
		g.setColor(LIQUID_A_COLOR);
		g.fillRect(left, top + (h - aHeight), w, aHeight);
		if (aHeight < h) {
			g.setColor(LIQUID_B_COLOR);
			g.fillRect(left, top, w, h - aHeight);
		}
		g.setColor(Color.BLACK);
		g.drawRect(left, top, w, h);
	}

	// --- rotary table: dial + per-bottle status, same information RotaryTableCanvas shows --

	private void drawRotaryTable(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.drawOval(TABLE_CENTER_X - TABLE_RADIUS, TABLE_CENTER_Y - TABLE_RADIUS, TABLE_RADIUS * 2, TABLE_RADIUS * 2);

		g.setColor(new Color(225, 225, 225));
		for (int i = 0; i < 6; i++) {
			// Static spokes, same as RotaryTableCanvas's own dial - RotaryTableState
			// deliberately no longer tracks a table rotation angle (see its own
			// comment): each lane's bottle moves independently, not the whole dial.
			double spokeAngleDeg = angleDeg(i);
			double spokeRad = Math.toRadians(spokeAngleDeg);
			int sx = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(spokeRad));
			int sy = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(spokeRad));
			g.drawLine(TABLE_CENTER_X, TABLE_CENTER_Y, sx, sy);
		}

		for (int i = 0; i < 6; i++) {
			int[] p = slotPos(i);
			boolean isPos1 = i == 0, isPos2 = i == 1, isPos3 = i == 2, isPos4 = i == 3, isPos5 = i == 4;

			Color fill = Color.WHITE;
			if (isPos1 && RotaryTableState.CAP_ON_BOTTLE_AT_POS1) fill = LIQUID_B_COLOR;
			if (isPos5 && RotaryTableState.BOTTLE_AT_POS5) fill = LIQUID_A_COLOR;

			int occupyingLane = -1;
			for (int lane = 0; lane < 3; lane++) {
				int stage = RotaryTableState.LANE_STAGE[lane];
				boolean laneHere = (isPos2 && stage == 2) || (isPos3 && stage == 4) || (isPos4 && stage == 1);
				if (laneHere) { occupyingLane = lane; break; }
			}
			boolean isCurrentStage = occupyingLane >= 0;
			if (isCurrentStage) fill = RotaryTableState.LANE_COLORS[occupyingLane];

			Ellipse2D slot = new Ellipse2D.Double(p[0] - SLOT_RADIUS, p[1] - SLOT_RADIUS, SLOT_RADIUS * 2, SLOT_RADIUS * 2);
			g.setColor(fill);
			g.fill(slot);
			g.setColor(isCurrentStage ? fill.darker() : Color.BLACK);
			g.setStroke(new BasicStroke(isCurrentStage ? 3f : 1f));
			g.draw(slot);
			g.setStroke(new BasicStroke(1f));

			g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 16f));
			g.setColor(isCurrentStage ? Color.WHITE : STATION_BLUE);
			String label = String.valueOf(i + 1);
			FontMetrics fm = g.getFontMetrics();
			g.drawString(label, p[0] - fm.stringWidth(label) / 2, p[1] + fm.getAscent() / 2 - 2);
		}

		stationLabel(g, slotPos(1), "Filling Unit\n(Pos 2)", FillerState.FAULT, FillerState.FILL_DONE);
		stationLabel(g, slotPos(2), "Lid Placer\n(Pos 3)", false, RotaryTableState.LANE_STAGE[0] == 4
				|| RotaryTableState.LANE_STAGE[1] == 4 || RotaryTableState.LANE_STAGE[2] == 4);
		stationLabel(g, slotPos(3), "Cap Screwing\n(Pos 4)", false, CapperState.GRIPPED);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		g.drawString("Table Aligned", TABLE_CENTER_X - 40, TABLE_CENTER_Y + TABLE_RADIUS + 60);
		drawStatusDot(g, TABLE_CENTER_X - 60, TABLE_CENTER_Y + TABLE_RADIUS + 56, RotaryTableState.TABLE_ALIGNED);

		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 12f));
		for (int lane = 0; lane < 3; lane++) {
			int stage = RotaryTableState.LANE_STAGE[lane];
			g.setColor(stage == 0 ? Color.GRAY : RotaryTableState.LANE_COLORS[lane]);
			String text = "Bottle " + (char) ('A' + lane) + ": " + RotaryTableState.STAGE_NAMES[stage];
			FontMetrics fm = g.getFontMetrics();
			g.drawString(text, TABLE_CENTER_X - fm.stringWidth(text) / 2,
					TABLE_CENTER_Y + TABLE_RADIUS + 78 + lane * 16);
		}
	}

	// small label + status dot floating near a station's slot, offset outward along the
	// same radial direction as that slot so it never overlaps the dial itself
	private void stationLabel(Graphics2D g, int[] slot, String text, boolean fault, boolean active) {
		double dx = slot[0] - TABLE_CENTER_X;
		double dy = slot[1] - TABLE_CENTER_Y;
		double len = Math.sqrt(dx * dx + dy * dy);
		int lx = (int) (slot[0] + dx / len * 70);
		int ly = (int) (slot[1] + dy / len * 70);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		String[] lines = text.split("\n");
		FontMetrics fm = g.getFontMetrics();
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], lx - fm.stringWidth(lines[i]) / 2, ly + i * 14);
		}
		Color statusColor = fault ? FAULT_RED : (active ? IDLE_GREEN : WAITING_YELLOW);
		drawStatusDot(g, lx, ly - 16, statusColor);
	}

	private void drawStatusDot(Graphics2D g, int x, int y, boolean active) {
		drawStatusDot(g, x, y, active ? IDLE_GREEN : OFFLINE_GRAY);
	}

	private void drawStatusDot(Graphics2D g, int x, int y, Color c) {
		g.setColor(c);
		g.fillOval(x - 5, y - 5, 10, 10);
		g.setColor(Color.BLACK);
		g.drawOval(x - 5, y - 5, 10, 10);
	}

	// --- labeller + sorter, at the far end of the exit conveyor -----------------------

	private void drawLabeller(Graphics2D g, int fromX, int beltY) {
		int cx = 770, cy = beltY;
		g.setColor(new Color(245, 245, 245));
		g.fillRect(cx - 55, cy - 45, 110, 90);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(cx - 55, cy - 45, 110, 90);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		g.drawString("Labeller", cx - 30, cy - 50);
		drawStatusDot(g, cx - 40, cy - 58, LabellerState.LABEL_APPLIED);

		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
		g.setColor(Color.DARK_GRAY);
		g.drawString("ID: " + LabellerState.CURRENT_LABEL_ID, cx - 48, cy - 10);
		g.drawString("Labelled: " + LabellerState.LABEL_COUNTER, cx - 48, cy + 6);

		if (LabellerState.BOTTLE_PRESENT) {
			g.setColor(LabellerState.LABEL_APPLIED ? LIQUID_B_COLOR : LIQUID_A_COLOR);
			g.fillRect(cx - 8, cy + 15, 16, 24);
			g.setColor(Color.BLACK);
			g.drawRect(cx - 8, cy + 15, 16, 24);
		}
	}

	private void drawSorter(Graphics2D g) {
		int cx = 980, cy = TABLE_CENTER_Y;

		g.setColor(new Color(245, 245, 245));
		g.fillRect(cx - 90, cy - 90, 180, 180);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(cx - 90, cy - 90, 180, 180);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 13f));
		g.drawString("Unloader / Sorting", cx - 55, cy - 95);
		drawStatusDot(g, cx - 75, cy - 103, !SorterState.BOTTLE_DEFECTIVE);

		int boxW = 70, boxH = 50;
		g.setColor(new Color(220, 235, 220));
		g.fillRect(cx - 80, cy + 10, boxW, boxH);
		g.setColor(Color.BLACK);
		g.drawRect(cx - 80, cy + 10, boxW, boxH);
		g.drawString("Sorted", cx - 68, cy + 30);
		g.drawString(String.valueOf(SorterState.SORTED_COUNT), cx - 45, cy + 48);

		g.setColor(new Color(235, 210, 200));
		g.fillRect(cx + 10, cy + 10, boxW, boxH);
		g.setColor(Color.BLACK);
		g.drawRect(cx + 10, cy + 10, boxW, boxH);
		g.drawString("Rejects", cx + 22, cy + 30);
		g.drawString(String.valueOf(SorterState.REJECTED_COUNT), cx + 45, cy + 48);

		if (SorterState.BOTTLE_PRESENT) {
			g.setColor(SorterState.BOTTLE_DEFECTIVE ? FAULT_RED : new Color(135, 206, 250));
			g.fillOval(cx - 10, cy - 45, 20, 20);
			g.setColor(Color.BLACK);
			g.drawOval(cx - 10, cy - 45, 20, 20);
		}
	}

	private Order latestOrder() {
		List<Order> orders = OrderQueue.getOrders();
		Order inProgress = null;
		for (Order o : orders) {
			if (o.status == Order.Status.IN_PROGRESS) inProgress = o;
		}
		if (inProgress != null) return inProgress;
		return orders.isEmpty() ? null : orders.get(orders.size() - 1);
	}
}
