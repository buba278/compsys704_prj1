package machines.conveyor;

// shared state written by ConveyorVizWorker, read by ConveyorCanvas
public class ConveyorState {
	public static volatile boolean MOTOR_ON = false;
	public static volatile boolean BOTTLE_AT_POS1 = false;
	public static volatile boolean BOTTLE_LEFT_POS5 = false;

	// horizontal scroll offset for the belt-marks animation, advanced by the
	// canvas on every repaint while the motor is running
	public static volatile double BELT_OFFSET = 0;

	// --- animated bottle tracking -------------------------------------
	// How many of the plant's 8 travel ticks (see conveyorPlant.sysj) the
	// current bottle has actually completed. LEFT_STEP/RIGHT_STEP are
	// advanced by ConveyorVizWorker each time a real leftMovingE/
	// rightMovingE tick arrives from the model - they are ground truth,
	// not a guess. LEFT_PROGRESS/RIGHT_PROGRESS are what the canvas
	// actually draws: eased toward STEP/TRAVEL_STEPS every repaint (same
	// technique RotaryTableCanvas uses for CURRENT_ANGLE_DEG) so the motion
	// still looks smooth between ticks, but the bottle is guaranteed to
	// visually reach Pos 1 / the collection point in step with the model
	// instead of drifting out of sync with it.
	public static final int TRAVEL_STEPS = 8;

	public static volatile boolean LEFT_BOTTLE_ACTIVE = false;
	public static volatile int LEFT_STEP = 0;
	public static volatile double LEFT_PROGRESS = 0;   // 0 = loading point, 1 = Pos 1

	public static volatile boolean RIGHT_BOTTLE_ACTIVE = false;
	public static volatile int RIGHT_STEP = 0;
	public static volatile double RIGHT_PROGRESS = 0;  // 0 = Pos 5, 1 = collection point

	// called when the plant reports a new bottle has been queued at the
	// loading end (bottleEnteredE)
	public static synchronized void startLeftTravel() {
		LEFT_BOTTLE_ACTIVE = true;
		LEFT_STEP = 0;
		LEFT_PROGRESS = 0;
	}

	// called once per real leftMovingE tick from the plant
	public static synchronized void advanceLeftStep() {
		if (LEFT_STEP < TRAVEL_STEPS) {
			LEFT_STEP++;
		}
	}

	// called when the plant reports a bottle has been handed off from the
	// Rotary Table at Pos 5 (bottleReceivedE)
	public static synchronized void startRightTravel() {
		RIGHT_BOTTLE_ACTIVE = true;
		RIGHT_STEP = 0;
		RIGHT_PROGRESS = 0;
	}

	// called once per real rightMovingE tick from the plant
	public static synchronized void advanceRightStep() {
		if (RIGHT_STEP < TRAVEL_STEPS) {
			RIGHT_STEP++;
		}
	}

	// called on bottleAtPos1E - true means the bottle has arrived at Pos 1
	// and is being handed to the Rotary Table; false means it has been
	// taken away and there is nothing left to draw on this segment
	public static synchronized void setBottleAtPos1(boolean status) {
		BOTTLE_AT_POS1 = status;
		if (status) {
			LEFT_BOTTLE_ACTIVE = true;
			LEFT_STEP = TRAVEL_STEPS;
			LEFT_PROGRESS = 1;
		} else {
			LEFT_BOTTLE_ACTIVE = false;
		}
	}

	// called on bottleLeftPos5E - true means the bottle has arrived at the
	// collection point and is waiting for the Sorter; false means the
	// (artificial) sort-finished signal has removed it
	public static synchronized void setBottleLeftPos5(boolean status) {
		BOTTLE_LEFT_POS5 = status;
		if (status) {
			RIGHT_BOTTLE_ACTIVE = true;
			RIGHT_STEP = TRAVEL_STEPS;
			RIGHT_PROGRESS = 1;
		} else {
			RIGHT_BOTTLE_ACTIVE = false;
		}
	}
}