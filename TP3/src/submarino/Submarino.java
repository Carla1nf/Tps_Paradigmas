package submarino;

import java.lang.Math;
public class Submarino {

    public int posicionX;
    public int posicionY;
    public int posicionZ;
    public double direccion;
    public Submarino() {
        posicionX = 0;
        posicionY = 0;
        posicionZ = 0;
        direccion = 0;
    }

    public boolean estaEnLaSuperficie() {
        return posicionZ == 0;
    }

    public void comando(String accion){
        if (accion == "r" || accion == "l"){
            girar(accion);
        }
        if (accion == "u"){
            ascender();
        }
        if (accion == "d"){
            descender();
        }
        if (accion == "f"){
            avanzar();
        }
        if (accion == "m"){
            liberarCapsula();
        }
    }
    public void ascender() {
        if (posicionZ < 0){
        posicionZ += 1;
        }
    }

    public void descender() {
        posicionZ -= 1;
    }

    public void girar(String sentido) {
        if (sentido == "r") {
            direccion -= Math.PI/2;
        }
        else{
            direccion += Math.PI/2;
        }
    }

    public void avanzar() {
        posicionX += Math.cos(direccion);
        posicionY += Math.sin(direccion);
    }

    public void liberarCapsula() {
        if(posicionZ < -1){
            throw new UnsupportedOperationException( "Se destruyó el submarino por exceso de chocolate" );
        }
    }

}