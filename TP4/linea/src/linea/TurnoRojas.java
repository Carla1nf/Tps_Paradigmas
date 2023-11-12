package linea;

public class TurnoRojas extends Turno{
    public Turno jugarRojo(Linea game, int columna){
        game.jugar(columna,game.getRedChar());
        return new TurnoAzul();
    }
    public Turno jugarAzul(Linea game, int columna){
        throw new RuntimeException(Linea.TURNO_ROJAS);
    }

}
