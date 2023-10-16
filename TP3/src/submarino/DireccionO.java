package submarino;

public class DireccionO extends Direccionalidad{
    public Direccionalidad girarL() {
        return new DireccionS();
    }

    public Direccionalidad girarR() {
        return new DireccionN();
    }

    public Coord avanzar(Coord coord) {
        return new Coord(coord.x - 1, coord.y);
    }
}
