package machines.labeller;

import java.util.Arrays;
import java.util.List;
import org.compsys704.Worker;

public class LabellerVizWorker extends Worker {
    @Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtLabellerE":
                if (status) {
                    // When a new bottle arrives, reset the label states from the previous run
                    if (LabellerState.LABEL_APPLIED) {
                        LabellerState.LABEL_APPLIED = false;
                        LabellerState.LABEL_PRINTED = false;
                    }
                    LabellerState.BOTTLE_PRESENT = true;
                }
                break;

            case "bottleClampedE":
                LabellerState.CLAMPED = status;
                break;
 
            case "labelPrintedE":
                // Latch print state ON when the pulse arrives
                if (status && !LabellerState.LABEL_PRINTED) {
                    LabellerState.LABEL_COUNTER++;
                    LabellerState.CURRENT_LABEL_ID = String.format("#%04d", LabellerState.LABEL_COUNTER);
                    LabellerState.LABEL_PRINTED = true;
                }
                break;

            case "labelAppliedE":
                // Latch applied state ON and clear the printer label graphic
                if (status) {
                    LabellerState.LABEL_APPLIED = true;
                    LabellerState.LABEL_PRINTED = false; 
                }
                break;

            case "liquidARatioE": case "targetVolumeMlE":
                break; // value arrives via setIntSignal

            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    @Override
    public void setIntSignal(int value) {
        switch (signame) {
            case "liquidARatioE":   LabellerState.LIQUID_RATIO = value; break;
            case "targetVolumeMlE": LabellerState.BOTTLE_SIZE_ML = value; break;
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    static final List<String> signames = Arrays.asList(
        "bottleAtLabellerE", "bottleClampedE", "labelPrintedE", "labelAppliedE",
        "liquidARatioE", "targetVolumeMlE"
    );

    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}