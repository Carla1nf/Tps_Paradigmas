package linea;

public class EstrategiaB extends Estrategia {
    public boolean checkWin(Linea game, char color) {
        return game.diagonalWin(color);
    }

    public boolean canHandle(char varianteTriunfo) {
        return varianteTriunfo == 'B';
    }
}
