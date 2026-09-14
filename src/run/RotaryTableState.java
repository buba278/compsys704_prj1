package run;

/** Lets multiple bottles be in flight on the Rotary Table at once. Three lanes
 *  each run the full Filler->Lid->Capper->Conveyor pipeline independently, but
 *  Filler/Lid Placer/Capper/the Conveyor handoff are each a single physical
 *  station - only one lane may be mid-offer to any of them at a time, so each
 *  is gated by a claim/release pair instead of every lane free-for-all-ing the
 *  same cross-domain signal. Safe as plain fields (not synchronized/volatile
 *  beyond what's needed for visibility) because SystemJ threads within one
 *  clock domain run cooperatively, one at a time per reaction, not as real
 *  concurrent OS threads - see CLAUDE.md.
 */
public class RotaryTableState {
	private static boolean fillerBusy, lidBusy, capperBusy, conveyorExitBusy;

	public static boolean tryClaimFiller() { if (fillerBusy) return false; fillerBusy = true; return true; }
	public static void releaseFiller() { fillerBusy = false; }

	public static boolean tryClaimLid() { if (lidBusy) return false; lidBusy = true; return true; }
	public static void releaseLid() { lidBusy = false; }

	public static boolean tryClaimCapper() { if (capperBusy) return false; capperBusy = true; return true; }
	public static void releaseCapper() { capperBusy = false; }

	public static boolean tryClaimConveyorExit() { if (conveyorExitBusy) return false; conveyorExitBusy = true; return true; }
	public static void releaseConveyorExit() { conveyorExitBusy = false; }

	public static boolean lane1Idle = true, lane2Idle = true, lane3Idle = true;

	public static boolean anyLaneIdle() { return lane1Idle || lane2Idle || lane3Idle; }
}
