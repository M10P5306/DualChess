package Model;

import javax.swing.*;

/**
 * Representation of the Pawn piece with the color white.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */

public class WhitePawn extends Piece implements Pawn{

    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a white Pawn.
     * @param color determines ownership of the piece.
     */
    public WhitePawn() {
        super("White", "Pawn");
        super.setIcon(new ImageIcon("src/main/java/Icons/WhitePawn.png"));
        super.setSoundFilePath("src/main/java/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(-1,1));
        addMoves(new Coordinate(1,1));
    }

}
