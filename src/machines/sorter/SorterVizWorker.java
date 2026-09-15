package machines.sorter;

import java.util.Arrays;
import java.util.List;
import org.compsys704.Worker;

public class SorterVizWorker extends Worker {

    // *BigE names are the same events mirrored to the Big-Picture window's own socket
    // (see sorterPlant.sysj/xml) - handled identically to their *E counterpart since
    // both just drive this same SorterState.
    @Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtSorterE": case "bottleAtSorterBigE":
                SorterState.setBottlePresent(status);
                if (status) {
                    // Reset flags on new bottle arrival
                    SorterState.SORTED = false;
                    SorterState.REJECTED = false;
                    SorterState.BOTTLE_DEFECTIVE = false;
                }
                break;
            case "bottleDefectiveE": case "bottleDefectiveBigE":
                SorterState.BOTTLE_DEFECTIVE = status;
                break;
            case "pusherExtendedE": case "pusherExtendedBigE":
                SorterState.PUSHER_EXTENDED = status;
                break;
            case "pusherRetractedE": case "pusherRetractedBigE":
                SorterState.PUSHER_EXTENDED = !status;
                break;
            case "sortedE":
                if (status) SorterState.SORTED = true;
                break;
            case "rejectedE":
                if (status) SorterState.REJECTED = true;
                break;
            case "sortedCountE": case "rejectedCountE":
            case "sortedCountBigE": case "rejectedCountBigE":
                break; // value arrives via setIntSignal
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    @Override
    public void setIntSignal(int value) {
        switch (signame) {
            case "sortedCountE": case "sortedCountBigE":     SorterState.SORTED_COUNT = value; break;
            case "rejectedCountE": case "rejectedCountBigE": SorterState.REJECTED_COUNT = value; break;
        }
    }

    static final List<String> signames = Arrays.asList(
        "bottleAtSorterE", "bottleDefectiveE", "pusherExtendedE",
        "pusherRetractedE", "sortedE", "rejectedE", "sortedCountE", "rejectedCountE",
        "bottleAtSorterBigE", "bottleDefectiveBigE", "pusherExtendedBigE",
        "pusherRetractedBigE", "sortedCountBigE", "rejectedCountBigE"
    );

    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}