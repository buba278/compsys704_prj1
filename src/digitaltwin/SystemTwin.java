package digitaltwin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.gson.Gson;

public class SystemTwin {
	
    private static final SystemTwin INSTANCE = new SystemTwin();
    private static final Gson GSON = new Gson();
	 
    public static SystemTwin getInstance() {
        return INSTANCE;
    }
 
    private final Map<String, PlantTwin> plantTwins = new ConcurrentHashMap<>();
    private final Map<String, ProductTwin> activeProducts = new ConcurrentHashMap<>();
    private final List<ProductTwin> archivedProducts = new CopyOnWriteArrayList<>();
 
    private String batchId = null;
    private int batchTargetCount = 0;
    private int batchCompletedCount = 0;
    private int rejectedCount = 0;
    private int rotaryTurnCount = 0;
    private int productSequence = 0;
    private long lastBatchElapsedMs = 0;
 
    private SystemTwin() {
    }
 
    void register(PlantTwin twin) {
        plantTwins.put(twin.getStationId(), twin);
    }
 
    void onPlantTwinUpdated(PlantTwin twin) {
        if (twin.getState() == PlantTwin.State.ERROR) {
            System.out.println("SystemTwin: FAULT at [" + twin.getStationName() + "] - " + twin.getLastError());
        }
    }
 
    public synchronized void startBatch(String batchId, int targetCount) {
        this.batchId = batchId;
        this.batchTargetCount = targetCount;
        this.batchCompletedCount = 0;
        this.rejectedCount = 0;
        System.out.println("SystemTwin: Batch " + batchId + " started, target " + targetCount + " bottles.");
    }
    
    public synchronized void reportBatchProgress(int completedCount) {
        this.batchCompletedCount = completedCount;
    }
    
    public synchronized void reportBatchDone(int elapsedMs) {
        this.lastBatchElapsedMs = elapsedMs;
    }
 
    public synchronized void onRotaryTurn() {
        rotaryTurnCount++;
        for (ProductTwin product : activeProducts.values()) {
            product.advancePosition();
        }
    }
 
    synchronized String createProductTwin(double volumeMl, double liquidRatio) {
        productSequence++;
        
        // Inherit batchId (or fallback to 'BATCH' if null) and append increasing sequence number
        String activeBatch = (this.batchId != null && !this.batchId.isEmpty()) ? this.batchId : "BATCH";
        String productId = String.format("%s-%03d", activeBatch, productSequence); // Produces e.g. "BATCH-001-001"

        // ProductTwin automatically receives the active batchId
        ProductTwin twin = new ProductTwin(productId, this.batchId, volumeMl, liquidRatio);
        activeProducts.put(productId, twin);
        System.out.println("SystemTwin: ProductTwin " + productId + " created for batch " + batchId + ".");
        return productId;
    }
    public ProductTwin getProductTwin(String productId) {
        return activeProducts.get(productId);
    }

    /** Looked up by TwinServer's control-socket dispatcher when a controller sends PLANT_UPDATE / PLANT_EVENT. */
    public PlantTwin getPlantTwin(String stationId) {
        return plantTwins.get(stationId);
    }
 
    synchronized void archiveProductTwin(String productId, boolean rejected) {
        ProductTwin twin = activeProducts.remove(productId);
        if (twin == null) {
            return;
        }
        twin.archive(rejected);
        archivedProducts.add(twin);
        batchCompletedCount++;
        if (rejected) {
            rejectedCount++;
        }
        System.out.println("SystemTwin: ProductTwin " + productId + " archived ("
                + (rejected ? "REJECTED" : "sorted OK") + "). Export: " + twin.toJson());
    }
 
    public synchronized String getBatchId() {
        return batchId;
    }
 
    public synchronized int getRejectedCount() {
        return rejectedCount;
    }
 
    public synchronized String toJson() {
        // Constructing a map forces Gson to format the Maps' values as JSON Arrays 
        // to maintain compatibility with the original system API.
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("batchId", batchId);
        data.put("batchTargetCount", batchTargetCount);
        data.put("batchCompletedCount", batchCompletedCount);
        data.put("rejectedCount", rejectedCount);
        data.put("rotaryTurnCount", rotaryTurnCount);
        data.put("plantTwins", plantTwins.values());
        data.put("activeProducts", activeProducts.values());
        
        return GSON.toJson(data);
    }
}