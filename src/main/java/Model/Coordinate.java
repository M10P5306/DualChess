package Model;

/**
 * Class that links the possition of squares and pieces to the board on the GUI.
 * @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */
public class Coordinate {
    /**
     * x position.
     */
    private int x;
    /**
     * y position.
     */
    private int y;

    /**
     * Constructor for the above-mentioned instance variables.
     * @param x position.
     * @param y position.
     */
    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @return the x position of the square or piece.
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return the y position of the square or piece.
     */
    public int getY() {
        return y;
    }

    /**
     * Compares this to another Coordinate.
     * @param o Object of the typ Coordinate.
     * @return true if this and o is the same position.
     */
    @Override
    public boolean equals(Object o) {
        Coordinate coordinate = (Coordinate) o;

        if (coordinate.getX() == this.x && coordinate.getY() == this.y) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return x and y position on the board.
     */
    public String toString() {
        return x + "," + y;
    }

    /**
     * Used by the equals method.
     * @return int for the equals method to compare to another Coordinate.
     */
    @Override
    public int hashCode() {
        return String.format("%d,%d", x, y).hashCode();
    }
}