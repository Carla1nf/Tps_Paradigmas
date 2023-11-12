package linea;

public class JuegoEnProgreso extends Juego{
    public Juego checkFinished(Linea game, Turno turno){
        if(game.finished()){
            return new JuegoTerminado();
    }
        return this;
    }
}
