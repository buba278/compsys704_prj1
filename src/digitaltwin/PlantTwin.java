package digitaltwin;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Base class for every Plant Digital Twin.
 */
public abstract class PlantTwin {
 
    public enum State { IDLE, IN_PROGRESS, ERROR }
 
    private final String stationId;
    private final String stationName;
    private State state = State.IDLE;
    private long lastUpdatedEpochMs = System.currentTimeMillis();
    private String lastError = null;

    /** Generic named event counters (e.g. "Cap screwed on" -> 42). Gson picks
     *  this up automatically, same as any other field, so subclasses that
     *  only need a simple count don't need their own counter field. */
    private final Map<String, Integer> eventCounts = new LinkedHashMap<>();
    
    private static final Gson GSON = new Gson();
 
    protected PlantTwin(String stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
        SystemTwin.getInstance().register(this);
    }
 
    public final void update(State newState) {
        update(newState, null);
    }
 
    public final synchronized void update(State newState, String errorDetail) {
        this.state = newState;
        this.lastError = (newState == State.ERROR) ? errorDetail : null;
        this.lastUpdatedEpochMs = System.currentTimeMillis();
        SystemTwin.getInstance().onPlantTwinUpdated(this);
    }

    /** Called by TwinServer when a controller reports a station-specific event with no bottle context. */
    public final void recordEvent(String eventLabel) {
        recordEvent(eventLabel, null);
    }

    /** Called by TwinServer when a controller reports a station-specific event tied to a bottle. */
    public final synchronized void recordEvent(String eventLabel, String productId) {
        eventCounts.merge(eventLabel, 1, Integer::sum);
        this.lastUpdatedEpochMs = System.currentTimeMillis();
        recordOutcomeOnProduct(productId, eventLabel);
        SystemTwin.getInstance().onPlantTwinUpdated(this);
    }
 
    public String getStationId() {
        return stationId;
    }
 
    public String getStationName() {
        return stationName;
    }
 
    public synchronized State getState() {
        return state;
    }
 
    public synchronized long getLastUpdatedEpochMs() {
        return lastUpdatedEpochMs;
    }
 
    public synchronized String getLastError() {
        return lastError;
    }
 
    protected final void recordOutcomeOnProduct(String productId, String outcome) {
        if (productId == null) {
            return;
        }
        ProductTwin product = SystemTwin.getInstance().getProductTwin(productId);
        if (product != null) {
            product.recordStationOutcome(stationName, outcome);
        }
    }
 
    public final synchronized String toJson() {
        // Gson automatically includes child-class fields at runtime.
        return GSON.toJson(this);
    }
}