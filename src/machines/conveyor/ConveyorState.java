package machines.conveyor;

// shared state written by ConveyorVizWorker, read by ConveyorCanvas
public class ConveyorState {
	public static volatile boolean MOTOR_ON = false;
	public static volatile boolean BOTTLE_AT_POS1 = false;
	public static volatile boolean BOTTLE_LEFT_POS5 = false;

	// horizontal scroll offset for the belt-marks animation, advanced by the
	// canvas on every repaint while the motor is running
	public static volatile double BELT_OFFSET = 0;
}
