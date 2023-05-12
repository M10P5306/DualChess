package Model;

import ExtremeMode.Model.PieceExtreme;

public class Square {

    private Piece piece;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasPiece() {
        if (piece != null) {
            return true;
        }
        return false;
    }

}
