package submarino;

import java.util.ArrayList;

public class Submarino {
    private final ArrayList<Comando> comandos = new ArrayList<>();
    private Profundidad profundidad;
    private Direccionalidad direccion;
    private Coord coord;

    public Submarino(Direccionalidad direccion, Coord coord, Profundidad profundidad) {
        this.direccion = direccion;
        this.coord = coord;
        this.profundidad = profundidad;

        comandos.add(new CommandLeft());
        comandos.add(new CommandRight());
        comandos.add(new CommandUp());
        comandos.add(new CommandDown());
        comandos.add(new CommandForward());
        comandos.add(new CommandRelease());
    }

    public Coord getPosition() {
        return coord;
    }
    public Direccionalidad getDirection() {
        return direccion;
    }
    public int getProfundidad() {
        return profundidad.getZ();
    }

    public void turnLeft() {
        direccion = direccion.girarL();
    }
    public void turnRight() {
        direccion = direccion.girarR();
    }
    public void ascend() {
        profundidad = profundidad.ascender();
    }
    public void down() {
        profundidad = profundidad.descender();
    }
    public void forward() {
        coord = direccion.avanzar(coord);
    }
    public void release() {
        profundidad.liberarCapsula();
    }
    public Submarino ejecutarComandos(String comands) {
        comands.chars().forEach(each -> this.comandos.stream()
                .filter(comando -> comando.canHandle((char) each))
                .forEach(comando -> comando.execute(this)));
        return this;
    }
}