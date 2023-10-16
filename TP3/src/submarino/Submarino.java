package submarino;

import java.util.HashMap;
import java.util.Map;

public class Submarino {
    private final Map<Character, Runnable> comandos = new HashMap<>();
    public Profundidad profundidad;
    public Direccionalidad direccion;
    public Coord coord;

    public Submarino(Direccionalidad direccion, Coord coord, Profundidad profundidad) {
        this.direccion = direccion;
        this.coord = coord;
        this.profundidad = profundidad;
        controladorComandos();
    }

    public void controladorComandos() {
        comandos.put('r', () -> direccion = direccion.girarR());
        comandos.put('l', () -> direccion = direccion.girarL());
        comandos.put('u', () -> profundidad = profundidad.ascender());
        comandos.put('d', () -> profundidad = profundidad.descender());
        comandos.put('f', () -> coord = direccion.avanzar(coord));
        comandos.put('m', () -> profundidad.liberarCapsula());
    }
    public void ejecutarComandos(String comands) {
        comands.chars().forEach(each -> comandos.get((char) each).run());
    }
}