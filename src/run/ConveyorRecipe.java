package run;

public class ConveyorRecipe {
    private static volatile int ratioA = 50;

    public static void setRatio(int v) { ratioA = v; }
    public static int  getRatio()      { return ratioA; }
}
