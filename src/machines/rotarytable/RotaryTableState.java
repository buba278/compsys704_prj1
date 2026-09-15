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

	// When each lane's bottle last actually changed stage (System.currentTimeMillis(),
	// 0 = never) - lets the canvas briefly highlight only the lane that just moved,
	// instead of implying every bottle moves together on some shared "rotation" (it
	// doesn't: each lane advances independently as it claims/releases stations - see
	// CLAUDE.md's Rotary Table multi-bottle concurrency section). Replaces an earlier
	// CURRENT_ANGLE_DEG/TARGET_ANGLE_DEG spinning-disc animation that advanced on every
	// new-bottle trigger regardless of which lane (if any) actually moved as a result -
	// purely decorative and unrelated to the real per-lane state, which is exactly what
	// made it look like "the table rotated but only one bottle moved."
	public static final long[] LANE_LAST_MOVE_MS = new long[3];

	public static void triggerStep() {
		// kept as the rotaryTableTriggerE hook point (a bottle arrival was sensed) -
		// no visual effect of its own now; each lane's own stage change is what
		// drives its highlight, via recordLaneStage() below.
	}

	private static final int[] lastStage = new int[3];

	public static void recordLaneStage(int lane, int stage) {
		if (stage != lastStage[lane]) {
			lastStage[lane] = stage;
			LANE_LAST_MOVE_MS[lane] = System.currentTimeMillis();
		}
		LANE_STAGE[lane] = stage;
	}
}
