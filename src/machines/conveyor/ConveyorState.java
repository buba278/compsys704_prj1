package machines.conveyor;

public class ConveyorState {
	public static volatile boolean MOTOR_ON = false;
	public static volatile boolean BOTTLE_AT_POS1 = false;
	public static volatile boolean BOTTLE_LEFT_POS5 = false;
	public static volatile int BOTTLES_ON_TABLE = 0;

	public static volatile double BELT_OFFSET = 0;

	public static final int TRAVEL_STEPS = 8; // animation steps for a bottle crossing a belt segment

	public static volatile boolean LEFT_BOTTLE_ACTIVE = false;
	public static volatile int LEFT_STEP = 0;
	public static volatile double LEFT_PROGRESS = 0;   // 0 = loading point, 1 = Pos 1

	public static volatile boolean RIGHT_BOTTLE_ACTIVE = false;
	public static volatile int RIGHT_STEP = 0;
	public static volatile double RIGHT_PROGRESS = 0;  // 0 = Pos 5, 1 = collection point

	public static volatile int RIGHT_RATIO_A = 50;

	// called when a bottle is queued at the loading end (bottleEnteredE)
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

	// called when a bottle is handed off from the rotary table at pos 5 (bottleReceivedE)
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

	// bottleAtPos1E: true = bottle arrived at pos 1, handing to the rotary table; false = taken away
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

	// bottleLeftPos5E: true = bottle waiting for the sorter at the collection point; false = sorted away
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