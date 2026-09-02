package machines.pos;

// shared state written by PosVizWorker + the form, read by PosPanel's status label
public class PosState {
      public static volatile boolean ORDER_ACTIVE = false;
      public static volatile boolean BATCH_DONE = false;
      public static volatile int LAST_COMPLETION_MS = -1;
}
