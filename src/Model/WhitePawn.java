package Model;

public class WhitePawn extends Piece{

    int movesMade = 0;

    public WhitePawn() {
        super("White", "Pawn");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(1,1));
    }

    public int getMovesMade() {
        return movesMade;
    }


}
