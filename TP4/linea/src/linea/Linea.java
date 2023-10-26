package linea;

public class Linea {
    private int base;
    private int altura;
    private char color;
    private char[][] tablero;

    public Linea(int base, int altura, char color) {
        this.base = base;
        this.altura = altura;
        this.color = color;
        this.tablero = new char[altura][base];
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < base; j++) {
                this.tablero[i][j] = ' ';
            }
        }
    }

    public String show() {
        String result = "";
        for (int i = 0; i < this.altura; i++) {
            for (int j = 0; j < this.base; j++) {
                result += this.tablero[i][j];
            }
            result += "\n";
        }
        return result;
    }

    public void playRedkAt(int x) {
        for (int i = 0; i < this.altura; i++) {
            if (this.tablero[i][x] == ' ') {
                this.tablero[i][x] = 'R';
                break;
            }
        }
    }

    public void playBlueAt(int x) {
        for (int i = 0; i < this.altura; i++) {
            if (this.tablero[i][x] == ' ') {
                this.tablero[i][x] = 'B';
                break;
            }
        }
    }

    public boolean finished() {
        boolean result = false;
        for (int i = 0; i < this.altura; i++) {
            for (int j = 0; j < this.base; j++) {
                if (this.tablero[i][j] == ' ') {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }
}
