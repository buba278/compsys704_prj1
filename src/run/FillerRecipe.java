package run;

/** Holds the current fill recipe (ratio and volume) set by the coordinator.
 *  Using plain Java volatile fields avoids SystemJ local-signal retention issues:
 *  local signals go absent if not re-emitted each tick, making getpreval() return null.
 *  Static accessors are used from .sysj files to avoid the SystemJ compiler misreading
 *  dotted field assignments (e.g. Foo.bar = x) as local variable declarations.
 */
public class FillerRecipe {
    private static volatile int ratio  = 50;
    private static volatile int volume = 330;

    public static void setRatio(int v)  { ratio  = v; }
    public static void setVolume(int v) { volume = v; }
    public static int  getRatio()       { return ratio;  }
    public static int  getVolume()      { return volume; }
}
