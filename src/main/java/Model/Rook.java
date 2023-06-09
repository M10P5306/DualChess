package Model;

import javax.swing.*;

/**
 * Representation of the Rook piece.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */

public class Rook extends Piece implements SpecialPiece{

    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a Rook.
     * @param color determines ownership of the piece.
     */
    public Rook(String color) {
        super(color, "Rook");
        super.setSoundFilePath("src/main/java/Sounds/stone-push-Rook.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/main/java/Icons/WhiteRook.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/main/java/Icons/BlackRook.png"));
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
