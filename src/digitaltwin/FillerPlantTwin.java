package digitaltwin;

public final class FillerPlantTwin extends PlantTwin {
	 
    private static final FillerPlantTwin INSTANCE = new FillerPlantTwin();
 
    public static FillerPlantTwin getInstance() {
        return INSTANCE;
    }
 
    private double lastVolumeMl = 0;
    private double lastLiquidRatio = 0;
    private int bottlesFilled = 0;
 
    private FillerPlantTwin() {
        super("filler", "Filling Station");
    }
 
    /**
     * Called by the Filler controller when a new bottle arrives at position 1
     * and its fill parameters (from the POS) are known.
     *
     * @return the newly created ProductTwin's productId
     */
    public synchronized String reportNewBottle(double volumeMl, double liquidRatio) {
        this.lastVolumeMl = volumeMl;
        this.lastLiquidRatio = liquidRatio;
        this.bottlesFilled++;
        String productId = SystemTwin.getInstance().createProductTwin(volumeMl, liquidRatio);
        recordOutcomeOnProduct(productId, "Filled " + volumeMl + "ml @ ratio " + liquidRatio);
        return productId;
    }
 
}

