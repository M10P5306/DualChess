package Model;

import java.util.ArrayList;

/**
 * Representation of the GUI's board containing the same amount of squares as the GUIs buttons, a RuleHandler checking the validity of moves and also keeps track
 * of the last moves piece for the specialMove en-passant.
 * @Author Mikael Nilsson.
 */
public class Board {
    /**
     * Array of Squares containing Pieces.
     */
    private Square[][] squares;
    /**
     * Class for checking the validity of moves.
     */
    private RuleHandler ruleHandler;
    /**
     * Used by the RuleHandler when verifying the move en-passant.
     */
    private Piece lastMovedPiece;

    /**
     * Constructor that instantiates the array of Squares and the RuleHandler before calling methods for setting up the board.
     */
    public Board() {
        this.squares = new Square[8][8];
        this.ruleHandler = new RuleHandler(this);

        setupSquares();
        setupPieces();
    }

    /**
     * Instantiates each square on the Square array starting from the lower left corner of the board going right before moving up on row at the time.
     */
    private void setupSquares() {
        for (int y = squares.length-1; y>=0; y--) {
            for (int x = 0; x<squares[y].length; x++) {
                squares[x][y] = new Square();
            }
        }
    }

    /**
     * Places all pieces in the starting position of chess.
     */
    private void setupPieces() {
        for (int x = 0; x<squares.length; x++) {
            squares[x][1].setPiece(new WhitePawn());
            squares[x][6].setPiece(new BlackPawn());
        }

        squares[0][0].setPiece(new Rook("White"));
        squares[1][0].setPiece(new Knight("White"));
        squares[2][0].setPiece(new Bishop("White"));
        squares[3][0].setPiece(new Queen("White"));
        squares[4][0].setPiece(new King("White"));
        squares[5][0].setPiece(new Bishop("White"));
        squares[6][0].setPiece(new Knight("White"));
        squares[7][0].setPiece(new Rook("White"));

        squares[0][7].setPiece(new Rook("Black"));
        squares[1][7].setPiece(new Knight("Black"));
        squares[2][7].setPiece(new Bishop("Black"));
        squares[3][7].setPiece(new Queen("Black"));
        squares[4][7].setPiece(new King("Black"));
        squares[5][7].setPiece(new Bishop("Black"));
        squares[6][7].setPiece(new Knight("Black"));
        squares[7][7].setPiece(new Rook("Black"));
    }

    /**
     *
     * @return the array of Squares containing the game's pieces.
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     *
     * @param x position
     * @param y position
     * @return Specific Square corresponding to the x and y coordinate.
     */
    public Square getSpecificSquare(int x, int y) {
        return squares[x][y];
    }

    /**
     *
     * @param coordinate combined x and y position.
     * @return specific Square corresponding to the x and y coordinate.
     */
    public Square getSpecificSquare(Coordinate coordinate) {
        return squares[coordinate.getX()][coordinate.getY()];
    }

    /**
     *
     * @param coordinate of the specific square containing the selected piece responding to the GUIs board.
     * @param opponentsMoves array of moves that the opponent can make with all of his/her pieces.
     * @return array of all possible moves for the piece to make.
     */
    public ArrayList<Coordinate> getValidMoves(Coordinate coordinate, ArrayList<Coordinate> opponentsMoves) {
        Piece selectedPiece = getSpecificSquare(coordinate).getPiece();
        ArrayList<Coordinate> validMoves;

        if (selectedPiece instanceof SpecialPiece) {
            validMoves = ruleHandler.specialPieceValidMoves(coordinate);
        }
        else if (selectedPiece instanceof Pawn) {
            validMoves = ruleHandler.pawnValidMoves(coordinate);
        }
        else if (selectedPiece instanceof King) {
            validMoves = ruleHandler.kingValidMoves(coordinate, opponentsMoves);
        }
        else {
            validMoves = ruleHandler.knightValidMoves(coordinate);
        }
        return validMoves;
    }

    /**
     *
     * @return the piece that made the last move.
     */
    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    /**
     *
     * @param lastMovedPiece replaces the variable when a piece is moved.
     */
    public void setLastMovedPiece(Piece lastMovedPiece) {
        this.lastMovedPiece = lastMovedPiece;
    }

    /**
     *
     * @param color used to return a specific players pieces.
     * @return the position of all pieces belonging to a player.
     */
    public ArrayList<Coordinate> getPiecesPositions(String color) {
        ArrayList<Coordinate> piecePositions = new ArrayList<>();

        for (int x = 0; x < squares.length; x++) {
            for (int y = 0; y < squares[x].length; y++) {
                if (squares[x][y].hasPiece() && squares[x][y].getPiece().getColor().equals(color)) {
                    piecePositions.add(new Coordinate(x, y));
                }
            }
        }
        return piecePositions;
    }

    /**
     *
     * @param color used to return a specific players King.
     * @return the position of the requested King.
     */
    public Coordinate getKingPosition(String color) {
        Coordinate kingsCoordinate = new Coordinate(0,0);

        for (int x = 0; x < squares.length; x++) {
            for (int y = 0; y < squares[x].length; y++) {
                if (squares[x][y].hasPiece() && (squares[x][y].getPiece() instanceof King && squares[x][y].getPiece().getColor().equals(color))) {
                    kingsCoordinate = new Coordinate(x, y);
                    break;
                }
            }
        }

        return kingsCoordinate;
    }

}
