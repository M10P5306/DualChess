package Model;

import javax.swing.*;

/**
 * Representation of the Bishop piece.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */
public class Bishop extends Piece implements SpecialPiece{
    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a Bishop.
     * @param color determines ownership of the piece.
     */
    public Bishop(String color) {
        super(color, "Bishop");
        super.setSoundFilePath("src/main/java/Sounds/sword-hit-bishop.wav");
        if (super.getColor().equals("White")) {
            super.setIcon(new ImageIcon("src/main/java/Icons/WhiteBishop.png"));
        }
        else {
            super.setIcon(new ImageIcon("src/main/java/Icons/BlackBishop.png"));
        }
        addMoves(new Coordinate(1, 1));
        addMoves(new Coordinate(2,2));
        addMoves(new Coordinate(3,3));
        addMoves(new Coordinate(4,4));
        addMoves(new Coordinate(5,5));
        addMoves(new Coordinate(6,6));
        addMoves(new Coordinate(7,7));
        addMoves(new Coordinate(-1, -1));
        addMoves(new Coordinate(-2,-2));
        addMoves(new Coordinate(-3,-3));
        addMoves(new Coordinate(-4,-4));
        addMoves(new Coordinate(-5,-5));
        addMoves(new Coordinate(-6,-6));
        addMoves(new Coordinate(-7,-7));
        addMoves(new Coordinate(-1, 1));
        addMoves(new Coordinate(-2,2));
        addMoves(new Coordinate(-3,3));
        addMoves(new Coordinate(-4,4));
        addMoves(new Coordinate(-5,5));
        addMoves(new Coordinate(-6,6));
        addMoves(new Coordinate(-7,7));
        addMoves(new Coordinate(1, -1));
        addMoves(new Coordinate(2,-2));
        addMoves(new Coordinate(3,-3));
        addMoves(new Coordinate(4,-4));
        addMoves(new Coordinate(5,-5));
        addMoves(new Coordinate(6,-6));
        addMoves(new Coordinate(7,-7));
    }

}
