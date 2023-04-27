package Model;

import javax.swing.*;

public class King extends Piece{

    public King(String color) {
        super(color, "King");
        super.setSoundFilePath("src/Sounds/hello_king.wav");
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
    }

}
