package Model;

import java.util.ArrayList;

public class King extends Piece{

    int movesMade = 0;

    public King(String color) {
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
