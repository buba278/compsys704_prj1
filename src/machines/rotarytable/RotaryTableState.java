package machines.rotarytable;

// shared state written by RotaryTableVizWorker, read by RotaryTableCanvas
public class RotaryTableState {
	public static volatile boolean TABLE_ALIGNED = true;
	public static volatile boolean BOTTLE_AT_POS5 = false;

	// Mirrors run.RotaryIndexState's 5 slots (0=Entry/Pos1..4=Exit/Pos5); 0 = empty, else a per-bottle sequence number for a stable colour across slots.
	public static final int[] SLOT_BOTTLE_ID = new int[5];

	// Timestamp each slot's bottle id last changed (0 = never) - lets the canvas highlight every slot that moved on the same index event.
	public static final long[] SLOT_LAST_MOVE_MS = new long[5];

	// One colour per active bottle id (cycled by id % length) so the same
	// bottle stays visually identifiable as it advances across slots.
	private static final java.awt.Color[] BOTTLE_COLORS = {
			new java.awt.Color(220, 70, 70),   // red
			new java.awt.Color(70, 160, 90),   // green
			new java.awt.Color(70, 110, 220),  // blue
			new java.awt.Color(200, 150, 40),  // amber
			new java.awt.Color(150, 90, 200),  // purple
	};

	public static java.awt.Color colorForBottle(int bottleId) {
		if (bottleId <= 0) return java.awt.Color.WHITE;
		return BOTTLE_COLORS[(bottleId - 1) % BOTTLE_COLORS.length];
	}

	private static final int[] lastSlotBottleId = new int[5];

	public static void recordSlotBottleId(int slot, int bottleId) {
		if (bottleId != lastSlotBottleId[slot]) {
			lastSlotBottleId[slot] = bottleId;
			SLOT_LAST_MOVE_MS[slot] = System.currentTimeMillis();
		}
		SLOT_BOTTLE_ID[slot] = bottleId;
	}
}
