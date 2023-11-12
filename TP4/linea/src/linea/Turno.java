package linea;

public abstract class Turno {
    public abstract Turno jugarRojo(Linea game, int columna);
    public abstract Turno jugarAzul(Linea game, int columna);
    public abstract String getTurno();
}
