package run;

/** Holds the current fill recipe (ratio and volume) set by the coordinator.
 *  Using plain Java volatile fields avoids SystemJ local-signal retention issues:
 *  a signal's carried value only exists on ticks it's actively (re-)emitted, so
 *  reading it many ticks after the sender stopped emitting (e.g. mid-dose, long
 *  after the recipe signals' one-shot 50ms hold from the Coordinator) makes
 *  getpreval() return null. Static accessors are used purely as the calling
 *  convention for this store from .sysj files, not to work around a parser issue.
 */
public class FillerRecipe {
    private static volatile int ratio  = 50;
    private static volatile int volume = 330;

    public static void setRatio(int v)  { ratio  = v; }
    public static void setVolume(int v) { volume = v; }
    public static int  getRatio()       { return ratio;  }
    public static int  getVolume()      { return volume; }
}
