package linea;

public class JuegoTerminado extends Turno{
    private String ganador;
    public JuegoTerminado(char ganador){
        this.ganador = ganador == 'X' ? "Rojas" : "Azules";
    }
    public Turno jugarRojo(Linea game, int columna){
        throw new RuntimeException(Linea.JUEGO_TERMINADO);
    }
    public Turno jugarAzul(Linea game, int columna){
        throw new RuntimeException(Linea.JUEGO_TERMINADO);
    }
    public String getTurno(){
        return Linea.JUEGO_TERMINADO + "\nGanador: " + ganador + "\n";
    }
}
