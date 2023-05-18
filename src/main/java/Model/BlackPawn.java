package Model;

import javax.swing.*;

/**
 * Representation of the Pawn piece with the color black.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */

public class BlackPawn extends Piece implements Pawn{

    /**
     * Constructor setting name, color, image and sound to the super-constructor and the adds all moves associated with a black Pawn.
     * @param color determines ownership of the piece.
     */
    public BlackPawn() {
        super("Black", "Pawn");
        super.setIcon(new ImageIcon("src/main/java/Icons/BlackPawn.png"));
        super.setSoundFilePath("src/main/java/Sounds/Pawn.wav");
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(-1,-1));
        addMoves(new Coordinate(1,-1));
    }

}