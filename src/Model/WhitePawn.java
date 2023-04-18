package Model;

public class WhitePawn extends Piece{

    public WhitePawn() {
        super("White", "Pawn");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(1,1));
    }

}
