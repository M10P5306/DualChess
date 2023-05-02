package Model;

import java.util.ArrayList;

public class RuleHandler {

    private Board board;

    public RuleHandler(Board board) {
        this.board = board;
    }

    public ArrayList<Coordinate> specialPieceValidMoves(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        System.out.println(board.getSpecificSquare(coordinate).getPiece().colorAndNameToString());

        for (int i = 0; i<possibleMoves.size(); i++) {
            if (withInRange(coordinate, possibleMoves.get(i))) {
                if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                    if(!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), board.getSpecificSquare(coordinate))) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }

                    if (i % 7 != 0) {
                        i = i + (6 - (i % 7));
                    }
                    else {
                        i = i+6;
                    }
                }
                else {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                }
            }
        }
        return validMoves;
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
            if (board.getSpecificSquare(coordinate).getPiece().getMoves()>1 &&
                    board.getSpecificSquare(new Coordinate(coordinate.getX()-1, coordinate.getY())).hasPiece() &&
                    !board.getSpecificSquare(combineCoordinates(possibleMoves.get(2), coordinate)).hasPiece() &&
                    (coordinate.getY() == 3 || coordinate.getY() == 4)) {
                if ((board.getSpecificSquare(new Coordinate(coordinate.getX()-1, coordinate.getY())).getPiece() instanceof BlackPawn ||
                        board.getSpecificSquare(new Coordinate(coordinate.getX()-1, coordinate.getY())).getPiece() instanceof WhitePawn) &&
                                board.getSpecificSquare(new Coordinate(coordinate.getX()-1, coordinate.getY())).getPiece() == board.getLastMovedPiece() &&
                                board.getSpecificSquare(new Coordinate(coordinate.getX()-1, coordinate.getY())).getPiece().getMoves() == 1) {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(2)));
                }
            }
        }
        if (withInRange(coordinate, possibleMoves.get(3))) {
            if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)).hasPiece()) {
                if (!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)), board.getSpecificSquare(coordinate)))
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(3)));
            }
            if (board.getSpecificSquare(coordinate).getPiece().getMoves()>1 &&
                    board.getSpecificSquare(new Coordinate(coordinate.getX()+1, coordinate.getY())).hasPiece() &&
                    !board.getSpecificSquare(combineCoordinates(possibleMoves.get(3), coordinate)).hasPiece() &&
                    (coordinate.getY() == 3 || coordinate.getY() == 4)) {
                if ((board.getSpecificSquare(new Coordinate(coordinate.getX()+1, coordinate.getY())).getPiece() instanceof BlackPawn ||
                        board.getSpecificSquare(new Coordinate(coordinate.getX()+1, coordinate.getY())).getPiece() instanceof WhitePawn) &&
                                board.getSpecificSquare(new Coordinate(coordinate.getX()+1, coordinate.getY())).getPiece() == board.getLastMovedPiece() &&
                                board.getSpecificSquare(new Coordinate(coordinate.getX()+1, coordinate.getY())).getPiece().getMoves() == 1) {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(3)));
                }
            }
        }
        return validMoves;
    }

    public ArrayList<Coordinate> knightValidMoves(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        for (int i = 0; i<possibleMoves.size(); i++) {
            if (withInRange(coordinate, possibleMoves.get(i))) {
                if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                    if(!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), board.getSpecificSquare(coordinate)))
                    {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                }
                else {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                }
            }
        }
        return validMoves;
    }


    public ArrayList<Coordinate> kingValidMoves(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMoves = board.getSpecificSquare(coordinate).getPiece().getPossibleMoves();
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        Piece selectedKing = board.getSpecificSquare(coordinate).getPiece();

        for (int i = 0; i<possibleMoves.size()-2; i++) {
            if (withInRange(coordinate, possibleMoves.get(i))) {
                if (board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)).hasPiece()) {
                    if(!sameColor(board.getSpecificSquare(combineCoordinates(possibleMoves.get(i), coordinate)), board.getSpecificSquare(coordinate)))
                    {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                    }
                }
                else {
                    validMoves.add(combineCoordinates(coordinate, possibleMoves.get(i)));
                }
            }
        }

        if(selectedKing.getMoves() == 0) {
            for(int i = 1; i<4; i++) {

                if (i==3 && board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).hasPiece()) {
                    if (board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).getPiece() instanceof Rook &&
                          board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).getPiece().getMoves() == 0) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(possibleMoves.size()-2)));
                        break;
                    }
                }
                if (board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).hasPiece()) {
                    break;
                }
            }
            for(int i = -1; i>-5; i--) {
                if (i==-4 && board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).hasPiece()) {
                    if (board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).getPiece() instanceof Rook &&
                            board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).getPiece().getMoves() == 0) {
                        validMoves.add(combineCoordinates(coordinate, possibleMoves.get(possibleMoves.size()-1)));
                        break;
                    }
                }
                if (board.getSpecificSquare(combineCoordinates(coordinate, new Coordinate(i, 0))).hasPiece()) {
                    break;
                }
            }
        }
        return validMoves;
    }

    public boolean withInRange(Coordinate currentPosition, Coordinate nextMove) {
        int x = currentPosition.getX()+nextMove.getX();
        int y = currentPosition.getY()+nextMove.getY();

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
            return true;
        }
        return false;
    }

}
