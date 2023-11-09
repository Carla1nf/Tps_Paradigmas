package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Linea {
    public static final char EMPTY = '-';
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
        IntStream.range(0, this.height)
                .forEach(i -> {
                    ArrayList<Character> row = new ArrayList<>();
                    IntStream.range(0, this.base)
                            .forEach(j -> row.add(EMPTY));
                    this.board.add(row);
                });}

    public String show() {
        return this.board.stream()
                .map(row -> "|" + row.stream()
                        .map(String::valueOf)
                        .reduce("", String::concat) + "|\n")
                .reduce("", String::concat);
    }

    public void playRedkAt(int position) {
        if (turno instanceof TurnoAzul){
            throw new RuntimeException("Turno de azules");
        }
        int row = rowColumnCheck(position);
        this.board.get(row).set(position, this.redChar);
        turno = turno.alternarTurno();
        finished = estrategia.checkWin(this, this.redChar);
    }

    public void playBlueAt(int position) {
        if( turno instanceof TurnoRojas){
            throw new RuntimeException("Turno de rojas");
        }
        int row = rowColumnCheck(position);
        this.board.get(row).set(position, this.blueChar);
        turno = turno.alternarTurno();
        finished = estrategia.checkWin(this, this.blueChar);
    }

    public int rowColumnCheck(int position){
        //buscar la primera fila vacia en la columna desde abajo hacia arriba

        return IntStream.range(0, this.height)
                .filter(i -> this.board.get(i).get(position) == EMPTY)
                .reduce((a, b) -> b)
                .orElseThrow(() -> new RuntimeException("Columna llena"));
    }

    public boolean verticalWin(char color) {
        return IntStream.range(0, this.base)
                .anyMatch(column -> IntStream.range(0, this.height)
                        .filter(row -> this.board.get(row).get(column) == color)
                        .count() == 4);
    }

    public boolean horizontalWin(char color) {
        return board.stream().anyMatch(row -> row.stream().filter(c -> c == color).count() == 4);
    }

    public boolean diagonalWin(char color) {
        return diagonalWinLeft(color) || diagonalWinRight(color);
    }

    public boolean diagonalWinLeft(char color) {
        return IntStream.range(0, this.height - 3)
                .anyMatch(i -> IntStream.range(0, this.base - 3)
                        .anyMatch(j -> this.board.get(i).get(j) == color &&
                                this.board.get(i + 1).get(j + 1) == color &&
                                this.board.get(i + 2).get(j + 2) == color &&
                                this.board.get(i + 3).get(j + 3) == color));
    }

    public boolean diagonalWinRight(char color) {
        return IntStream.range(0, this.height - 3)
                .anyMatch(i -> IntStream.range(3, this.base)
                        .anyMatch(j -> this.board.get(i).get(j) == color &&
                                this.board.get(i + 1).get(j - 1) == color &&
                                this.board.get(i + 2).get(j - 2) == color &&
                                this.board.get(i + 3).get(j - 3) == color));
    }

    public boolean tie() {
        return board.stream().allMatch(row -> row.stream().noneMatch(c -> c == EMPTY));
    }
    public boolean finished() {
        return finished || this.tie();
    }
}

