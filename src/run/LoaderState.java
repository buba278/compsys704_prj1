package run;

/** Stands in for a real Bottle Loader station: conveyorPlant.sysj's own
 *  bridging thread adds to this when the Coordinator's bottlesNeeded signal
 *  arrives (once per order), and its auto-loader thread consumes one at a
 *  time as the belt frees up. Only ever touched from within ConveyorPlantCD's
 *  own process - Java statics don't cross clock-domain processes (see
 *  CLAUDE.md), so this can't be shared directly with coordinator.sysj.
 */
public class LoaderState {
	private static int pendingBottles = 0;

	public static void addPending(int count) { pendingBottles += count; }

	public static boolean tryConsumePending() {
		if (pendingBottles <= 0) return false;
		pendingBottles--;
		return true;
	}
}
