package ExtremeMode.Model;

import Model.*;

import java.util.ArrayList;

public class ExtremeRuleHandler {

    private ExtremeBoard board;

    public ExtremeRuleHandler (ExtremeBoard extremeBoard){
        this.board= extremeBoard;
    }
    public ArrayList<Coordinate> pawnValidMoves(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        if (withInRange(coordinate, possibleMoves.get(0))) {
            if (!board.getSpecificSquare(combineCoordinates(possibleMoves.get(0), coordinate)).hasPiece()) {
                validMoves.add(combineCoordinates(coordinate, possibleMoves.get(0)));
                if (withInRange(coordinate, possibleMoves.get(1))) {
                    if (!board.getSpecificSquare(combineCoordinates(possibleMoves.get(1), coordinate)).hasPiece() && board.getSpecificSquare(coordinate).getPiece().getMoves() == 0) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(1)));
                    }
                }
            }
        }
        if (withInRange(coordinate, possibleMoves.get(2))) {
            if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(2), coordinate)).hasPiece()) {
                if (!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(2), coordinate)), board.getSpecificSquare(coordinate)))
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(2)));
            }
            if (board.getSpecificSquare(coordinate).getPiece().getMoves() > 1 &&
                    board.getSpecificSquare(new Coordinate(coordinate.getX() - 1, coordinate.getY())).hasPiece() &&
                    !board.getSpecificSquare(combineCoordinates(possibleMoves.get(2), coordinate)).hasPiece() &&
                    (coordinate.getY() == 3 || coordinate.getY() == 4)) {
                if ((board.getSpecificSquare(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getPiece() instanceof BlackPawnExtreme ||
                        board.getSpecificSquare(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getPiece() instanceof WhitePawnExtreme) &&
                        board.getSpecificSquare(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getPiece() == board.getLastMovedPiece() &&
                        board.getSpecificSquare(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getPiece().getMoves() == 1) {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(2)));
                }
            }
        }
        if (withInRange(coordinate, possibleMoves.get(3))) {
            if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)).hasPiece()) {
                if (!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)), board.getSpecificSquare(coordinate)))
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(3)));
            }
            if (board.getSpecificSquare(coordinate).getPiece().getMoves() > 1 &&
                    board.getSpecificSquare(new Coordinate(coordinate.getX() + 1, coordinate.getY())).hasPiece() &&
                    !board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)).hasPiece() &&
                    (coordinate.getY() == 3 || coordinate.getY() == 4)) {
                if ((board.getSpecificSquare(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getPiece() instanceof BlackPawnExtreme ||
                        board.getSpecificSquare(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getPiece() instanceof WhitePawnExtreme) &&
                        board.getSpecificSquare(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getPiece() == board.getLastMovedPiece() &&
                        board.getSpecificSquare(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getPiece().getMoves() == 1) {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(3)));
                }
            }
        }
        return validMoves;
    }
    public ArrayList<Coordinate> kingValidMoves(Coordinate coordinate, ArrayList<Coordinate> opponentsMoves) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        PieceExtreme selectedKing = board.getSpecificSquare(coordinate).getPiece();

        for (int i = 0; i < possibleMoves.size() - 2; i++) {
            if (withInRange(coordinate, possibleMoves.get(i))) {
                if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                    if (!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), board.getSpecificSquare(coordinate))) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                } else {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                }
            }
        }
        return validMoves;
    }
    public ArrayList<Coordinate> knightValidMoves(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        for (int i = 0; i < possibleMoves.size(); i++) {
            if (withInRange(coordinate, possibleMoves.get(i))) {
                if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                    if (!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), board.getSpecificSquare(coordinate))) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                } else {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                }
            }
        }
        return validMoves;
    }



    public boolean withInRange(Coordinate currentPosition, Coordinate nextMove) {
        int x = currentPosition.getX() + nextMove.getX();
        int y = currentPosition.getY() + nextMove.getY();

        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            return true;
        }
        return false;
    }
    public boolean sameColor(SquareExtreme squareOne, SquareExtreme squareTwo) {
        if (squareOne.getPiece().getColor().equals(squareTwo.getPiece().getColor())) {
            return true;
        }
        return false;
    }
    public Coordinate combineCoordinates(Coordinate coordinateOne, Coordinate coordinateTwo) {
        int x = coordinateOne.getX() + coordinateTwo.getX();
        int y = coordinateOne.getY() + coordinateTwo.getY();
        return new Coordinate(x, y);
    }
}
