package Model;

import java.util.ArrayList;

public class BlackPawn extends Piece{

    int movesMade = 0;

    public BlackPawn() {
        super("Black", "Pawn");
    }

    public int getMovesMade() {
        return movesMade;
    }

}