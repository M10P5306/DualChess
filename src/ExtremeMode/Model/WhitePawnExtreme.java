package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class WhitePawnExtreme extends PieceExtreme {
    public WhitePawnExtreme() {
        super("White", "Pawn", 2);
        super.setIcon(new ImageIcon("src/Icons/WhitePawn.png"));
        super.setSoundFilePath("src/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(1,1));
    }
}
