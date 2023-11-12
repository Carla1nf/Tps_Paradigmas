package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Linea {
    public static final char EMPTY = '-';
    public static final String TURNO_AZULES = "Turno de Azules";
    public static final String TURNO_ROJAS = "Turno de Rojas";
    public static final String COLUMNA_LLENA = "Columna llena";
    public static final String JUEGO_TERMINADO = "Juego terminado!";
    public static final String FUERA_DE_TABLERO = "Fuera de tablero";
    public static final String TAMANO_INVALIDO = "Error, el tamaño debe ser mayor a 0";
    public static final String ESTRATEGIA_INVALIDA = "Error, la estrategia debe ser A, B o C";
    public static final String EMPATE = "Empate!";
    private final ArrayList<ArrayList<Character>> board;
    private final int base;
    private final int height;
    private final char redChar;
    private final char blueChar;
    private boolean finished;
    private Turno turno;
    private final Estrategia estrategia;
    public static char ganador;
    private final ArrayList<Estrategia> estrategias = new ArrayList<>(Arrays.asList(new EstrategiaA(), new EstrategiaB(), new EstrategiaC()));
    public Linea(int base, int height, char varianteTriunfo) {
        if (base <= 0 || height <= 0) {
            throw new RuntimeException(TAMANO_INVALIDO);
        }
        this.base = base;
        this.height = height;
        this.redChar = 'X';
        this.blueChar = 'O';
        this.board = new ArrayList<>();
        this.finished = false;
        this.turno = new TurnoRojas();
        this.estrategia = setEstrategia(varianteTriunfo);
        this.setUpBoard();
    }

    public Estrategia setEstrategia(char varianteTriunfo){
        return estrategias.stream().filter(estrategia -> estrategia.canHandle(varianteTriunfo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ESTRATEGIA_INVALIDA));
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

        return board + this.turno.getTurno();
    }

    public void playRedkAt(int columna) {
        turno = turno.jugarRojo(this, columna-1);
        finished = estrategia.checkWin(this, this.redChar);
        checkFinalJuego(this.redChar);
    }

    public void playBlueAt(int columna) {
        turno = turno.jugarAzul(this, columna-1);
        finished = estrategia.checkWin(this, this.blueChar);
        checkFinalJuego(this.blueChar);
    }

    public int rowColumnCheck(int column){
        checkBounds(column);
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

    public boolean diagonalWin(char color) {
        return diagonalWinLeft(color) || diagonalWinRight(color);
    }

    public void checkBounds(int columna){
        IntStream.range(0, this.base)
                .filter(i -> i == columna)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(FUERA_DE_TABLERO));
    }

    public void jugar(int columna, char color){
        int row = rowColumnCheck(columna);
        this.board.get(row).set(columna, color);
    }

    private void checkFinalJuego(char color){
        if(finished){
           ganador = color;
           this.turno = new JuegoTerminado(color);
        }
        if(empate()){
            this.turno = new Empate();
        }
    }

    public boolean empate() {
        return board.stream().allMatch(row -> row.stream().noneMatch(c -> c == EMPTY));
    }

    public boolean finished() {
        return finished || this.empate();
    }

    public char getRedChar(){
        return this.redChar;
    }

    public char getBlueChar(){
        return this.blueChar;
    }
}

