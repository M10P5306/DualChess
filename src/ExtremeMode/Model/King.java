package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;

public class King extends Piece {
    public King(String color, String name, int points) {
        super(color, "King", 50);
        //super.setSoundFilePath();
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/Icons/WhiteKing.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/Icons/BlackKing.png"));
        }
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(1,1));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(1,-1));
        addMoves(new Coordinate(2,0));
        addMoves(new Coordinate(-2,0));

        addShots(new Coordinate(-1,2));
        addShots(new Coordinate(0,2));
        addShots(new Coordinate(1,2));
    }
}
