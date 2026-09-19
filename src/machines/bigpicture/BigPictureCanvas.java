package machines.bigpicture;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import digitaltwin.BigPictureTwinState;
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

public class BigPictureCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	static final int CANVAS_W = 1220;
	static final int CANVAS_H = 780;

	private static final int TABLE_CENTER_X = 440;
	private static final int TABLE_CENTER_Y = 430;
	private static final int TABLE_RADIUS = 130;
	private static final int SLOT_RADIUS = 18;
	private static final int TOP_POSITION_INDEX = 2; // slot index drawn at the top of the dial (pos3)

	private static final double EASE_FACTOR = 0.12; 

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255);
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);
	private static final Color STATION_BLUE = new Color(30, 40, 60);
	private static final Color IDLE_GREEN = new Color(60, 170, 90);
	private static final Color WAITING_YELLOW = new Color(230, 180, 40);
	private static final Color FAULT_RED = new Color(210, 60, 60);

	private static final String TITLE_CONVEYOR = "Conveyor Belt";
	private static final String TITLE_ROTARY_TABLE = "Rotary Table";
	private static final String TITLE_FILLER = "Filler";
	private static final String TITLE_LID_PLACER = "Lid Placer";
	private static final String TITLE_CAPPER = "Capper Visualizer";
	private static final String TITLE_LABELLER = "Labeller Visualizer";
	private static final String TITLE_SORTER = "Sorter Visualizer";

	// recomputed each paint by drawBottleProductionList(); parallel lists (not a
	// map) since draw order also determines display order
	private final List<Rectangle> bottleListHitboxes = new ArrayList<>();
	private final List<String> bottleListProductIds = new ArrayList<>();

	public BigPictureCanvas() {
		MouseAdapter clickHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String clickedProduct = bottleListProductIdAt(e.getX(), e.getY());
				if (clickedProduct != null) {
					showBottleDetail(clickedProduct);
					return;
				}
				String title = stationTitleAt(e.getX(), e.getY());
				if (title != null) WindowFocuser.focus(title);
			}
		};
		addMouseListener(clickHandler);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				boolean overStation = bottleListProductIdAt(e.getX(), e.getY()) != null
						|| stationTitleAt(e.getX(), e.getY()) != null;
				setCursor(Cursor.getPredefinedCursor(overStation ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
			}
		});
	}

	private String bottleListProductIdAt(int x, int y) {
		for (int i = 0; i < bottleListHitboxes.size(); i++) {
			if (bottleListHitboxes.get(i).contains(x, y)) return bottleListProductIds.get(i);
		}
		return null;
	}

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

		bottleListHitboxes.clear();
		bottleListProductIds.clear();

		drawHeader(g);
		drawFaultBanner(g);
		drawBottleProductionList(g);
		drawLegend(g);

		if (ConveyorState.MOTOR_ON) {
			ConveyorState.BELT_OFFSET = (ConveyorState.BELT_OFFSET + 1.5) % 24;
		}

		int[] pos1 = slotPos(0);
		int[] pos5 = slotPos(4);
		int beltY = pos1[1];

		drawLoadingSide(g, pos1, beltY);
		drawCollectionSide(g, pos5, beltY);
		drawRotaryTable(g);
		drawLabeller(g, pos5[0], beltY);
		drawSorter(g);
	}

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

	private static final long FAULT_BANNER_MS = 2000;

	private boolean prevFillerFault = false, prevLidFault = false, prevCapperFault = false;
	private long fillerFaultFlashMs = -1, lidFaultFlashMs = -1, capperFaultFlashMs = -1;

	private void drawFaultBanner(Graphics2D g) {
		String banner = activeFaultBanner();
		if (banner == null) return;

		int bx = 20, by = 62, bw = CANVAS_W - 40, bh = 24;
		g.setColor(FAULT_RED);
		g.fillRoundRect(bx, by, bw, bh, 8, 8);
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 13f));
		g.drawString(banner, bx + 12, by + bh - 7);
	}

	private String activeFaultBanner() {
		long now = System.currentTimeMillis();

		boolean fillerFault = FillerState.FAULT || FillerState.BACKUP_ACTIVE;
		if (fillerFault && !prevFillerFault) fillerFaultFlashMs = now;
		prevFillerFault = fillerFault;

		boolean lidFault = org.compsys704.States.FAULTED || org.compsys704.States.BACKUP_ACTIVE;
		if (lidFault && !prevLidFault) lidFaultFlashMs = now;
		prevLidFault = lidFault;

		boolean capperFault = CapperState.FAULTED || CapperState.BACKUP_ACTIVE;
		if (capperFault && !prevCapperFault) capperFaultFlashMs = now;
		prevCapperFault = capperFault;

		if (now - fillerFaultFlashMs < FAULT_BANNER_MS) return "FAULT - Filling Unit: switched to backup unit automatically";
		if (now - lidFaultFlashMs < FAULT_BANNER_MS) return "FAULT - Lid Placer: switched to backup unit automatically";
		if (now - capperFaultFlashMs < FAULT_BANNER_MS) return "FAULT - Cap Screwing: switched to backup unit automatically";
		return null;
	}

	// --- digital twin: "Bottles in Production" panel + per-bottle detail popup --------

	private void drawBottleProductionList(Graphics2D g) {
		List<BigPictureTwinState.ProductInfo> products = BigPictureTwinState.getActiveProducts();

		int x = 20, y = 94;
		int lineHeight = 16;
		int maxLines = 6;
		int shown = Math.min(products.size(), maxLines);
		int boxW = 230;
		// title row + one line per shown bottle, plus an extra line for "+N more" if truncated
		int boxH = 20 + Math.max(shown, 1) * lineHeight + (products.size() > maxLines ? lineHeight : 0) + 6;

		g.setColor(new Color(245, 245, 245));
		g.fillRoundRect(x, y, boxW, boxH, 8, 8);
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(x, y, boxW, boxH, 8, 8);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		g.drawString("Bottles in Production:", x + 8, y + 16);

		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
		if (products.isEmpty()) {
			g.setColor(Color.GRAY);
			g.drawString("(none)", x + 8, y + 16 + lineHeight);
			return;
		}

		FontMetrics fm = g.getFontMetrics();
		for (int i = 0; i < shown; i++) {
			BigPictureTwinState.ProductInfo p = products.get(i);
			int ly = y + 16 + (i + 1) * lineHeight;
			g.setColor(colorForProduct(p.productId));
			String line = p.productId + " — " + capitalize(p.currentWorkstation);
			g.drawString(line, x + 8, ly);

			bottleListHitboxes.add(new Rectangle(x + 6, ly - 11, fm.stringWidth(line) + 4, 14));
			bottleListProductIds.add(p.productId);
		}
		if (products.size() > maxLines) {
			g.setColor(Color.GRAY);
			g.drawString("+" + (products.size() - maxLines) + " more", x + 8, y + 16 + (shown + 1) * lineHeight);
		}
	}

	// deterministic per-productId colour, since the twin has no native colour of its
	// own (unlike RotaryTableState.colorForBottle, keyed by the viz-side bottleId)
	private Color colorForProduct(String productId) {
		int hash = productId.hashCode();
		float hue = ((hash % 360) + 360) % 360 / 360f;
		return Color.getHSBColor(hue, 0.55f, 0.7f);
	}

	private String capitalize(String s) {
		if (s == null || s.isEmpty()) return s;
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	private void showBottleDetail(String productId) {
		BigPictureTwinState.ProductInfo p = null;
		for (BigPictureTwinState.ProductInfo info : BigPictureTwinState.getActiveProducts()) {
			if (info.productId.equals(productId)) {
				p = info;
				break;
			}
		}
		if (p == null) return; // bottle moved on (archived/sorted) since the list was drawn

		StringBuilder sb = new StringBuilder("<html>");
		sb.append("<b>").append(p.productId).append("</b><br>");
		sb.append("Volume: ").append((int) p.volumeMl).append(" ml<br>");
		int ratioA = (int) p.liquidRatio;
		sb.append("Ratio: ").append(ratioA).append(":").append(100 - ratioA).append("<br>");
		sb.append("Workstation: ").append(capitalize(p.currentWorkstation)).append("<br>");
		sb.append("Status: ").append(p.status).append("<br>");
		sb.append("Fault: ").append(p.hasFault ? "Yes" : "No").append("<br><br>");
		sb.append("<b>Outcomes so far:</b><br>");
		if (p.stationOutcomes.isEmpty()) {
			sb.append("(none yet)");
		} else {
			for (Map.Entry<String, String> entry : p.stationOutcomes.entrySet()) {
				sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
			}
		}
		sb.append("</html>");

		JOptionPane.showMessageDialog(this, sb.toString(), "Bottle " + productId, JOptionPane.INFORMATION_MESSAGE);
	}

	private void drawLegend(Graphics2D g) {
		int x = 20, y = CANVAS_H - 50;
		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 11f));
		legendDot(g, x, y, IDLE_GREEN, "Active");
		legendDot(g, x + 110, y, WAITING_YELLOW, "Idle");
		legendDot(g, x + 210, y, FAULT_RED, "Fault");
		g.setColor(Color.GRAY);
		g.drawString("Click a station to bring its window forward, or a bottle in the list above for its digital twin.", x, y + 20);
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
		drawStatusDot(g, beltLeft + 90, beltY - beltHeight / 2 - 38, ConveyorState.LEFT_BOTTLE_ACTIVE);

		drawBelt(g, beltLeft, beltRight, beltY, beltHeight, "Loading Conveyor", false);

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

		drawBelt(g, beltLeft, beltRight, beltY, beltHeight, "Exit Conveyor", true);

		if (ConveyorState.RIGHT_BOTTLE_ACTIVE) {
			double target = ConveyorState.RIGHT_STEP / (double) ConveyorState.TRAVEL_STEPS;
			if (ConveyorState.MOTOR_ON) {
				ConveyorState.RIGHT_PROGRESS += (target - ConveyorState.RIGHT_PROGRESS) * EASE_FACTOR;
			}
			int bx = (int) (beltLeft + (beltRight - beltLeft) * ConveyorState.RIGHT_PROGRESS);
			drawFilledBottleOnBelt(g, bx, beltY, ConveyorState.RIGHT_RATIO_A);
		}
	}

	private void drawBelt(Graphics2D g, int left, int right, int y, int height, String label, boolean showMotorDot) {
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
		if (showMotorDot) drawStatusDot(g, left - 14, y, ConveyorState.MOTOR_ON);
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
			double spokeAngleDeg = angleDeg(i);
			double spokeRad = Math.toRadians(spokeAngleDeg);
			int sx = (int) (TABLE_CENTER_X + TABLE_RADIUS * Math.cos(spokeRad));
			int sy = (int) (TABLE_CENTER_Y + TABLE_RADIUS * Math.sin(spokeRad));
			g.drawLine(TABLE_CENTER_X, TABLE_CENTER_Y, sx, sy);
		}

		for (int i = 0; i < 6; i++) {
			int[] p = slotPos(i);
			boolean isPos5 = i == 4;

			int bottleId = i < 5 ? RotaryTableState.SLOT_BOTTLE_ID[i] : 0;
			boolean isCurrentStage = bottleId != 0;

			Color fill = Color.WHITE;
			if (isCurrentStage) fill = RotaryTableState.colorForBottle(bottleId);
			else if (isPos5 && RotaryTableState.BOTTLE_AT_POS5) fill = LIQUID_A_COLOR;

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

		long nowFault = System.currentTimeMillis();
		boolean fillerFaultFlash = nowFault - fillerFaultFlashMs < FAULT_BANNER_MS;
		boolean lidFaultFlash = nowFault - lidFaultFlashMs < FAULT_BANNER_MS;
		boolean capperFaultFlash = nowFault - capperFaultFlashMs < FAULT_BANNER_MS;

		stationLabel(g, slotPos(1), "Filling Unit\n(Pos 2)", fillerFaultFlash, RotaryTableState.SLOT_BOTTLE_ID[1] != 0);
		stationLabel(g, slotPos(2), "Lid Placer\n(Pos 3)", lidFaultFlash, RotaryTableState.SLOT_BOTTLE_ID[2] != 0);
		stationLabel(g, slotPos(3), "Cap Screwing\n(Pos 4)", capperFaultFlash, RotaryTableState.SLOT_BOTTLE_ID[3] != 0);

		g.setColor(STATION_BLUE);
		g.setFont(g.getFont().deriveFont(java.awt.Font.BOLD, 12f));
		g.drawString("Table Aligned", TABLE_CENTER_X - 40, TABLE_CENTER_Y + TABLE_RADIUS + 60);
		drawStatusDot(g, TABLE_CENTER_X - 60, TABLE_CENTER_Y + TABLE_RADIUS + 56, RotaryTableState.TABLE_ALIGNED);

		g.setFont(g.getFont().deriveFont(java.awt.Font.PLAIN, 12f));
		String[] slotNames = {"Entry", "Filler", "Lid Placer", "Capper", "Exit"};
		for (int i = 0; i < 5; i++) {
			int bottleId = RotaryTableState.SLOT_BOTTLE_ID[i];
			g.setColor(bottleId == 0 ? Color.GRAY : RotaryTableState.colorForBottle(bottleId));
			String text = slotNames[i] + ": " + (bottleId == 0 ? "empty" : "Bottle #" + bottleId);
			FontMetrics fm = g.getFontMetrics();
			g.drawString(text, TABLE_CENTER_X - fm.stringWidth(text) / 2,
					TABLE_CENTER_Y + TABLE_RADIUS + 78 + i * 16);
		}
	}

	// small label + status dot floating near a station's slot
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

	// green = active, yellow = idle; fault stations call the colour overload with FAULT_RED directly
	private void drawStatusDot(Graphics2D g, int x, int y, boolean active) {
		drawStatusDot(g, x, y, active ? IDLE_GREEN : WAITING_YELLOW);
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
			drawFilledBottleRect(g, cx, cy + 15, 16, 24, LabellerState.LIQUID_RATIO, Color.BLACK);
		}
	}

	// top-anchored filled bottle (labeller/sorter style); drawFilledBottleOnBelt
	// is the centre-anchored, always-black-bordered equivalent for the belt
	private void drawFilledBottleRect(Graphics2D g, int centerX, int top, int w, int h, int ratioA, Color borderColor) {
		int left = centerX - w / 2;
		int aHeight = (int) Math.round(h * (ratioA / 100.0));
		g.setColor(LIQUID_A_COLOR);
		g.fillRect(left, top + (h - aHeight), w, aHeight);
		if (aHeight < h) {
			g.setColor(LIQUID_B_COLOR);
			g.fillRect(left, top, w, h - aHeight);
		}
		g.setColor(borderColor);
		g.drawRect(left, top, w, h);
	}

	// the sorter has no ratio signal of its own, so borrow it from the twin's product
	// list by workstation; falls back to 50:50 if there's no match (e.g. no twin server)
	private int sorterBottleRatio() {
		for (BigPictureTwinState.ProductInfo p : BigPictureTwinState.getActiveProducts()) {
			if ("sorter".equalsIgnoreCase(p.currentWorkstation)) return (int) p.liquidRatio;
		}
		return 50;
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
		drawStatusDot(g, cx - 75, cy - 103,
				SorterState.BOTTLE_DEFECTIVE ? FAULT_RED : (SorterState.BOTTLE_PRESENT ? IDLE_GREEN : WAITING_YELLOW));

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
			Color border = SorterState.BOTTLE_DEFECTIVE ? FAULT_RED : Color.BLACK;
			drawFilledBottleRect(g, cx, cy - 49, 16, 24, sorterBottleRatio(), border);
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
