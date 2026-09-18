package run;

/** True synchronized indexing state for the Rotary Table: 5 fixed physical slots (0=Entry/Pos1..4=Exit/Pos5) advance together in one atomic step.
 *  Plain static fields are safe (not synchronized/volatile) since SystemJ threads in one clock domain run cooperatively, never as real concurrent OS threads.
 *  Loading Pos 1 / unloading Pos 5 are stationary conveyor pushes independent of rotation (Geneva-style indexing) - only doIndex() is gated by canIndex().
 */
public class RotaryIndexState {
	public static final int NUM_SLOTS = 5;

	// 0 = slot empty, else a per-bottle sequence number so the viz can keep a
	// bottle visually identifiable (same colour) as it moves across slots.
	private static final int[] bottleId = new int[NUM_SLOTS];
	// Has this slot's station finished its work (or abandoned the bottle) for
	// the current dwell period? Reset to false for every slot on every index
	// event. An empty slot always counts as done - see isDone-equivalent
	// logic folded into canIndex() below.
	private static final boolean[] done = new boolean[NUM_SLOTS];
	// Travels with the bottle across slots (shifted alongside bottleId) -
	// set true if any station abandoned this bottle (unfilled/no lid/
	// uncapped), so the Sorter can reject it for a real reason.
	private static final boolean[] defective = new boolean[NUM_SLOTS];

	private static int nextBottleSeq = 1;

	public static int getBottleId(int slot) { return bottleId[slot]; }
	public static boolean isOccupied(int slot) { return bottleId[slot] != 0; }

	public static boolean isEntryFree() { return bottleId[0] == 0; }

	// Loads a newly-arrived bottle into the Entry slot immediately - a
	// conveyor push, not a rotation. Returns the id assigned, so the caller
	// can assert the acceptance signal for this specific bottle.
	public static int loadEntry() {
		int id = nextBottleSeq++;
		bottleId[0] = id;
		defective[0] = false;
		done[0] = false;
		return id;
	}

	public static void markDone(int slot) { done[slot] = true; }
	public static void markDefective(int slot) { defective[slot] = true; }
	public static boolean isDefective(int slot) { return defective[slot]; }

	// The whole table may rotate one step when: the Exit slot is empty
	// (otherwise rotating would silently drop an unpicked bottle), every
	// currently-occupied working slot (Filler/Lid Placer/Capper) has been
	// marked done, and there's actually something to move.
	public static boolean canIndex() {
		if (bottleId[4] != 0) return false;
		for (int i = 1; i <= 3; i++) {
			if (bottleId[i] != 0 && !done[i]) return false;
		}
		return bottleId[0] != 0 || bottleId[1] != 0 || bottleId[2] != 0 || bottleId[3] != 0;
	}

	// Rotates the disc one step: shifts every slot's occupant to slot+1 and
	// clears all done flags. Slot 0 comes out empty - a bottle already
	// staged on the conveyor loads into it again immediately (loadEntry()),
	// independent of this call.
	public static void doIndex() {
		for (int i = 4; i >= 1; i--) {
			bottleId[i] = bottleId[i - 1];
			defective[i] = defective[i - 1];
			done[i] = false;
		}
		bottleId[0] = 0;
		defective[0] = false;
		done[0] = false;
	}

	// Conveyor confirms pickup from the Exit slot - also just a stationary
	// push, independent of rotation.
	public static void takeExit() { bottleId[4] = 0; }
}
