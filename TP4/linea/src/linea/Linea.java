package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Linea {
    public static final char EMPTY = '-';
    public static final String TURNO_AZULES = "Turno de azules";
    public static final String TURNO_ROJAS = "Turno de rojas";
    public static final String COLUMNA_LLENA = "Columna llena";
    public static final String JUEGO_TERMINADO = "Juego terminado";
    private final int base;
    private final int height;
    private final ArrayList<ArrayList<Character>> board;
    private final char redChar;
    private final char blueChar;
    private boolean finished;
    private Turno turno;
    private Estrategia estrategia;
    private final ArrayList<Estrategia> estrategias = new ArrayList<>(Arrays.asList(new EstrategiaA(), new EstrategiaB(), new EstrategiaC()));
    public Linea(int base, int height, char varianteTriunfo) {
        this.base = base < 0 ? 8 : base;
        this.height = height < 0 ? 8 : height;
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
        turno = turno.turnoRojo();
        if (finished()){
            throw new RuntimeException(JUEGO_TERMINADO);
        }
        if (!finished()){
        int row = rowColumnCheck(position);
        this.board.get(row).set(position, this.redChar);
        finished = estrategia.checkWin(this, this.redChar);
        }
        else{System.out.println("Ganó " + turno.getClass().getSimpleName() + "!");}

    }

    public void playBlueAt(int position) {
        turno = turno.turnoAzul();
        if (finished()){
            throw new RuntimeException(JUEGO_TERMINADO);
        }
        if (!finished()) {
            int row = rowColumnCheck(position);
            this.board.get(row).set(position, this.blueChar);
            finished = estrategia.checkWin(this, this.blueChar);
        }
        else{
            System.out.println("Ganó " + turno.getClass().getSimpleName() + "!");
        }
    }

    public int rowColumnCheck(int position){
        return IntStream.range(0, this.height)
                .filter(i -> this.board.get(i).get(position) == EMPTY)
                .reduce((a, b) -> b)
                .orElseThrow(() -> new RuntimeException(COLUMNA_LLENA));
    }

    public boolean verticalWin(char color) {
        return IntStream.range(0, this.base)
                        .anyMatch(column -> IntStream.range(0, this.height - 3)
                        .anyMatch(row -> this.board.get(row).get(column) == color &&
                                this.board.get(row + 1).get(column) == color &&
                                this.board.get(row + 2).get(column) == color &&
                                this.board.get(row + 3).get(column) == color));
    }

    public boolean horizontalWin(char color) {
        return IntStream.range(0, this.height)
                        .anyMatch(row -> IntStream.range(0, this.base - 3)
                        .anyMatch(column -> this.board.get(row).get(column) == color &&
                                this.board.get(row).get(column + 1) == color &&
                                this.board.get(row).get(column + 2) == color &&
                                this.board.get(row).get(column + 3) == color));
    }

    public boolean diagonalWin(char color) {
        return diagonalWinLeft(color) || diagonalWinRight(color);
    }

    public boolean diagonalWinLeft(char color) {
        return IntStream.range(0, this.height - 3)
                .anyMatch(row -> IntStream.range(0, this.base - 3)
                        .anyMatch(column -> this.board.get(row).get(column) == color &&
                                this.board.get(row + 1).get(column + 1) == color &&
                                this.board.get(row + 2).get(column + 2) == color &&
                                this.board.get(row + 3).get(column + 3) == color));
    }

    public boolean diagonalWinRight(char color) {
        return IntStream.range(0, this.height - 3)
                .anyMatch(row -> IntStream.range(3, this.base)
                        .anyMatch(column -> this.board.get(row).get(column) == color &&
                                this.board.get(row + 1).get(column - 1) == color &&
                                this.board.get(row + 2).get(column - 2) == color &&
                                this.board.get(row + 3).get(column - 3) == color));
    }

    public boolean tie() {
        return board.stream().allMatch(row -> row.stream().noneMatch(c -> c == EMPTY));
    }
    public boolean finished() {
        return finished || this.tie();
    }
}

