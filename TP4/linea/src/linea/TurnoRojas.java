package linea;

public class TurnoRojas extends Turno{
    public Turno turnoRojo(){
        return new TurnoAzul();
    }
    public Turno turnoAzul(){
        throw new RuntimeException(Linea.TURNO_ROJAS);
    }

}
