package submarino;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Object o) {
        return o instanceof Coord && ((Coord) o).x == x && ((Coord) o).y == y;
    }
}
