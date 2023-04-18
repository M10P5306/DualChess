package Model;

public class Bishop extends Piece{

    public Bishop(String color) {
        super(color, "Bishop");
        addMoves(new Coordinate(1, 1));
        addMoves(new Coordinate(2,2));
        addMoves(new Coordinate(3,3));
        addMoves(new Coordinate(4,4));
        addMoves(new Coordinate(5,5));
        addMoves(new Coordinate(6,6));
        addMoves(new Coordinate(7,7));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(-2,-2));
        addMoves(new Coordinate(-3,-3));
        addMoves(new Coordinate(-4,-4));
        addMoves(new Coordinate(-5,-5));
        addMoves(new Coordinate(-6,-6));
        addMoves(new Coordinate(-7,-7));
        addMoves(new Coordinate(-1, 1));
        addMoves(new Coordinate(-2,2));
        addMoves(new Coordinate(-3,3));
        addMoves(new Coordinate(-4,4));
        addMoves(new Coordinate(-5,5));
        addMoves(new Coordinate(-6,6));
        addMoves(new Coordinate(-7,7));
        addMoves(new Coordinate(1, -1));
        addMoves(new Coordinate(2,-2));
        addMoves(new Coordinate(3,-3));
        addMoves(new Coordinate(4,-4));
        addMoves(new Coordinate(5,-5));
        addMoves(new Coordinate(6,-6));
        addMoves(new Coordinate(7,-7));
    }

}
