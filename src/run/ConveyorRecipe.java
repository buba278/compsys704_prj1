package run;

/** Holds the current batch's Liquid A ratio, latched from the Coordinator's
 *  liquidARatioC (see coordinator.sysj/conveyorPlant.sysj), so the exiting bottle's
 *  colour split can be tagged with the ratio it was actually filled with. Plain Java
 *  field rather than a SystemJ signal, see CLAUDE.md (local-signal retention) - same
 *  pattern as FillerRecipe.
 */
public class ConveyorRecipe {
    private static volatile int ratioA = 50;

    public static void setRatio(int v) { ratioA = v; }
    public static int  getRatio()      { return ratioA; }
}
