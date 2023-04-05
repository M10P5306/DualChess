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
        ArrayList<Coordinate> possibleMoves = getSpecificSquare(coordinate).getPiece().getPossibleMoves();

        ArrayList<Coordinate> validMoves = new ArrayList<>();

        if (isSpecialPiece(getSpecificSquare(coordinate).getPiece())) {
            System.out.println("SpecialPiece selected");

            for (int i = 0; i<possibleMoves.size(); i++) {
                if (withInRange(coordinate, possibleMoves.get(i))) {

                    if (getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                        System.out.println("Fanns pjäs");
                        System.out.println(i);
                        if(!sameColor(getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), getSpecificSquare(coordinate)))
                        {
                            validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));

                            if (i % 7 != 0) {
                                i = i + (7 - (i % 7));
                                System.out.println("hopp");
                            }
                        }
                        else {
                            System.out.println("borde skippa");
                            System.out.println("i är " + i + " och i % 7 är " +i % 7);
                            if (i % 7 != 0) {
                                System.out.println("hopp");
                                i = i + (7 - (i % 7));
                            }
                            else {
                                System.out.println("hopp hopp");
                                i = i+6;
                            }
                        }
                    }
                    else {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                }
            }
        }

        else {
            System.out.println("Not SpecialPiece selected");
            for (int i = 0; i<possibleMoves.size(); i++) {
                if (withInRange(coordinate, possibleMoves.get(i))) {

                    if (getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                        System.out.println("Fanns pjäs");

                        if(!sameColor(getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), getSpecificSquare(coordinate)))
                        {
                            validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                        }
                    }
                    else {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                }
            }
        }

        return validMoves;
    }

    public boolean withInRange(Coordinate currentPosition, Coordinate nextMove) {

        int x = currentPosition.getX()+nextMove.getX();
        int y = currentPosition.getY()+nextMove.getY();

        System.out.println(x + " " + y);

        if (y < 8 && y >= 0 && x < 8 && x >= 0) {
        return true;
        }
        return false;
    }

    public Coordinate combineCoordinates(Coordinate coordinateOne, Coordinate coordinateTwo) {
        int x = coordinateOne.getX() + coordinateTwo.getX();
        int y = coordinateOne.getY() + coordinateTwo.getY();

        return new Coordinate(x, y);
    }

    public boolean sameColor(Square squareOne, Square squareTwo) {

        if (squareOne.getPiece().getColor().equals(squareTwo.getPiece().getColor())) {
            System.out.println("samma färg");
            return true;
        }
        System.out.println("inte samma färg");
        return false;
    }

    public boolean isSpecialPiece(Piece piece) {

        if (piece instanceof Queen ||
                piece instanceof Bishop ||
                piece instanceof Rook) {
            return true;
        }
        return false;
    }

}
