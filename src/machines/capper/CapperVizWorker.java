package machines.capper;

import java.util.Arrays;
import java.util.List;

import org.compsys704.Worker;

public class CapperVizWorker extends Worker {

    @Override
    public void setSignal(boolean status) {
        switch (signame) {
            case "bottleAtPos4E":
                CapperState.BOTTLE_PRESENT = status;
                CapperState.CLAMPED = status; // Clamp engages when bottle is present
                break;
            case "bottleGoneE":
                // NEW: explicit off-signal companion to bottleAtPos4E. Without this,
                // BOTTLE_PRESENT/CLAMPED could only ever be set true and would latch
                // forever once the first bottle arrived.
                if (status) {
                    CapperState.BOTTLE_PRESENT = false;
                    CapperState.CLAMPED = false;
                }
                break;
            case "gripperMaxLowerE":
                if (status) {
                    CapperState.GRIP_HEIGHT = 40; // Lowers to cap level
                    CapperState.GRIPPED = true;
                    CapperState.BOTTLE_PRESENT = true;
                    CapperState.CLAMPED = true;
                }
                break;
            case "gripperMaxLiftE":
                if (status) {
                    CapperState.GRIP_HEIGHT = 0; // Returns to resting height
                    CapperState.GRIPPED = false;
                }
                break;
            case "gripperInitPosE":
                if (status) { 
                	CapperState.TWIST_ANGLE = 0; // Untwisted
                	CapperState.BOTTLE_PRESENT = false;
                	CapperState.CLAMPED = false;
                }
                break;
            case "gripperFullTwistE":
                if (status) CapperState.TWIST_ANGLE = 270; // Fully twisted
                break;
            case "capperFaultE":
                if (status) CapperState.FAULTED = true;
                break;
            default:
                System.err.println("Wrong sig name : " + signame);
                System.exit(1);
        }
    }

    @Override
    public void setIntSignal(int value) {
        // No integer signals expected based on the provided SystemJ snippet
    }

    static final List<String> signames = Arrays.asList(
        "bottleAtPos4E", "bottleGoneE", "gripperMaxLowerE", "gripperMaxLiftE",
        "gripperInitPosE", "gripperFullTwistE", "capperFaultE"
    );

    @Override
    public boolean hasSignal(String sn) {
        return signames.contains(sn);
    }
}