package Model;

import java.util.ArrayList;

public abstract class Piece {

    //TODO Add and int variable for checking the value of the piece for "highscore"

    private ArrayList<Coordinate> possibleMoves;

    private String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract ArrayList validMoves(Coordinate currentPosition);

}


