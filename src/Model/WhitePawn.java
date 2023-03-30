package Model;

import java.util.ArrayList;

public class WhitePawn extends Piece{

    int movesMade = 0;

    public WhitePawn(String color) {
        super(color);
    }

    public int getMovesMade() {
        return movesMade;
    }

    @Override
    public ArrayList validMoves(Coordinate currentPosition) {
        return null;
    }
}
