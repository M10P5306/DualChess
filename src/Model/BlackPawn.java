package Model;

import javax.swing.*;

public class BlackPawn extends Piece implements Pawn {

    public BlackPawn() {
        super("Black", "Pawn");
        super.setIcon(new ImageIcon("src/Icons/BlackPawn.png"));
        super.setSoundFilePath("src/Sounds/Pawn.wav");
        addMoves(new Coordinate(0, -1));
        addMoves(new Coordinate(0, -2));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(1, -1));
    }

}