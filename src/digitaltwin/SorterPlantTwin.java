package digitaltwin;

public class SorterPlantTwin extends PlantTwin{
    private static final SorterPlantTwin INSTANCE = new SorterPlantTwin();
    
    public static SorterPlantTwin getInstance() {
        return INSTANCE;
    }
 
    private SorterPlantTwin() {
        super("sorter", "Sorting Station");
    }

}
