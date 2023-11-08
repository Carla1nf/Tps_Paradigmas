package linea;

public abstract class Estrategia {
    public abstract boolean canHandle(char varianteTriunfo);

    public abstract boolean checkWin(Linea game, char color);
}
