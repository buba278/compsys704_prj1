package digitaltwin;

public final class LabellerPlantTwin extends PlantTwin {
	 
    private static final LabellerPlantTwin INSTANCE = new LabellerPlantTwin();
 
    public static LabellerPlantTwin getInstance() {
        return INSTANCE;
    }
 
    private int labelsApplied = 0;
 
    private LabellerPlantTwin() {
        super("labeller", "Labelling Station");
    }
 
    /** Called by the LabelController once a label has been applied to a bottle. */
    public synchronized void recordLabelApplied(String productId) {
        labelsApplied++;
        recordOutcomeOnProduct(productId, "Label applied");
    }
 
}
