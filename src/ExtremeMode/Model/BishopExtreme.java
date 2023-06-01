package ExtremeMode.Model;

import Model.Coordinate;
import Model.SpecialPiece;

import javax.swing.*;

public class BishopExtreme extends PieceExtreme implements SpecialPiece {
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
        addMoves(new Coordinate(2, 2));
        addMoves(new Coordinate(3, 3));
        addShots(new Coordinate(4, 4));
        //addMoves(new Coordinate(5, 5));
        //addMoves(new Coordinate(6, 6));
        //addMoves(new Coordinate(7, 7));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(-2, -2));
        addMoves(new Coordinate(-3, -3));
        addShots(new Coordinate(-4, -4));
        //addMoves(new Coordinate(-5, -5));
        //addMoves(new Coordinate(-6, -6));
        //addMoves(new Coordinate(-7, -7));
        addMoves(new Coordinate(-1, 1));
        addMoves(new Coordinate(-2, 2));
        addMoves(new Coordinate(-3, 3));
        addShots(new Coordinate(-4, 4));
        //addMoves(new Coordinate(-5, 5));
        //addMoves(new Coordinate(-6, 6));
        //addMoves(new Coordinate(-7, 7));
        addMoves(new Coordinate(1, -1));
        addMoves(new Coordinate(2, -2));
        addMoves(new Coordinate(3, -3));
        addShots(new Coordinate(4, -4));
        //addMoves(new Coordinate(5, -5));
        //addMoves(new Coordinate(6, -6));
        //addMoves(new Coordinate(7, -7));
    }
}
