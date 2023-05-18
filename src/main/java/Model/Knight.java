package Model;

import javax.swing.*;

/**
 * Representation of the Knight piece.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */

public class Knight extends Piece{

    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a Knight.
     * @param color determines ownership of the piece.
     */
    public Knight(String color) {
        super(color, "Knight");
        super.setSoundFilePath("src/main/java/Sounds/horse.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/main/java/Icons/WhiteKnight.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/main/java/Icons/BlackKnight.png"));
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
