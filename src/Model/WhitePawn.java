package Model;

import javax.swing.*;

public class WhitePawn extends Piece implements Pawn{

    public WhitePawn() {
        super("White", "Pawn");
        super.setIcon(new ImageIcon("src/Icons/WhitePawn.png"));
        super.setSoundFilePath("src/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(1,1));
    }

}
