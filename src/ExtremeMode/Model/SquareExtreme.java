package ExtremeMode.Model;

import Model.Piece;

public class SquareExtreme {
    private PieceExtreme piece;

    public void setPiece(PieceExtreme piece) {
        this.piece = piece;
    }

    public PieceExtreme getPiece() {
        return piece;
    }

    public boolean hasPiece() {
        if (piece != null) {
            return true;
        }
        return false;
    }

    //TODO implement box.
    public boolean hasBox() {
        return false;
    }
}
