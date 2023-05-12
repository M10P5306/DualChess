package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class KnightExtreme extends PieceExtreme {
    private int charge;
    public KnightExtreme(String color) {
        super(color, "Knight", 10);
        super.setSoundFilePath("src/Sounds/horse.wav");
        charge = 0;
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

    public void charge() {
        charge++;
        if (charge < 1) {
            addMoves(new Coordinate(0, 1));
            addMoves(new Coordinate(0, 2));
            addMoves(new Coordinate(0, 3));
            addMoves(new Coordinate(0, 4));
            addMoves(new Coordinate(0, 5));
            addMoves(new Coordinate(0, 6));
            addMoves(new Coordinate(0, 7));
        }
    }
}
