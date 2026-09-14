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
	private static final int BELT_RIGHT = 280;
	private static final int BELT_Y = 150;
	private static final int BELT_HEIGHT = 36;
	private static final int MARK_SPACING = 24;

	// Purely visual: how far the belt marks scroll per repaint while the motor is on.
	private static final double SCROLL_SPEED = 3.0;

	private static final Color LIQUID_A_COLOR = new Color(135, 190, 255); // matches FillerCanvas/RotaryTableCanvas
	private static final Color LIQUID_B_COLOR = new Color(255, 195, 130);

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

		// scrolling marks give the belt a sense of motion while the motor runs
		if (ConveyorState.MOTOR_ON) {
			ConveyorState.BELT_OFFSET = (ConveyorState.BELT_OFFSET + SCROLL_SPEED) % MARK_SPACING;
		}
		g.setColor(Color.LIGHT_GRAY);
		for (int x = (int) (BELT_LEFT - ConveyorState.BELT_OFFSET); x < BELT_RIGHT; x += MARK_SPACING) {
			if (x > BELT_LEFT) {
				g.drawLine(x, BELT_Y, x, BELT_Y + BELT_HEIGHT);
			}
		}

		g.setColor(Color.BLACK);
		g.drawString("Pos 1 (from Loader)", BELT_LEFT, BELT_Y - 6);
		g.drawString("Pos 5 (to Rotary Table)", BELT_RIGHT - 130, BELT_Y - 6);

		if (ConveyorState.BOTTLE_AT_POS1) {
			drawBottle(g, BELT_LEFT + 10, BELT_Y - 6, LIQUID_A_COLOR);
		}
		if (ConveyorState.BOTTLE_LEFT_POS5) {
			drawBottle(g, BELT_RIGHT - 20, BELT_Y - 6, LIQUID_B_COLOR);
		}
	}

	private void drawBottle(Graphics2D g, int x, int y, Color color) {
		g.setColor(color);
		g.fillRect(x, y - 14, 12, 20);
		g.setColor(Color.BLACK);
		g.drawRect(x, y - 14, 12, 20);
	}
}
