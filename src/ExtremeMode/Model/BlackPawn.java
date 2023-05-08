package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class BlackPawn extends Piece {
    public BlackPawn() {
        super("Black", "Pawn", 2);
        super.setIcon(new ImageIcon("src/Icons/BlackPawn.png"));
        super.setSoundFilePath("src/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addShots(new Coordinate(-1,-1));
        addShots(new Coordinate(1,-1));
    }
}
