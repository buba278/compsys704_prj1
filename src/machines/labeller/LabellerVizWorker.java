package machines.labeller;
import java.util.Arrays;
import java.util.List;
import org.compsys704.Worker;
 
public class LabellerVizWorker extends Worker {
	@Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtLabellerE":
                // bottleAtLabeller in the plant model really means "detected,
                // not yet clamped" - it drops the instant clamping begins, even
                // though the bottle is still physically at the station all the
                // way through clamping and label application. Mirroring status
                // directly made the bottle sprite (and the sticker drawn on it)
                // vanish right as clamping started, which is why print vs.
                // applied only ever showed up on alternating runs. Latch it on
                // when the bottle first arrives, and only clear it once the
                // whole cycle actually finishes (see labelAppliedE below).
                if (status) {
                    LabellerState.BOTTLE_PRESENT = true;
                }
                break;
 
            case "bottleClampedE":
                // Also `sustain`-ed for as long as the clamp is engaged - level signal.
                LabellerState.CLAMPED = status;
                break;
 
            case "labelPrintedE":
                // NOTE: labelPrinted is only a ONE-TICK PULSE in the plant model
                // (`await(printLabel); emit labelPrinted; pause;`). If we mirror
                // `status` directly here it gets set true and then immediately
                // false again on the very next tick, before Swing ever repaints -
                // which is why the printed label appeared to never show up /
                // the visualiser looked "stuck" right after the print command.
                // Latch it on and only clear it once the label has been applied.
                if (status) {
                    LabellerState.LABEL_COUNTER++;
                    LabellerState.CURRENT_LABEL_ID = String.format("#%04d", LabellerState.LABEL_COUNTER);
                    LabellerState.LABEL_PRINTED = true;
                }
                break;
 
            case "labelAppliedE":
                // labelApplied is also a one-tick pulse. Latch it on for the
                // "applied" visual, and use its falling edge as the cue that
                // the bottle has actually left the station / cycle is resetting -
                // this is also what clears BOTTLE_PRESENT and LABEL_PRINTED.
                if (status) {
                    LabellerState.LABEL_APPLIED = true;
                } else {
                    LabellerState.LABEL_APPLIED = false;
                    LabellerState.LABEL_PRINTED = false;
                    LabellerState.BOTTLE_PRESENT = false;
                }
                break;
 
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }
 
    @Override
    public void setIntSignal(int value) {}
 
    static final List<String> signames = Arrays.asList(
        "bottleAtLabellerE", "bottleClampedE", "labelPrintedE", "labelAppliedE"
    );
 
    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}

