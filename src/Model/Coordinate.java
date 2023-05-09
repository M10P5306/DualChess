package Model;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        Coordinate coordinate = (Coordinate) o;

        if (coordinate.getX() == this.x && coordinate.getY() == this.y) {
            return true;
        }
        return false;
    }

    public String toString() {
        return x + "," + y;
    }

    @Override
    public int hashCode() {
        return String.format("%d,%d", x, y).hashCode();
    }
}