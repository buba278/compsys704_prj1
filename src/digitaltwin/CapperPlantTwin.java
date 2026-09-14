package digitaltwin;

public final class CapperPlantTwin extends PlantTwin {
	 
    private static final CapperPlantTwin INSTANCE = new CapperPlantTwin();
 
    public static CapperPlantTwin getInstance() {
        return INSTANCE;
    }
 
    private int twistCount = 0;
 
    private CapperPlantTwin() {
        super("cap-screwer", "Cap Screwing Station");
    }
 
    /** Called by the CapperController once a full twist has been achieved for a bottle. */
    public synchronized void recordTwist(String productId) {
        twistCount++;
        recordOutcomeOnProduct(productId, "Cap screwed on");
    }
 
}

