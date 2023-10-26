package linea;

public class Linea {

//    Se nos pide desarrollar la lógica de juego del '4 en línea'. (https://www.epasatiempos.es/juego-4-en-raya.php)
//    El espacio de juego se define al iniciar, junto con la variante de triunfo.
//    La variante de triunfo puede ser,
//- 'A' solo 4 en línea verticales u horizontales.
//- 'B' solo 4 en línea diagonales
//- 'C' 4 en línea en cualquier orientación.
//    Inician el juego las Rojas y alternan los turnos a partir de ahí
//    El juego puede terminar por triunfo o por empate. Una vez terminado no se puede seguir colocando fichas

    private int base;
    private int height;
    private char[][] board;
    private char varianteTriunfo;
    private int red;
    private int blue;
    private char redChar;
    private char blueChar;

    public Linea(int base, int height, char varianteTriunfo) {
        this.base = base;
        this.height = height;
        this.varianteTriunfo = varianteTriunfo;
        this.redChar = 'R';
        this.blueChar = 'B';
        this.board = new char[height][base];
        this.red = 0;
        this.blue = 0;
    }

    public String show() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < height; i++) {
            result.append("|");
            for (int j = 0; j < base; j++) {
                result.append(board[i][j] == 0 ? " " : board[i][j]);
            }
            result.append("|\n");
        }
        return result.toString();
    }

    public void playRedkAt(int position) {
        for(int i = height - 1; i >= 0; i--) {
            if (board[i][position] == 0) {
                board[i][position] = redChar;
                red++;
                return;
            }
        }
    }

    public void playBlueAt(int position) {
        for(int i = height - 1; i >= 0; i--) {
            if (board[i][position] == 0) {
                board[i][position] = blueChar;
                blue++;
                return;
            }
        }
    }

    public boolean finished() {
            if (varianteTriunfo == 'C' || varianteTriunfo == 'A') {
                // Verificar si hay 4 colores seguidos en horizontal
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < base - 3; j++) {
                        if (board[i][j] != 0 && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                            return true;
                        }
                    }
                }
                // Verificar si hay 4 colores seguidos en vertical
                for (int i = 0; i < height - 3; i++) {
                    for (int j = 0; j < base; j++) {
                        if (board[i][j] != 0 && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                            return true;
                        }
                    }
                }
            }
            if (varianteTriunfo == 'C' || varianteTriunfo == 'B'){
            // Verificar si hay 4 colores seguidos en diagonal
            for (int i = 0; i < height - 3; i++) {
                for (int j = 0; j < base - 3; j++) {
                    if (board[i][j] != 0 && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]){
                        return true;
                    }
                    if ( j > 3 && board[i][j] != 0 && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2] && board[i][j] == board[i + 3][j - 3]){
                        return true;
                    }
                }
            }
        }
        return red + blue == base * height;
    }

}
