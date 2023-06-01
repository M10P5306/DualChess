package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class BlackPawnExtreme extends PieceExtreme {
    public BlackPawnExtreme() {
        super("Black", "Pawn", 5);
        super.setIcon(new ImageIcon("src/Icons/BlackPawn.png"));
        super.setSoundFilePath("src/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(1,-1));
    }
}
