package run;

/** Holds the current fill recipe (ratio and volume) set by the coordinator - a plain Java field, not a SystemJ signal, since it must persist across ticks/threads. */
public class FillerRecipe {
    private static volatile int ratio  = 50;
    private static volatile int volume = 330;

    public static void setRatio(int v)  { ratio  = v; }
    public static void setVolume(int v) { volume = v; }
    public static int  getRatio()       { return ratio;  }
    public static int  getVolume()      { return volume; }
}
