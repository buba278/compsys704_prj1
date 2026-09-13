package machines.rotarytable;

// shared state written by RotaryTableVizWorker, read by RotaryTableCanvas
public class RotaryTableState {
	public static volatile boolean TABLE_ALIGNED = true;
	public static volatile boolean BOTTLE_AT_POS5 = false;
	public static volatile boolean CAP_ON_BOTTLE_AT_POS1 = false;

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
