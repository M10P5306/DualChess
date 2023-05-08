package ExtremeMode.Model;

import Model.Coordinate;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece {
    private ArrayList<Coordinate> possibleMoves;
    private ArrayList<Coordinate> possibleShots;
    private String color;
    private String name;
    private int moves;
    private int shots;
    private ImageIcon icon;
    private String soundFilePath;
    private int points;

    public Piece(String color, String name, int points) {
        this.color = color;
        this.name = name;
        this.possibleMoves = new ArrayList<>();
        this.possibleShots = new ArrayList<>();
        this.moves = 0;
        this.points = points;
        //this.soundFilePath= "src/Sounds/handgun.wav";
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Coordinate> getPossibleMoves() {
        return possibleMoves;
    }

    public ArrayList<Coordinate> getPossibleShots() {
        return possibleShots;
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

    public int getPoints() {
        return points;
    }

    public void addShots(Coordinate coordinate) {
        possibleShots.add(coordinate);
    }

    public int getShots() {
        return shots;
    }


}
