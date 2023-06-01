package ExtremeMode.Model;

import Model.Coordinate;
import Model.SpecialPiece;

import javax.swing.*;

public class QueenExtreme extends PieceExtreme implements SpecialPiece {
    public QueenExtreme(String color) {
        super(color, "Queen", 30);
        super.setSoundFilePath("src/Sounds/tada-fanfare-Queen.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/Icons/WhiteQueen.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/Icons/BlackQueen.png"));
        }
        addMoves(new Coordinate(1, 1));
        addMoves(new Coordinate(2,2));
        addMoves(new Coordinate(3,3));
        addShots(new Coordinate(4,4));
        //addShots(new Coordinate(5,5));
        //addMoves(new Coordinate(6,6));
        //addMoves(new Coordinate(7,7));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(-2,-2));
        addMoves(new Coordinate(-3,-3));
        addShots(new Coordinate(-4,-4));
        //addShots(new Coordinate(-5,-5));
        //addShots(new Coordinate(-6,-6));
        //addMoves(new Coordinate(-7,-7));
        addMoves(new Coordinate(-1, 1));
        addMoves(new Coordinate(-2,2));
        addMoves(new Coordinate(-3,3));
        addShots(new Coordinate(-4,4));
        //addShots(new Coordinate(-5,5));
        //addMoves(new Coordinate(-6,6));
        //addMoves(new Coordinate(-7,7));
        addMoves(new Coordinate(1, -1));
        addMoves(new Coordinate(2,-2));
        addMoves(new Coordinate(3,-3));
        addShots(new Coordinate(4,-4));
        //addShots(new Coordinate(5,-5));
        //addMoves(new Coordinate(6,-6));
        //addMoves(new Coordinate(7,-7));
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(0,3));
        addShots(new Coordinate(0,4));
        //addShots(new Coordinate(0,5));
        //addMoves(new Coordinate(0,6));
        //addMoves(new Coordinate(0,7));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(0,-3));
        addShots(new Coordinate(0,-4));
        //addShots(new Coordinate(0,-5));
        //addMoves(new Coordinate(0,-6));
        //addMoves(new Coordinate(0,-7));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(2,0));
        addMoves(new Coordinate(3,0));
        addShots(new Coordinate(4,0));
        //addShots(new Coordinate(5,0));
        //addMoves(new Coordinate(6,0));
        //addMoves(new Coordinate(7,0));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-2,0));
        addMoves(new Coordinate(-3,0));
        addShots(new Coordinate(-4,0));
        //addShots(new Coordinate(-5,0));
        //addMoves(new Coordinate(-6,0));
        //addMoves(new Coordinate(-7,0));
    }
}
