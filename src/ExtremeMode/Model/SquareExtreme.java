package ExtremeMode.Model;

import Model.Coordinate;
import Model.Piece;

public class SquareExtreme {
    private PieceExtreme piece;
    private Box box;
    private Bomb bomb;

    public void setPiece(PieceExtreme piece) {
        this.piece = piece;
    }
    public void setBox(Box box) {
        this.box = box;
    }

    public PieceExtreme getPiece() {
        return piece;
    }

    public Box getBox() {
        return box;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public boolean hasPiece() {
        if (piece != null) {
            return true;
        }
        return false;
    }

   /* public Coordinate getPosition() {
   }*/


    //TODO implement box.
    public boolean hasBox() {
        if (box != null) {
            return true;
        }
        return false;
    }

    public boolean hasBomb() {
        if (bomb != null) {
            return true;
        }
        return false;
    }
}
