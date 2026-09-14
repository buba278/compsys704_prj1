package org.compsys704;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * Each station's GUI is its own top-level JFrame in its own JVM process, so
 * there's no single place to lay them all out at once - every panel has to
 * know its own assigned spot. Positions are fractions of the current screen
 * size (not fixed pixels), so the same layout scales to any machine's
 * resolution. Filler and the Purchase Order System are noticeably wider than
 * the rest (dev controls / order queue side panels), so they each get a
 * half-width slot in their own row instead of sharing a uniform grid cell
 * with the narrower panels - a uniform grid left them overlapping their
 * neighbours.
 */
public class WindowTile {
	private static final java.util.Map<String, double[]> POSITIONS = new java.util.HashMap<String, double[]>();
	static {
		POSITIONS.put("conveyor",    new double[]{ 0.00, 0.00 });
		POSITIONS.put("rotarytable", new double[]{ 0.33, 0.00 });
		POSITIONS.put("capper",      new double[]{ 0.66, 0.00 });
		POSITIONS.put("labeller",    new double[]{ 0.00, 0.33 });
		POSITIONS.put("sorter",      new double[]{ 0.33, 0.33 });
		POSITIONS.put("loader",      new double[]{ 0.66, 0.33 });
		POSITIONS.put("filler",      new double[]{ 0.00, 0.66 });
		POSITIONS.put("pos",         new double[]{ 0.50, 0.66 });
	}

	public static void place(JFrame frame, String key) {
		double[] frac = POSITIONS.get(key.toLowerCase());
		if (frac == null) {
			frame.setLocationRelativeTo(null);
			return;
		}
		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int x = screen.x + (int) (screen.width * frac[0]);
		int y = screen.y + (int) (screen.height * frac[1]);
		frame.setLocation(x, y);
	}
}
