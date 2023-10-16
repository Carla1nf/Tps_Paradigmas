package submarino;

public class DireccionS extends Direccionalidad {
    public Direccionalidad girarL() {
        return new DireccionE();
    }

    public Direccionalidad girarR() {
        return new DireccionO();
    }

    public Coord avanzar(Coord coord) {
        return new Coord(coord.x, coord.y - 1);
    }
}
