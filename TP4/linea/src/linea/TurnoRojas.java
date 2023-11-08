package linea;

public class TurnoRojas extends Turno{
    public Turno alternarTurno(){
         return new TurnoAzul();
    }

}
