package Model;

import java.util.ArrayList;

public class Board {

    private Square[][] squares;
    private RuleHandler ruleHandler;
    private Piece lastMovedPiece;

    public Board() {
        this.squares = new Square[8][8];
        this.ruleHandler = new RuleHandler(this);

        setupSquares();
        setupPieces();
    }

    private void setupSquares() {
        for (int y = squares.length - 1; y >= 0; y--) {
            for (int x = 0; x < squares[y].length; x++) {
                squares[x][y] = new Square();
            }
        }
    }

    private void setupPieces() {
        for (int x = 0; x < squares.length; x++) {
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

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSpecificSquare(int x, int y) {
        return squares[x][y];
    }

    public Square getSpecificSquare(Coordinate coordinate) {
        return squares[coordinate.getX()][coordinate.getY()];
    }

    public ArrayList<Coordinate> getValidMoves(Coordinate coordinate, ArrayList<Coordinate> opponentsMoves) {
        Piece selectedPiece = getSpecificSquare(coordinate).getPiece();
        ArrayList<Coordinate> validMoves;

        if (selectedPiece instanceof SpecialPiece) {
            validMoves = ruleHandler.specialPieceValidMoves(coordinate);
        } else if (selectedPiece instanceof Pawn) {
            validMoves = ruleHandler.pawnValidMoves(coordinate);
        } else if (selectedPiece instanceof King) {
            validMoves = ruleHandler.kingValidMoves(coordinate, opponentsMoves);
        } else {
            validMoves = ruleHandler.knightValidMoves(coordinate);
        }
        return validMoves;
    }

    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    public void setLastMovedPiece(Piece lastMovedPiece) {
        this.lastMovedPiece = lastMovedPiece;
    }

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

    public Coordinate getKingPosition(String color) {
        Coordinate kingsCoordinate = new Coordinate(0, 0);

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
