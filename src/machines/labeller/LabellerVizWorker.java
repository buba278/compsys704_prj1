package machines.labeller;

import digitaltwin.TwinClient;
import java.util.Arrays;
import java.util.List;
import org.compsys704.Worker;

public class LabellerVizWorker extends Worker {
    // *BigE names are the same events mirrored to the Big-Picture window's own socket
    // (see labellerPlant.sysj/xml) - handled identically to their *E counterpart since
    // both just drive this same LabellerState.
	private final TwinClient twin = new TwinClient("labeller-viz", "127.0.0.1", 9090);
	@Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtLabellerE": case "bottleAtLabellerBigE":
                if (status) {
                    // When a new bottle arrives, reset the label states from the previous run
                    if (LabellerState.LABEL_APPLIED) {
                        LabellerState.LABEL_APPLIED = false;
                        LabellerState.LABEL_PRINTED = false;
                    }
                    LabellerState.BOTTLE_PRESENT = true;
                 // Fetch live ID for Position 4 (Labeller) directly from Digital Twin
                    String id = twin.getProductIdAtPosition(4);
                    LabellerState.CURRENT_LABEL_ID = (id != null && !id.isEmpty()) ? id : "UNKNOWN";
                }
                break;

            case "bottleGoneE":
                // NEW: explicit off-signal companion to bottleAtLabellerE. Without
                // this, BOTTLE_PRESENT (and the finished label) could only ever be
                // set true and would latch forever once the first bottle arrived,
                // showing the last bottle's label indefinitely after it left.
                if (status) {
                    LabellerState.BOTTLE_PRESENT = false;
                    LabellerState.CLAMPED = false;
                    LabellerState.LABEL_PRINTED = false;
                    LabellerState.LABEL_APPLIED = false;
                }
                break;

            case "bottleClampedE": case "bottleClampedBigE":
                LabellerState.CLAMPED = status;
                break;

            case "labelPrintedE": case "labelPrintedBigE":
                // Latch print state ON when the pulse arrives
                if (status && !LabellerState.LABEL_PRINTED) {
                    LabellerState.LABEL_PRINTED = true;
                }
                break;

            case "labelAppliedE": case "labelAppliedBigE":
                // Latch applied state ON and clear the printer label graphic
                if (status) {
                    LabellerState.LABEL_APPLIED = true;
                    LabellerState.LABEL_PRINTED = false;
                }
                break;

            case "liquidARatioE": case "targetVolumeMlE":
            case "liquidARatioBigE": case "targetVolumeMlBigE":
                break; // value arrives via setIntSignal

            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    @Override
    public void setIntSignal(int value) {
        switch (signame) {
            case "liquidARatioE": case "liquidARatioBigE":     LabellerState.LIQUID_RATIO = value; break;
            case "targetVolumeMlE": case "targetVolumeMlBigE": LabellerState.BOTTLE_SIZE_ML = value; break;
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    static final List<String> signames = Arrays.asList(
        "bottleAtLabellerE", "bottleGoneE", "bottleClampedE", "labelPrintedE", "labelAppliedE",
        "liquidARatioE", "targetVolumeMlE",
        "bottleAtLabellerBigE", "bottleClampedBigE", "labelPrintedBigE", "labelAppliedBigE",
        "liquidARatioBigE", "targetVolumeMlBigE"
    );

    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}