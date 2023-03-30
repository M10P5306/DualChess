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
    }

    public String getColor() {
        return color;
    }

    public ArrayList validMoves(Coordinate currentPosition) {
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        for (Coordinate coordinate : possibleMoves) {
            int x = coordinate.getX()+currentPosition.getX();
            int y = coordinate.getX()+currentPosition.getY();
            if (x>=0 && x<8) {
                if (y>=0 && y<8) {
                    validMoves.add(coordinate);
                }
            }
        }
        return validMoves;
    }

    public String colorAndNameToString() {
        return color + name;
    }

}


