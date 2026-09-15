package machines.coordinator;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class CoordinatorVizWorker extends Worker {

      @Override
      public void setSignal(boolean status) {
              switch (signame) {
              case "bottleFaultedE": if (status) CoordinatorState.LAST_FAULT_MS = System.currentTimeMillis(); break;
              case "batchDoneE":     CoordinatorState.BATCH_DONE = status; break;
              case "liquidARatioE":
              case "targetVolumeMlE":
              case "bottlesNeededE":
              case "bottlesFilledE":
              case "batchElapsedE":  break; // values arrive via setIntSignal
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      @Override
      public void setIntSignal(int value) {
              switch (signame) {
              case "liquidARatioE":   CoordinatorState.LIQUID_A_RATIO = value; break;
              case "targetVolumeMlE": CoordinatorState.TARGET_VOLUME_ML = value; break;
              case "bottlesNeededE":  CoordinatorState.BOTTLES_NEEDED = value; CoordinatorState.BATCH_DONE = false; break;
              case "bottlesFilledE":  CoordinatorState.BOTTLES_FILLED = value; break;
              case "batchElapsedE":   CoordinatorState.LAST_BATCH_ELAPSED_MS = value; break;
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      static final List<String> signames = Arrays.asList(
              "liquidARatioE", "targetVolumeMlE", "bottlesNeededE", "bottlesFilledE",
              "bottleFaultedE", "batchDoneE", "batchElapsedE");

      @Override
      public boolean hasSignal(String sn) {
              return signames.contains(sn);
      }
}
