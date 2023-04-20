package Model;

public class BlackPawn extends Piece{

    public BlackPawn() {
        super("Black", "Pawn", "C:\\Users\\edinj\\Downloads\\mixkit-gear-fast-lock-tap-2857.wav");
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(1,-1));
    }

}