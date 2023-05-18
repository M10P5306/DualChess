package Model;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class representing an unspecified chess piece.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */

public abstract class Piece {
    /**
     * Holds every possible move a piece can make regardless of placement on the board.
     */
    private ArrayList<Coordinate> possibleMoves;
    /**
     * Determines who the piece belongs to.
     */
    private String color;
    /**
     * When the piece is defined it gets its name depending on it's subclass.
     */
    private String name;
    /**
     * Keeps track of how many moves the piece has made.
     */
    private int moves;
    /**
     * Image that's displayed on the GUIs board.
     */
    private ImageIcon icon;
    /**
     * When the piece is defined it gets its sound depending on its subclass.
     */
    private String soundFilePath;

    /**
     * Constructor for a piece with the instance variables found above.
     * @param color White or black.
     * @param name Related to it's subclass.
     */
    public Piece(String color, String name) {
        this.color = color;
        this.name = name;
        this.possibleMoves = new ArrayList<>();
        this.moves = 0;
        this.soundFilePath= "src/Sounds/handgun.wav";
    }

    /**
     *
     * @return White or black.
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return Every possible move a piece can make regardless of placement on the board.
     */
    public ArrayList<Coordinate> getPossibleMoves() {
      return possibleMoves;
    }

    /**
     * Combines getColor and getName.
     * @return the Piece's color and its name.
     */
    public String colorAndNameToString() {
        return color + name;
    }

    /**
     * Adds possible moves to the array depending on its subclass.
     * @param coordinate holds a x and a y coordinate
     */
    public void addMoves(Coordinate coordinate) {
        possibleMoves.add(coordinate);
    }

    /**
     *
     * @return the Total amount of moves made by the piece.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Increments the amount of moves made by the piece.
     */
    public void addMoves() {
        moves++;
    }

    /**
     * Adds an image to the piece which will be shown on the GUIs board.
     * @param icon Image representing a piece with it's color and subclass.
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     *
     * @return Image representing the piece's color and subclass.
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     *
     * @return Filepath for the pieces sound when being marked in the GUI.
     */
    public String getSoundFilePath() {
        return soundFilePath;
    }

    /**
     *
     * @param soundFilePath path to the sound corresponding with the pieces subclass.
     */
    public void setSoundFilePath(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }


}


