package run;

/** Tracks bottles currently on the Rotary Table from the Conveyor's point of view: incremented at the Pos 1 handoff, decremented at the Pos 5 handoff.
 *  Only touched by two sibling threads within ConveyorPlantCD's own process, so plain fields (no synchronization) are safe.
 */
public class TableOccupancy {
	private static int count = 0;

	public static int increment() { return ++count; }
	public static int decrement() { return --count; }
}
