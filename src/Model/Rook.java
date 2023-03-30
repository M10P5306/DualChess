package Model;

import java.util.ArrayList;

public class Rook extends Piece{

    int movesMade = 0;

    public Rook(String color) {
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
