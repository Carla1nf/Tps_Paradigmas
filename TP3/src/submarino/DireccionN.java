package submarino;

public class DireccionN extends Direccionalidad{
    public Direccionalidad girarL() {
        return new DireccionO();
    }

    public Direccionalidad girarR() {
        return new DireccionE();
    }

    public Coord avanzar(Coord coord) {
        return new Coord(coord.x, coord.y + 1);
    }

}
