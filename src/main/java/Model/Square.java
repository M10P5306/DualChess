package Model;

/**
 * Class representing a tile on the board corresponding to a button on the GUIs board.
 *  @Author Mikael Nilsson, Hugo Andersson, Eding Jahic and Adel Mohammed Abo Khamis.
 */
public class Square {
    /**
     * the potential piece on the Square.
     */
    private Piece piece;

    /**
     * Set's a piece on this Square.
     * @param piece of any color or subclass.
     */
    public void setPiece(Piece piece) {
        this.piece =piece;
    }

    /**
     *
     * @return the piece currently placed on this Square.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     *
     * @return true if piece is not null.
     */
    public boolean hasPiece() {
        if (piece != null) {
            return true;
        }
        return false;
    }

}
