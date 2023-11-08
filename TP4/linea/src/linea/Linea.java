package linea;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Linea {

//    Se nos pide desarrollar la lógica de juego del '4 en línea'. (https://www.epasatiempos.es/juego-4-en-raya.php)
//    El espacio de juego se define al iniciar, junto con la variante de triunfo.
//    La variante de triunfo puede ser,
//  - 'A' solo 4 en línea verticales u horizontales.
//  - 'B' solo 4 en línea diagonales
//  - 'C' 4 en línea en cualquier orientación.
//    Inician el juego las Rojas y alternan los turnos a partir de ahí
//    El juego puede terminar por triunfo o por empate. Una vez terminado no se puede seguir colocando fichas

    private int base;
    private int height;
    private ArrayList<ArrayList<Character>> board;
    private char redChar;
    private char blueChar;
    private boolean finished;
    private Turno turno;
    private Estrategia estrategia;
    private ArrayList<Estrategia> estrategias = new ArrayList<>(Arrays.asList(new EstrategiaA(), new EstrategiaB(), new EstrategiaC()));
    public Linea(int base, int height, char varianteTriunfo) {
        this.base = base;
        this.height = height;
        this.redChar = 'X';
        this.blueChar = 'O';
        this.board = new ArrayList<>();
        this.finished = false;
        this.turno = new TurnoRojas();
        this.estrategia = setEstrategia(varianteTriunfo);
        this.board();
    }

    public Estrategia setEstrategia(char varianteTriunfo){
        estrategias.stream()
                .filter(estrategia -> estrategia.canHandle(varianteTriunfo))
                .forEach(estrategia -> this.estrategia = estrategia);
        return estrategia;
    }

    public void board(){
        for (int i = 0; i < this.height; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < this.base; j++) {
                row.add('-');
            }
            this.board.add(row);
        }
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            sb.append("|");
            for (int j = 0; j < this.base; j++) {
                sb.append(this.board.get(i).get(j));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }


    public void playRedkAt(int position) {
        if (position < 0 || position >= this.base) {
            throw new RuntimeException("Invalid position");
        }
        if (this.board.get(0).get(position) != '-') {
            throw new RuntimeException("Position already occupied");
        }
        if( turno instanceof TurnoAzul){
            throw new RuntimeException("Turno de azules");
        }
        int row = this.height - 1;
        while (row >= 0 && this.board.get(row).get(position) != '-') {
            row--;
        }
        this.board.get(row).set(position, this.redChar);
        turno = turno.alternarTurno();
        finished = estrategia.checkWin(this, this.redChar);
    }


    public void playBlueAt(int position) {
        if (position < 0 || position >= this.base) {
            throw new RuntimeException("Invalid position");
        }
        if (this.board.get(0).get(position) != '-') {
            throw new RuntimeException("Position already occupied");
        }
        if( turno instanceof TurnoRojas){
            throw new RuntimeException("Turno de rojas");
        }
        int row = this.height - 1;
        while (row >= 0 && this.board.get(row).get(position) != '-') {
            row--;
        }
        this.board.get(row).set(position, this.blueChar);
        turno = turno.alternarTurno();
        finished = estrategia.checkWin(this, this.blueChar);
    }

    public boolean verticalWin(char color) {
        for (int i = 0; i < this.base; i++) {
            int count = 0;
            for (int j = 0; j < this.height; j++) {
                if (this.board.get(j).get(i) == color) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean horizontalWin(char color) {
        return board.stream().anyMatch(row -> row.stream().filter(c -> c == color).count() == 4);
    }

    public boolean diagonalWin(char color) {
        return diagonalWinLeft(color) || diagonalWinRight(color);
    }

    public boolean diagonalWinLeft(char color) {
        for (int i = 0; i < this.height - 3; i++) {
            for (int j = 0; j < this.base - 3; j++) {
                if (this.board.get(i).get(j) == color &&
                        this.board.get(i + 1).get(j + 1) == color &&
                        this.board.get(i + 2).get(j + 2) == color &&
                        this.board.get(i + 3).get(j + 3) == color) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean diagonalWinRight(char color) {
        for (int i = 0; i < this.height - 3; i++) {
            for (int j = 3; j < this.base; j++) {
                if (this.board.get(i).get(j) == color &&
                        this.board.get(i + 1).get(j - 1) == color &&
                        this.board.get(i + 2).get(j - 2) == color &&
                        this.board.get(i + 3).get(j - 3) == color) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean tie() {
        return board.stream().allMatch(row -> row.stream().noneMatch(c -> c == '-'));
    }
    public boolean finished() {
        return finished || this.tie();
    }
}

