package submarino;

public class DireccionO extends Direccionalidad{
    public Direccionalidad girarL() {
        return new DireccionN();
    }

    public Direccionalidad girarR() {
        return new DireccionS();
    }

    public Coord avanzar(Coord coord) {
        return new Coord(coord.x - 1, coord.y);
    }
}
}
