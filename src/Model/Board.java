package Model;

import Controller.Controller;
import java.util.ArrayList;

public class Board {

    private Square[][] squares;
    private RuleHandler ruleHandler;

    public Board() {
        this.squares = new Square[8][8];
        this.ruleHandler = new RuleHandler(this);

        setupSquares();
        setupPieces();
    }

    private void setupSquares() {
        for (int y = squares.length-1; y>=0; y--) {
            for (int x = 0; x<squares[y].length; x++) {
                squares[x][y] = new Square();
            }
        }
    }

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

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSpecificSquare(int x, int y) {
        return squares[x][y];
    }

    public Square getSpecificSquare(Coordinate coordinate) {
        return squares[coordinate.getX()][coordinate.getY()];
    }

    public ArrayList<Coordinate> getValidMoves(Coordinate coordinate) {
        Piece selectedPiece = getSpecificSquare(coordinate).getPiece();
        ArrayList<Coordinate> possibleMoves = getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves;

        if (selectedPiece instanceof Queen || selectedPiece instanceof Bishop || selectedPiece instanceof Rook) {
            validMoves = ruleHandler.specialPieceValidMoves(coordinate);
            return validMoves;
        }
        else if (selectedPiece instanceof BlackPawn || selectedPiece instanceof WhitePawn) {
            validMoves = ruleHandler.pawnValidMoves(coordinate);
            return validMoves;
        }
        else {
            validMoves = ruleHandler.kingOrKnightValidMoves(coordinate);
        }
        return validMoves;
    }

}
