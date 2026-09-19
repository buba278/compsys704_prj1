package machines.conveyor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class ConveyorCanvas extends JPanel {

	private static final int INDICATOR_X = 10;
	private static final int LABEL_X = 30;
	private static final int MOTOR_Y = 10;
	private static final int POS1_Y = 35;
	private static final int POS5_Y = 60;

	private static final int BELT_LEFT = 20;
	private static final int BELT_RIGHT = 600;
	private static final int BELT_Y = 130;
	private static final int BELT_HEIGHT = 36;
	private static final int MARK_SPACING = 24;

	private static final int BELT_LENGTH = BELT_RIGHT - BELT_LEFT;
	private static final int POS1_X = BELT_LEFT + BELT_LENGTH / 4;        
	private static final int POS5_X = BELT_LEFT + (3 * BELT_LENGTH) / 4;  

	private static final double SCROLL_SPEED = 1.5;

	private static final double EASE_FACTOR = 0.15;

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255); // matches FillerCanvas/RotaryTableCanvas
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);
	private static final Color EMPTY_BOTTLE_COLOR = Color.WHITE; // bottle hasn't been filled yet

	@Override
	protected void paintComponent(Graphics gOrig) {
		super.paintComponent(gOrig);
		Graphics2D g = (Graphics2D) gOrig;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(ConveyorState.MOTOR_ON ? Color.GREEN : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, MOTOR_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Motor Running", LABEL_X, MOTOR_Y + 12);

		g.setColor(ConveyorState.BOTTLE_AT_POS1 ? LIQUID_A_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS1_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Bottle at Pos 1", LABEL_X, POS1_Y + 12);

		g.setColor(ConveyorState.BOTTLE_LEFT_POS5 ? LIQUID_B_COLOR : Color.LIGHT_GRAY);
		g.fillOval(INDICATOR_X, POS5_Y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawString("Bottle Left Pos 5", LABEL_X, POS5_Y + 12);

		// the belt frame
		g.setColor(Color.DARK_GRAY);
		g.drawRect(BELT_LEFT, BELT_Y, BELT_RIGHT - BELT_LEFT, BELT_HEIGHT);

		if (ConveyorState.MOTOR_ON) {
			ConveyorState.BELT_OFFSET = (ConveyorState.BELT_OFFSET + SCROLL_SPEED) % MARK_SPACING;
		}
		g.setColor(Color.LIGHT_GRAY);
		for (int x = (int) (BELT_LEFT + ConveyorState.BELT_OFFSET - MARK_SPACING); x < BELT_RIGHT; x += MARK_SPACING) {
			if (x > BELT_LEFT) {
				g.drawLine(x, BELT_Y, x, BELT_Y + BELT_HEIGHT);
			}
		}

		// tick marks for Pos 1 / Pos 5 so their location on the belt is visible
		g.setColor(Color.DARK_GRAY);
		g.drawLine(POS1_X, BELT_Y - 4, POS1_X, BELT_Y + BELT_HEIGHT + 4);
		g.drawLine(POS5_X, BELT_Y - 4, POS5_X, BELT_Y + BELT_HEIGHT + 4);

		g.setColor(Color.BLACK);
		g.drawString("Loading end", BELT_LEFT, BELT_Y - 6);
		g.drawString("Pos 1", POS1_X - 12, BELT_Y + BELT_HEIGHT + 16);
		g.drawString("Pos 5", POS5_X - 12, BELT_Y + BELT_HEIGHT + 16);
		g.drawString("Collection end", BELT_RIGHT - 90, BELT_Y - 6);

		String onTable = "On Rotary Table: " + ConveyorState.BOTTLES_ON_TABLE;
		int midX = (POS1_X + POS5_X) / 2;
		java.awt.FontMetrics fm = g.getFontMetrics();
		g.drawString(onTable, midX - fm.stringWidth(onTable) / 2, BELT_Y + BELT_HEIGHT / 2 + 5);


		if (ConveyorState.LEFT_BOTTLE_ACTIVE) {
			double leftTarget = ConveyorState.LEFT_STEP / (double) ConveyorState.TRAVEL_STEPS;
			if (ConveyorState.MOTOR_ON) {
				ConveyorState.LEFT_PROGRESS += (leftTarget - ConveyorState.LEFT_PROGRESS) * EASE_FACTOR;
			}
			int x = (int) (BELT_LEFT + (POS1_X - BELT_LEFT) * ConveyorState.LEFT_PROGRESS);
			drawBottle(g, x, EMPTY_BOTTLE_COLOR);
		}

		// bottle travelling to the collection end, coloured by its actual fill ratio
		if (ConveyorState.RIGHT_BOTTLE_ACTIVE) {
			double rightTarget = ConveyorState.RIGHT_STEP / (double) ConveyorState.TRAVEL_STEPS;
			if (ConveyorState.MOTOR_ON) {
				ConveyorState.RIGHT_PROGRESS += (rightTarget - ConveyorState.RIGHT_PROGRESS) * EASE_FACTOR;
			}
			int x = (int) (POS5_X + (BELT_RIGHT - POS5_X) * ConveyorState.RIGHT_PROGRESS);
			drawFilledBottle(g, x, ConveyorState.RIGHT_RATIO_A);
		}
	}

	private void drawFilledBottle(Graphics2D g, int centerX, int ratioA) {
		int width = 14;
		int height = 26;
		int baseY = BELT_Y + 4;
		int left = centerX - width / 2;
		int top = baseY - height;

		int aHeight = (int) Math.round(height * (ratioA / 100.0));
		g.setColor(LIQUID_A_COLOR);
		g.fillRect(left, top + (height - aHeight), width, aHeight);
		if (aHeight < height) {
			g.setColor(LIQUID_B_COLOR);
			g.fillRect(left, top, width, height - aHeight);
		}
		g.setColor(Color.BLACK);
		g.drawRect(left, top, width, height);

		Color capColor = ratioA < 100 ? LIQUID_B_COLOR : LIQUID_A_COLOR;
		g.setColor(capColor);
		g.fillRect(centerX - 3, top - 6, 6, 6);
		g.setColor(Color.BLACK);
		g.drawRect(centerX - 3, top - 6, 6, 6);
	}

	private void drawBottle(Graphics2D g, int centerX, Color color) {
		int width = 14;
		int height = 26;
		int baseY = BELT_Y + 4;
		int left = centerX - width / 2;
		int top = baseY - height;

		g.setColor(color);
		g.fillRect(left, top, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(left, top, width, height);
		g.setColor(color);
		g.fillRect(centerX - 3, top - 6, 6, 6);
		g.setColor(Color.BLACK);
		g.drawRect(centerX - 3, top - 6, 6, 6);
	}
}