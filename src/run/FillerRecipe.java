package run;

/** Holds the current fill recipe (ratio and volume) set by the coordinator. Plain Java
 *  fields rather than SystemJ signals, see CLAUDE.md (local-signal retention).
 */
public class FillerRecipe {
    private static volatile int ratio  = 50;
    private static volatile int volume = 330;

    public static void setRatio(int v)  { ratio  = v; }
    public static void setVolume(int v) { volume = v; }
    public static int  getRatio()       { return ratio;  }
    public static int  getVolume()      { return volume; }
}
