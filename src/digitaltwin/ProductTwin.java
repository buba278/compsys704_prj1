package digitaltwin;

import java.util.LinkedHashMap;
import java.util.Map;
import com.google.gson.Gson;

public class ProductTwin {
    public enum Status { ON_LINE, SORTED, REJECTED }
    
    private final String productId;
    private final String batchId;
    private final double volumeMl;
    private final double liquidRatio;
    private final long createdEpochMs;
 
    private int currentPosition = 1;
    private String currentWorkstation;
    
    private Status status = Status.ON_LINE;
    private Long archivedEpochMs = null;
    private boolean hasFault = false;
 
    private final Map<String, String> stationOutcomes = new LinkedHashMap<>();
    
    private static final Gson GSON = new Gson();
 
    ProductTwin(String productId, String batchId, double volumeMl, double liquidRatio) {
        this.productId = productId;
        this.batchId = batchId;
        this.volumeMl = volumeMl;
        this.liquidRatio = liquidRatio;
        this.createdEpochMs = System.currentTimeMillis();
        
        // Set default workstation for position 1 upon creation
        this.currentWorkstation = "filler"; 
    }
 
    synchronized void advancePosition() {
        if (status == Status.ON_LINE && currentPosition < 4) {
            currentPosition++;
            updateWorkstationName();
        }
    }
 
    synchronized void recordStationOutcome(String stationName, String outcome) {
        stationOutcomes.put(stationName, outcome);
        // Convention observed in capperController.sysj/lidPlacerController.sysj:
        // fault outcomes are phrased with "(fault)" or "fault" in the label
        // (e.g. "Capping aborted (fault)", "Lid placement aborted (fault)").
        // If a future station phrases it differently, this silently misses it.
        if (outcome != null && outcome.toLowerCase().contains("fault")) {
            hasFault = true;
        }
        
        if (stationName != null && stationName.toLowerCase().contains("label")) {
            if (currentPosition == 4) {
                currentPosition = 5;
                updateWorkstationName();
            }
        }
    }
    
    private void updateWorkstationName() {
        switch (currentPosition) {
            case 1:  currentWorkstation = "filler"; break;
            case 2:  currentWorkstation = "lid placer"; break;
            case 3:  currentWorkstation = "cap screwer"; break;
            case 4:  currentWorkstation = "labeller"; break;
            case 5:  currentWorkstation = "sorter"; break;
            default: currentWorkstation = "unknown"; break;
        }
    }
 
    synchronized void archive(boolean rejected) {
        this.status = rejected ? Status.REJECTED : Status.SORTED;
        this.archivedEpochMs = System.currentTimeMillis();
    }
 
    public String getProductId() {
        return productId;
    }
 
    public String getBatchId() {
        return batchId;
    }
 
    public synchronized int getCurrentPosition() {
        return currentPosition;
    }

    public synchronized String getCurrentWorkstation() {
        return currentWorkstation;
    }
 
    public synchronized Status getStatus() {
        return status;
    }

    /** True the moment any station has recorded an outcome mentioning "fault" for this bottle. */
    public synchronized boolean hasFault() {
        return hasFault;
    }
 
    public synchronized String toJson() {
        return GSON.toJson(this);
    }
}