package submarino;

public abstract class Direccionalidad {
    public abstract Direccionalidad girarL();
    public abstract Direccionalidad girarR();
    public abstract Coord avanzar(Coord coord);

    public boolean equals(Object o) {
        return o instanceof Direccionalidad;
    }
}
