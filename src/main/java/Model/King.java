package Model;

import javax.swing.*;

/**
 * Representation of the Bishop piece.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */
public class King extends Piece{
    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a King.
     * @param color determines ownership of the piece.
     */
    public King(String color) {
        super(color, "King");
        super.setSoundFilePath("src/main/java/Sounds/hello_king.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/main/java/Icons/WhiteKing.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/main/java/Icons/BlackKing.png"));
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
