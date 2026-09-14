package machines.rotarytable;

// shared state written by RotaryTableVizWorker, read by RotaryTableCanvas
public class RotaryTableState {
	public static volatile boolean TABLE_ALIGNED = true;
	public static volatile boolean BOTTLE_AT_POS5 = false;
	public static volatile boolean CAP_ON_BOTTLE_AT_POS1 = false;

	// Which stage each of the three lanes' bottle is currently at - mirrors
	// rotaryTablePlant.sysj's three independent lane threads: 0 = idle/no
	// bottle in that lane, 1 = at the Capper, 2 = at the Filler (via the
	// Coordinator), 3 = departing to Pos 5, 4 = at the Lid Placer (Pos 3,
	// between Filling and Cap Screwing). Index 0/1/2 = lane 1/2/3.
	public static final int[] LANE_STAGE = new int[3];

	public static final String[] STAGE_NAMES = {
			"Idle", "Cap Screwing (Pos4)", "Filling (Pos2)", "Departing to Pos5", "Lid Placing (Pos3)"
	};

	// One colour per lane so the same bottle stays visually identifiable as
	// it moves between stations across the dial.
	public static final java.awt.Color[] LANE_COLORS = {
			new java.awt.Color(220, 70, 70),   // lane 1 - red
			new java.awt.Color(70, 160, 90),   // lane 2 - green
			new java.awt.Color(70, 110, 220),  // lane 3 - blue
	};

	// Rotation angle of the table, in degrees. TARGET_ANGLE_DEG advances by one
	// 60-degree step every time a rotaryTableTriggerE event arrives; the canvas
	// eases CURRENT_ANGLE_DEG toward it on each repaint so the table appears to
	// turn rather than jump straight to the next position.
	public static volatile double CURRENT_ANGLE_DEG = 0;
	public static volatile double TARGET_ANGLE_DEG = 0;

	private static final double STEP_DEG = 60.0;

	public static void triggerStep() {
		TARGET_ANGLE_DEG += STEP_DEG;
	}
}
