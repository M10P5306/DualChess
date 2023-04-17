package Model;

public class Knight extends Piece{

    public Knight(String color) {
        super(color, "Knight");
        addMoves(new Coordinate(-1,2));
        addMoves(new Coordinate(1,2));
        addMoves(new Coordinate(-2,1));
        addMoves(new Coordinate(-2,-1));
        addMoves(new Coordinate(2,1));
        addMoves(new Coordinate(2,-1));
        addMoves(new Coordinate(1,-2));
        addMoves(new Coordinate(-1,-2));
    }

}
