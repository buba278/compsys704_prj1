package machines.coordinator;

// shared state written by CoordinatorVizWorker, read by BigPictureCanvas.
// The Coordinator clock domain has no GUI of its own, so this mirrors the
// batch-level signals it emits (liquidARatioE/targetVolumeMlE/bottlesNeededE/
// bottlesFilledE/bottleFaultedE/batchDoneE/batchElapsedE) for the Big-Picture
// view, the same way FillerState mirrors the Filler's viz signals.
public class CoordinatorState {
      public static volatile int LIQUID_A_RATIO = 50;
      public static volatile int TARGET_VOLUME_ML = 330;
      public static volatile int BOTTLES_NEEDED = 0;
      public static volatile int BOTTLES_FILLED = 0;
      public static volatile boolean BATCH_DONE = false;
      public static volatile int LAST_BATCH_ELAPSED_MS = -1;

      // bottleFaultedE is a momentary pulse (see coordinator.sysj) - latched here with
      // a timestamp so the canvas can flash the fault indicator for a short window
      // instead of missing it between repaints.
      public static volatile long LAST_FAULT_MS = 0;
}
