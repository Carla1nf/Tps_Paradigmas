package linea;

public class TurnoAzul extends Turno{
    public Turno jugarRojo(Linea game, int columna){
        throw new RuntimeException(Linea.TURNO_AZULES);
    }
    public Turno jugarAzul(Linea game, int columna){
        game.jugar(columna,game.getBlueChar());
        return new TurnoRojas();
    }
}
