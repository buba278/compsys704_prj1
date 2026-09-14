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
    private Status status = Status.ON_LINE;
    private Long archivedEpochMs = null;
 
    private final Map<String, String> stationOutcomes = new LinkedHashMap<>();
    
    private static final Gson GSON = new Gson();
 
    ProductTwin(String productId, String batchId, double volumeMl, double liquidRatio) {
        this.productId = productId;
        this.batchId = batchId;
        this.volumeMl = volumeMl;
        this.liquidRatio = liquidRatio;
        this.createdEpochMs = System.currentTimeMillis();
    }
 
    synchronized void advancePosition() {
        if (status == Status.ON_LINE) {
            currentPosition++;
        }
    }
 
    synchronized void recordStationOutcome(String stationName, String outcome) {
        stationOutcomes.put(stationName, outcome);
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
 
    public synchronized Status getStatus() {
        return status;
    }
 
    public synchronized String toJson() {
        return GSON.toJson(this);
    }
}