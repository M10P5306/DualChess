package Model;

import Controller.Controller;

import java.util.ArrayList;

public class Board {

    private Controller controller;

    private Square[][] squares;

    public Board(Controller controller) {
        this.controller = controller;
        this.squares = new Square[8][8];

        setupSquares();
        setupPieces();

    }

    private void setupSquares() {
        for (int x = squares.length-1; x>=0; x--) {
            for (int y = 0; y<squares[x].length; y++) {
                squares[x][y] = new Square();
            }
        }
    }

    private void setupPieces() {

        for (int y = 0; y<squares.length; y++) {
            squares[1][y].setPiece(new WhitePawn());
            squares[6][y].setPiece(new BlackPawn());
        }

        squares[0][0].setPiece(new Rook("White"));
        squares[0][1].setPiece(new Knight("White"));
        squares[0][2].setPiece(new Bishop("White"));
        squares[0][3].setPiece(new Queen("White"));
        squares[0][4].setPiece(new King("White"));
        squares[0][5].setPiece(new Bishop("White"));
        squares[0][6].setPiece(new Knight("White"));
        squares[0][7].setPiece(new Rook("White"));

        squares[7][0].setPiece(new Rook("Black"));
        squares[7][1].setPiece(new Knight("Black"));
        squares[7][2].setPiece(new Bishop("Black"));
        squares[7][3].setPiece(new Queen("Black"));
        squares[7][4].setPiece(new King("Black"));
        squares[7][5].setPiece(new Bishop("Black"));
        squares[7][6].setPiece(new Knight("Black"));
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

}
