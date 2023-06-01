package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class KnightExtreme extends PieceExtreme {
    public KnightExtreme(String color) {
        super(color, "Knight", 15);
        super.setSoundFilePath("src/Sounds/horse.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/Icons/WhiteKnight.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/Icons/BlackKnight.png"));
        }
        addMoves(new Coordinate(-1,2));
        addMoves(new Coordinate(1,2));
        addMoves(new Coordinate(-2,1));
        addMoves(new Coordinate(-2,-1));
        addMoves(new Coordinate(2,1));
        addMoves(new Coordinate(2,-1));
        addMoves(new Coordinate(1,-2));
        addMoves(new Coordinate(-1,-2));
    }
}
