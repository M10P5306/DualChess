package Model;

import java.util.ArrayList;

public class WhitePawn extends Piece{

    int movesMade = 0;

    public WhitePawn() {
        super("White", "Pawn");
    }

    public int getMovesMade() {
        return movesMade;
    }

}
