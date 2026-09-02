package machines.filler;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class FillerVizWorker extends Worker {

      @Override
      public void setSignal(boolean status) {
              switch (signame) {
              case "valve1OpenE":     FillerState.setValve1Open(status); break;
              case "valve2OpenE":     FillerState.VALVE2_OPEN = status; break;
              case "fillDoneE":       if (status) FillerState.FILL_DONE = true; break;
              case "fillLevelE":      break;
              case "totalVolumeMlE":  break;
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      @Override
      public void setIntSignal(int value) {
              switch (signame) {
              case "fillLevelE":     FillerState.setFillLevel(value); break;
              case "totalVolumeMlE": FillerState.TARGET_VOLUME_ML = value; break;
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      static final List<String> signames = Arrays.asList("valve1OpenE", "valve2OpenE", "fillDoneE", "fillLevelE", "totalVolumeMlE");

      @Override
      public boolean hasSignal(String sn) {
              return signames.contains(sn);
      }
}