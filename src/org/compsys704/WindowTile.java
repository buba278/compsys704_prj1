package org.compsys704;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * Each station's GUI is its own top-level JFrame in its own JVM process, so
 * there's no single place to lay them all out at once - every panel has to
 * know its own assigned spot. Positions are fractions of the current screen
 * size (not fixed pixels), so the same layout scales to any machine's
 * resolution. Grouped by physical flow rather than a plain grid, so windows
 * that are adjacent in the pipeline end up adjacent on screen too: top row
 * is the two wide "entry point" windows (Conveyor load/collect, the Purchase
 * Order System); middle row is the three rotary-table stations in process
 * order (Filler = Station 2, Lid Placer = Station 3, Capper = Station 4)
 * plus the Rotary Table itself; bottom row is what comes after the table
 * (Labeller, then Sorter). See StationHeader for the matching on-panel
 * "STATION N" labels.
 */
public class WindowTile {
	private static final java.util.Map<String, double[]> POSITIONS = new java.util.HashMap<String, double[]>();
	static {
		POSITIONS.put("conveyor",    new double[]{ 0.00, 0.00 });
		POSITIONS.put("pos",         new double[]{ 0.30, 0.00 });
		POSITIONS.put("filler",      new double[]{ 0.00, 0.33 });
		POSITIONS.put("loader",      new double[]{ 0.25, 0.33 });
		POSITIONS.put("capper",      new double[]{ 0.50, 0.33 });
		POSITIONS.put("rotarytable", new double[]{ 0.75, 0.33 });
		POSITIONS.put("labeller",    new double[]{ 0.00, 0.66 });
		POSITIONS.put("sorter",      new double[]{ 0.25, 0.66 });
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
