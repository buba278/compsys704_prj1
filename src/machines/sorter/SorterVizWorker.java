package machines.sorter;

import java.util.Arrays;
import java.util.List;
import org.compsys704.Worker;

public class SorterVizWorker extends Worker {

    @Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtSorterE":
                SorterState.BOTTLE_PRESENT = status;
                if (status) {
                    // Reset flags on new bottle arrival
                    SorterState.SORTED = false;
                    SorterState.REJECTED = false;
                    SorterState.BOTTLE_DEFECTIVE = false;
                }
                break;
            case "bottleDefectiveE":
                SorterState.BOTTLE_DEFECTIVE = status;
                break;
            case "pusherExtendedE":
                SorterState.PUSHER_EXTENDED = status;
                break;
            case "pusherRetractedE":
                SorterState.PUSHER_EXTENDED = !status;
                break;
            case "sortedE":
                if (status) SorterState.SORTED = true;
                break;
            case "rejectedE":
                if (status) SorterState.REJECTED = true;
                break;
            case "sortedCountE": case "rejectedCountE":
                break; // value arrives via setIntSignal
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    @Override
    public void setIntSignal(int value) {
        switch (signame) {
            case "sortedCountE":   SorterState.SORTED_COUNT = value; break;
            case "rejectedCountE": SorterState.REJECTED_COUNT = value; break;
        }
    }

    static final List<String> signames = Arrays.asList(
        "bottleAtSorterE", "bottleDefectiveE", "pusherExtendedE",
        "pusherRetractedE", "sortedE", "rejectedE", "sortedCountE", "rejectedCountE"
    );

    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}