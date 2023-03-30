package Model;

import java.util.ArrayList;

public class King extends Piece{

    int movesMade = 0;

    public King(String color) {
        super(color, "King");
    }

    public int getMovesMade() {
        return movesMade;
    }

}
