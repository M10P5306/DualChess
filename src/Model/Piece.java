package Model;

import java.util.ArrayList;

public abstract class Piece {

    private ArrayList<Coordinate> possibleMoves;
    private String color;
    private String name;
    private int moves;

    public Piece(String color, String name) {
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

}


