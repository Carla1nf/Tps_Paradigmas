package submarino;

import java.lang.Math;
public class Submarino {
    public int profundidad;
    public Direccionalidad direccion;
    public Coord coord;

    public Submarino(Direccionalidad direccion, Coord coord) {
        this.direccion = direccion;
        this.coord = coord;
        this.profundidad = 0;
    }

    public boolean estaEnLaSuperficie() {
        return profundidad == 0;
    }

    public void comando(String accion){
        if (accion == "r"){
            direccion = direccion.girarR();
        }
        if (accion == "l"){
            direccion = direccion.girarL();
        }
        if (accion == "u"){
            ascender();
        }
        if (accion == "d"){
            descender();
        }
        if (accion == "f"){
            coord = direccion.avanzar(coord);
        }
        if (accion == "m"){
            liberarCapsula();
        }
    }
    public void ascender() {
        if (profundidad < 0){
            profundidad += 1;
        }
    }

    public void descender() {
        profundidad -= 1;
    }

    public void liberarCapsula() {
        if(profundidad < -1){
            throw new UnsupportedOperationException( "Se destruyó el submarino por exceso de chocolate" );
        }
    }

}