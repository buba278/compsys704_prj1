package run;

/** Stands in for a real Bottle Loader station: a conveyorPlant.sysj thread adds to this on bottlesNeeded, and the auto-loader consumes one at a time as the belt frees up.
 *  Only touched within ConveyorPlantCD's own process - Java statics don't cross clock-domain processes.
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
