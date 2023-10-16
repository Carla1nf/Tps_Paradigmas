package submarino;

public abstract class Profundidad {
    protected int z;
    public abstract Profundidad descender();
    public abstract Profundidad ascender();
    public abstract void liberarCapsula();
    public int getZ() {
        return z;
    }
}
