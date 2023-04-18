package Model;

public class BlackPawn extends Piece{

    public BlackPawn() {
        super("Black", "Pawn");
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(1,-1));
    }

}