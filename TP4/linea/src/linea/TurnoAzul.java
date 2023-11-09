package linea;

public class TurnoAzul extends Turno{
    public Turno turnoRojo(){
        throw new RuntimeException(Linea.TURNO_AZULES);
    }
    public Turno turnoAzul(){
        return new TurnoRojas();
    }
}
