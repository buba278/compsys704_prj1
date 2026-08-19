package stations.filler;

// shared states written by VizWorker, read by Panel
public class FillerState {
      public static volatile boolean VALVE1_OPEN = false;
      public static volatile boolean VALVE2_OPEN = false;
      public static volatile boolean FILL_DONE = false;
      public static volatile int FILL_LEVEL = 0;
}