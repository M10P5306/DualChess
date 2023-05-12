package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class BishopExtreme extends PieceExtreme {
    public BishopExtreme(String color) {
        super(color, "Bishop", 10);
        super.setSoundFilePath("src/Sounds/sword-hit-bishop.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/Icons/WhiteBishop.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/Icons/BlackBishop.png"));
        }
        addMoves(new Coordinate(1, 1));
        addMoves(new Coordinate(2,2));
        addMoves(new Coordinate(3,3));
        addMoves(new Coordinate(4,4));
        addShots(new Coordinate(5,5));
        addShots(new Coordinate(6,6));
        addShots(new Coordinate(7,7));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(-2,-2));
        addMoves(new Coordinate(-3,-3));
        addMoves(new Coordinate(-4,-4));
        addShots(new Coordinate(-5,-5));
        addShots(new Coordinate(-6,-6));
        addShots(new Coordinate(-7,-7));
        addMoves(new Coordinate(-1, 1));
        addMoves(new Coordinate(-2,2));
        addMoves(new Coordinate(-3,3));
        addMoves(new Coordinate(-4,4));
        addShots(new Coordinate(-5,5));
        addShots(new Coordinate(-6,6));
        addShots(new Coordinate(-7,7));
        addMoves(new Coordinate(1, -1));
        addMoves(new Coordinate(2,-2));
        addMoves(new Coordinate(3,-3));
        addMoves(new Coordinate(4,-4));
        addShots(new Coordinate(5,-5));
        addShots(new Coordinate(6,-6));
        addShots(new Coordinate(7,-7));
    }
}
