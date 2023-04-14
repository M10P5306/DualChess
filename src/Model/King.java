package Model;

import java.util.ArrayList;

public class King extends Piece{

    int movesMade = 0;

    public King(String color) {
        super(color, "King");
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(1,1));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(1,-1));
    }

    public int getMovesMade() {
        return movesMade;
    }

}
