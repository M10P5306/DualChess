package Model;

import javax.swing.*;

public class Knight extends Piece{

    public Knight(String color) {
        super(color, "Knight");
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
