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
    private Juego estado;
    private final ArrayList<Estrategia> estrategias = new ArrayList<>(Arrays.asList(new EstrategiaA(), new EstrategiaB(), new EstrategiaC()));
    public Linea(int base, int height, char varianteTriunfo) {
        this.base = base < 0 ? 8 : base;
        this.height = height < 0 ? 8 : height;
        this.redChar = 'X';
        this.blueChar = 'O';
        this.board = new ArrayList<>();
        this.finished = false;
        this.turno = new TurnoRojas();
        this.estado = new JuegoEnProgreso();
        this.estrategia = setEstrategia(varianteTriunfo);
        this.setUpBoard();
    }

    public char getRedChar(){
        return this.redChar;
    }

    public char getBlueChar(){
        return this.blueChar;
    }

    public Estrategia setEstrategia(char varianteTriunfo){
        estrategias.stream()
                .filter(estrategia -> estrategia.canHandle(varianteTriunfo))
                .forEach(estrategia -> this.estrategia = estrategia);
        return estrategia;
    }

    public void setUpBoard(){
        IntStream.range(0, this.height)
                .forEach(i -> {
                    ArrayList<Character> row = new ArrayList<>();
                    IntStream.range(0, this.base)
                            .forEach(j -> row.add(EMPTY));
                    this.board.add(row);
                });}

    public String show() {
        StringBuilder board = new StringBuilder();

        this.board
                .forEach(row -> {
                    board.append("|");
                    row
                            .forEach(board::append);
                    board.append("|\n");
                });

        board.append(">").append(IntStream.range(1, this.base + 1)
                .mapToObj(String::valueOf)
                .reduce("", String::concat)).append("<\n");


        return board.toString();
    }

    public void playRedkAt(int columna) {
        estado = estado.checkFinished(this, turno);
        turno = turno.jugarRojo(this, columna-1);
        finished = estrategia.checkWin(this, this.redChar);
    }

    public void playBlueAt(int columna) {
        estado = estado.checkFinished(this, turno);
        turno = turno.jugarAzul(this, columna-1);
        finished = estrategia.checkWin(this, this.blueChar);
    }

    public void jugar(int columna, char color){
        int row = rowColumnCheck(columna);
        this.board.get(row).set(columna, color);
    }

    public int rowColumnCheck(int column){
        return IntStream.range(0, this.height)
                .filter(row -> this.board.get(row).get(column) == EMPTY)
                .reduce((a, b) -> b)
                .orElseThrow(() -> finished() ? new RuntimeException(JUEGO_TERMINADO) : new RuntimeException(COLUMNA_LLENA));
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

    public boolean empate() {
        return board.stream().allMatch(row -> row.stream().noneMatch(c -> c == EMPTY));
    }
    public boolean finished() {
        return finished || this.empate();
    }
}

