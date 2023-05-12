package Model;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece {

    private ArrayList<Coordinate> possibleMoves;
    private String color;
    private String name;
    private int moves;
    private ImageIcon icon;
    private String soundFilePath;

    public Piece(String color, String name) {
        this.color = color;
        this.name = name;
        this.possibleMoves = new ArrayList<>();
        this.moves = 0;
        this.soundFilePath = "src/Sounds/handgun.wav";
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Coordinate> getPossibleMoves() {
        return possibleMoves;
    }

    public String colorAndNameToString() {
        return color + name;
    }

    public void addMoves(Coordinate coordinate) {
        possibleMoves.add(coordinate);
    }

    public int getMoves() {
        return moves;
    }

    public void addMoves() {
        moves++;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public void setSoundFilePath(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }

}


