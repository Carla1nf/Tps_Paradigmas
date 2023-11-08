package linea;

public class EstrategiaA extends Estrategia {
    public boolean checkWin(Linea game, char color) {
        return game.verticalWin(color) || game.horizontalWin(color);
    }

    public boolean canHandle(char varianteTriunfo) {
        return varianteTriunfo == 'A';
    }
}
