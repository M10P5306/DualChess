package Model;

public class King extends Piece{

    public King(String color) {
        super(color, "King", "C:\\Users\\edinj\\Downloads\\mixkit-gear-fast-lock-tap-2857.wav");
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(1,1));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(1,-1));
    }

}
