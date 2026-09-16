package digitaltwin;

/** Plant twin for the loading conveyor station. */
public final class LoadingConveyorPlantTwin extends PlantTwin {

    private static final LoadingConveyorPlantTwin INSTANCE = new LoadingConveyorPlantTwin();

    public static LoadingConveyorPlantTwin getInstance() {
        return INSTANCE;
    }

    private int bottlesLoaded = 0;

    private LoadingConveyorPlantTwin() {
        super("loading-conveyor", "Loading Conveyor Station");
    }

    /** Called by the Loading Conveyor controller each time an empty bottle is fed onto the line. */
    public synchronized void recordBottleLoaded() {
        bottlesLoaded++;
    }

}
