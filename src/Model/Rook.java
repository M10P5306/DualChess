package Model;

import java.util.ArrayList;

public class Rook extends Piece{

    int movesMade = 0;

    public Rook(String color) {
        super(color, "Rook");
        addMoves(new Coordinate(0,1));
        addMoves(new Coordinate(0,2));
        addMoves(new Coordinate(0,3));
        addMoves(new Coordinate(0,4));
        addMoves(new Coordinate(0,5));
        addMoves(new Coordinate(0,6));
        addMoves(new Coordinate(0,7));
        addMoves(new Coordinate(0,-1));
        addMoves(new Coordinate(0,-2));
        addMoves(new Coordinate(0,-3));
        addMoves(new Coordinate(0,-4));
        addMoves(new Coordinate(0,-5));
        addMoves(new Coordinate(0,-6));
        addMoves(new Coordinate(0,-7));
        addMoves(new Coordinate(1,0));
        addMoves(new Coordinate(2,0));
        addMoves(new Coordinate(3,0));
        addMoves(new Coordinate(4,0));
        addMoves(new Coordinate(5,0));
        addMoves(new Coordinate(6,0));
        addMoves(new Coordinate(7,0));
        addMoves(new Coordinate(-1,0));
        addMoves(new Coordinate(-2,0));
        addMoves(new Coordinate(-3,0));
        addMoves(new Coordinate(-4,0));
        addMoves(new Coordinate(-5,0));
        addMoves(new Coordinate(-6,0));
        addMoves(new Coordinate(-7,0));
    }

    public int getMovesMade() {
        return movesMade;
    }

}
