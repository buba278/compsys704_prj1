package run;

/** Tracks how many bottles are currently on the Rotary Table, from the
 *  Conveyor's own point of view: incremented when the left segment hands a
 *  bottle off at Pos 1, decremented when the right segment receives one back
 *  at Pos 5. Only ever touched from within ConveyorPlantCD's own process
 *  (two sibling threads within that one domain - see CLAUDE.md on why plain
 *  fields are safe there), so no cross-domain signal was needed to compute
 *  this, only to display it.
 */
public class TableOccupancy {
	private static int count = 0;

	public static int increment() { return ++count; }
	public static int decrement() { return --count; }
}
