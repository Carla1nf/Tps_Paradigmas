package linea;

public class Empate extends Turno {
    public Turno jugarRojo(Linea game, int columna){
        throw new RuntimeException(Linea.EMPATE);
    }
    public Turno jugarAzul(Linea game, int columna){
        throw new RuntimeException(Linea.EMPATE);
    }
    public String getTurno(){
        return Linea.EMPATE;
    }

}
