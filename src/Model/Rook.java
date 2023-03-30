package Model;

import java.util.ArrayList;

public class Rook extends Piece{

    int movesMade = 0;

    public Rook(String color) {
        super(color, "Rook");
    }

    public int getMovesMade() {
        return movesMade;
    }

}
