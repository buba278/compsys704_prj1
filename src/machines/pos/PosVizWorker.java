package machines.pos;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class PosVizWorker extends Worker {

      @Override
      public void setSignal(boolean status) {
              switch (signame) {
              case "batchDoneE":
                      PosState.BATCH_DONE = status;
                      if (status) OrderQueue.markDone(PosState.LAST_COMPLETION_MS);
                      break;
              case "completionTimeE": break; // value arrives via setIntSignal
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      @Override
      public void setIntSignal(int value) {
              if (signame.equals("completionTimeE")) {
                      PosState.LAST_COMPLETION_MS = value;
              } else {
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      static final List<String> signames = Arrays.asList("batchDoneE", "completionTimeE");

      @Override
      public boolean hasSignal(String sn) {
              return signames.contains(sn);
      }
}
