package linea;

public class JuegoTerminado extends Juego{
    public Juego checkFinished(Linea game, Turno turno){
        throw new RuntimeException(Linea.JUEGO_TERMINADO);
    }
}
