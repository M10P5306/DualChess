package Model;

import java.util.ArrayList;

public abstract class Piece {

    //TODO Add and int variable for checking the value of the piece for "highscore"

    private ArrayList<Coordinate> possibleMoves;

    private String color;

    private String name;

    public Piece(String color, String name) {
        this.color = color;
        this.name = name;
        this.possibleMoves = new ArrayList<>();
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

}


