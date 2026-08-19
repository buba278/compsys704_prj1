package machines.filler;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class FillerVizWorker extends Worker {

      @Override
      public void setSignal(boolean status) {
              switch (signame) {
              case "valve1OpenE": FillerState.VALVE1_OPEN = status; break;
              case "valve2OpenE": FillerState.VALVE2_OPEN = status; break;
              case "fillDoneE":   FillerState.FILL_DONE = status; break;
              case "fillLevelE":  break;
              default:
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      @Override
      public void setIntSignal(int value) {
              if (signame.equals("fillLevelE")) {
                      FillerState.FILL_LEVEL = value;
              } else {
                      System.err.println("Wrong sig name : " + signame);
                      System.exit(1);
              }
      }

      static final List<String> signames = Arrays.asList("valve1OpenE", "valve2OpenE", "fillDoneE", "fillLevelE");

      @Override
      public boolean hasSignal(String sn) {
              return signames.contains(sn);
      }
}