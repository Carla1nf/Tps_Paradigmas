package linea;

public class TurnoAzul extends Turno{
    public Turno alternarTurno(){
         return new TurnoRojas();
    }
}
