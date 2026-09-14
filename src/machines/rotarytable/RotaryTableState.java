package machines.rotarytable;

// shared state written by RotaryTableVizWorker, read by RotaryTableCanvas
public class RotaryTableState {
	public static volatile boolean TABLE_ALIGNED = true;
	public static volatile boolean BOTTLE_AT_POS5 = false;
	public static volatile boolean CAP_ON_BOTTLE_AT_POS1 = false;

	// Which stage the bottle currently on the table is at - mirrors
	// rotaryTablePlant.sysj's per-bottle pipeline thread: 0 = idle/no
	// bottle being actively processed, 1 = at the Capper, 2 = at the
	// Filler (via the Coordinator), 3 = departing to Pos 5, 4 = at the
	// Lid Placer (Pos 3, between Filling and Cap Screwing).
	public static volatile int BOTTLE_STAGE = 0;

	public static final String[] STAGE_NAMES = {
			"Idle", "Cap Screwing (Pos4)", "Filling (Pos2)", "Departing to Pos5", "Lid Placing (Pos3)"
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
