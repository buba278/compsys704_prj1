package machines.rotarytable;

// shared state written by RotaryTableVizWorker, read by RotaryTableCanvas
public class RotaryTableState {
	public static volatile boolean TABLE_ALIGNED = true; // mirrors tableAlignedWithSensor
	public static volatile boolean BOTTLE_AT_POS5 = false; // mirrors bottleAtPos5

	// 0=Entry/Pos1..4=Exit/Pos5; 0 = empty, else a bottle id, for a stable colour per slot
	public static final int[] SLOT_BOTTLE_ID = new int[5];

	// timestamp of each slot's last bottle change, so the canvas can flash slots that just moved
	public static final long[] SLOT_LAST_MOVE_MS = new long[5];

	// cycled by id % length so the same bottle stays identifiable across slots
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
