package Model;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Piece {

    private ArrayList<Coordinate> possibleMoves;
    private String color;
    private String name;
    private int moves;
    //private ImageIcon icon;
    private String selectSound;


    public Piece(String color, String name, String filePath) {
        this.selectSound = filePath;
        this.color = color;
        this.name = name;
        this.possibleMoves = new ArrayList<>();
        this.moves = 0;
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

    /*
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
     */

    public void setSelectSound(String selectSound) {
        this.selectSound = selectSound;
    }

    public String getSelectSound() {
        return selectSound;
    }
}


