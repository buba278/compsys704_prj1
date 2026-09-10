package machines.filler;

// shared states written by VizWorker, read by Panel
public class FillerState {
      public static volatile boolean VALVE1_OPEN = false;
      public static volatile boolean VALVE2_OPEN = false;
      public static volatile boolean FILL_DONE = false;
      public static volatile boolean FAULT = false;
      public static volatile int FILL_LEVEL = 0;
      public static volatile int TARGET_VOLUME_ML = 330;

      // fill level at which liquid A finished dosing and liquid B started,
      // so the canvas can render the two liquids in different colours
      public static volatile int PHASE1_END_LEVEL = 0;

      public static void setValve1Open(boolean open) {
            if (VALVE1_OPEN && !open) {
                  PHASE1_END_LEVEL = FILL_LEVEL;
            }
            VALVE1_OPEN = open;
      }

      public static void setFillLevel(int level) {
            if (level == 0) {
                  PHASE1_END_LEVEL = 0;
                  FAULT = false;
            }
            FILL_LEVEL = level;
      }
}
