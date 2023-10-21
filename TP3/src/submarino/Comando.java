package submarino;

public abstract class Comando {
    public abstract boolean canHandle(char command);
    public abstract void execute(Submarino submarino);
}
