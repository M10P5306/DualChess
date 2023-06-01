package ExtremeMode.Model;

import Model.Coordinate;
import Model.SpecialPiece;

import javax.swing.*;

public class RookExtreme extends PieceExtreme {
    public RookExtreme(String color) {
        super(color, "Rook", 15);
        super.setSoundFilePath("src/Sounds/stone-push-Rook.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/Icons/WhiteRook.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/Icons/BlackRook.png"));
        }
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(0,3));
        addMoves(new Coordinate(0,4));
        addMoves(new Coordinate(0,5));
        addMoves(new Coordinate(0,6));
        addMoves(new Coordinate(0,7));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(0,-3));
        addMoves(new Coordinate(0,-4));
        addMoves(new Coordinate(0,-5));
        addMoves(new Coordinate(0,-6));
        addMoves(new Coordinate(0,-7));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(2,0));
        addMoves(new Coordinate(3,0));
        addMoves(new Coordinate(4,0));
        addMoves(new Coordinate(5,0));
        addMoves(new Coordinate(6,0));
        addMoves(new Coordinate(7,0));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-2,0));
        addMoves(new Coordinate(-3,0));
        addMoves(new Coordinate(-4,0));
        addMoves(new Coordinate(-5,0));
        addMoves(new Coordinate(-6,0));
        addMoves(new Coordinate(-7,0));
    }
}
