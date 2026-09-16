package digitaltwin;

/**
 * Plant twin for the rotary table. Section 3 of the report singles this
 * station out: the System Twin uses its turn signal to infer the physical
 * position of every bottle on the line, so {@link #recordTurn} forwards
 * straight into {@link SystemTwin#onRotaryTurn()}.
 */
public final class RotaryPlantTwin extends PlantTwin {

    private static final RotaryPlantTwin INSTANCE = new RotaryPlantTwin();

    public static RotaryPlantTwin getInstance() {
        return INSTANCE;
    }

    private int turnCount = 0;

    private RotaryPlantTwin() {
        super("rotary-table", "Rotary Table Station");
    }

    /** Called by the Rotary Table controller every time it indexes the table by one position. */
    public synchronized void recordTurn() {
        turnCount++;
        SystemTwin.getInstance().onRotaryTurn();
    }

}
