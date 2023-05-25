package ExtremeMode.Model;

import Model.Coordinate;
import Model.Piece;

public class SquareExtreme {
    private PieceExtreme piece;
    private Box box;

    public void setPiece(PieceExtreme piece) {
        this.piece = piece;
    }
    public void setBox(Box box) {this.box = box;}

    public PieceExtreme getPiece() {
        return piece;
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
        return false;
    }
}
