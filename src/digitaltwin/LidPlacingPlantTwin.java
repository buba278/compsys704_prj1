package digitaltwin;

public final class LidPlacingPlantTwin extends PlantTwin {
	 
    private static final LidPlacingPlantTwin INSTANCE = new LidPlacingPlantTwin();
 
    public static LidPlacingPlantTwin getInstance() {
        return INSTANCE;
    }
 
    private int lidsPlaced = 0;
 
    private LidPlacingPlantTwin() {
        super("lid-placer", "Lid Placing Station");
    }
 
    /** Called by the Lid Placer controller each time a lid is set on a bottle. */
    public synchronized void recordLidPlaced(String productId) {
        lidsPlaced++;
        recordOutcomeOnProduct(productId, "Lid placed");
    }
 
}
