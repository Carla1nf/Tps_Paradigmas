package linea;

public class EstrategiaC extends Estrategia{
    public boolean checkWin(Linea game, char color) {
        return game.diagonalWin(color) || game.horizontalWin(color) || game.verticalWin(color);
    }

    public boolean canHandle(char varianteTriunfo) {
        return varianteTriunfo == 'C';
    }
}
