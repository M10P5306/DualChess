package com.example.chesspiece3d;

import Model.*;
import java.util.ArrayList;
import java.util.HashSet;
import static java.lang.Math.abs;

public class ControllerFor3D {
    private Board board;
    private Coordinate selectedPiecePosition;
    private ArrayList<Coordinate> selectedPieceValidMoves;
    private int turnCounter;
    private ArrayList<Coordinate> opponentsMoves;
    private HelloController helloController;


    public ControllerFor3D(HelloController helloController, Board board) {
        this.helloController = helloController;
        this.board = board;
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
    }

    public boolean movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);
        Piece pieceToMove = board.getSpecificSquare(selectedPiecePosition).getPiece();


        if (isCheck(newPosition, selectedPiecePosition)) {
            return false;
        }

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiecePosition).getPiece().colorAndNameToString() + " moved from " +
                        intToLetter(selectedPiecePosition.getX()) + "," + (selectedPiecePosition.getY() + 1) + " to " + intToLetter(newPositionX) + "," + (newPositionY + 1);
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPosition).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                }

                board.getSpecificSquare(selectedPiecePosition).setPiece(null);

                if ((pieceToMove instanceof Pawn) &&
                        !(selectedPiecePosition.getX() == newPositionX) &&
                        !board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + enPassant(pieceToMove, newPosition);
                    toPrint.append(takenPiece);
                }

                board.getSpecificSquare(newPosition).setPiece(pieceToMove);

                if (pieceToMove instanceof King && abs(selectedPiecePosition.getX() - newPositionX) == 2) {
                    rockad(newPositionX - selectedPiecePosition.getX());
                }

                pieceToMove.addMoves();
                String message = toPrint.toString();
                turnCounter++;
                helloController.setPlayersTurn(turnCounter);
                board.setLastMovedPiece(pieceToMove);

                if (board.getSpecificSquare(newPosition).getPiece() instanceof BlackPawn ||
                        board.getSpecificSquare(newPosition).getPiece() instanceof WhitePawn) {
                    if (newPositionY == 0 || newPositionY == 7) {
                        String color = board.getSpecificSquare(newPositionX, newPositionY).getPiece().getColor();
                        board.getSpecificSquare(newPosition).setPiece(new Queen(color));
                        helloController.promoted(newPosition, color);
                    }
                }

                opponentsMoves = updateOpponentsMoves(pieceToMove.getColor());

                if (checkForCheck(pieceToMove.getColor(), updateOpponentsMoves(pieceToMove.getColor()))) {
                    if (checkForMatt(pieceToMove)) {
                        win();
                        break;
                    }
                }
                if (checkForMatt(pieceToMove)) {
                    draw();
                }
            }
        } return true;
    }


    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x, y).getPiece() != null) {
            if (turnCounter % 2 != 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("White")) {
                updateBoardColors(x, y, "Black");
                return true;
            }

            if (turnCounter % 2 == 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("Black")) {
                updateBoardColors(x, y, "White");
                return true;
            }
        }
        return false;
    }

    public void updateBoardColors(int x, int y, String color) {
        this.selectedPiecePosition = new Coordinate(x, y);
        selectedPieceValidMoves = board.getValidMoves(selectedPiecePosition, opponentsMoves);

        for (Coordinate coordinate : selectedPieceValidMoves) {
            int possibleX = coordinate.getX();
            int possibleY = coordinate.getY();
            if (board.getSpecificSquare(possibleX, possibleY).getPiece() != null && board.getSpecificSquare(possibleX, possibleY).getPiece().getColor().equals(color)) {
                helloController.setPossibleAttack(possibleX, possibleY);
            } else if (specialMove(coordinate)) {
                helloController.setSpecialMove(possibleX, possibleY);
            } else {
                helloController.setValidMoves(possibleX, possibleY);
            }
        }
    }

    public boolean specialMove(Coordinate newPosition) {
        if (board.getSpecificSquare(selectedPiecePosition).getPiece() instanceof King && abs(selectedPiecePosition.getX() - newPosition.getX()) == 2) {
            return true;
        }
        if (board.getSpecificSquare(selectedPiecePosition).getPiece() instanceof Pawn) {
            if (newPosition.getY() == 0 || newPosition.getY() == 7) {
                return true;
            }
            if (abs(selectedPiecePosition.getX() - newPosition.getX()) == 1 &&
                    abs(selectedPiecePosition.getY() - newPosition.getY()) == 1 &&
                    !board.getSpecificSquare(newPosition).hasPiece()) {
                return true;
            }
        }
        return false;
    }

    public void draw() {
        theGameIsOver("DRAW");
    }


    public void win() {
        String winner;
        if (turnCounter % 2 != 1) {
            winner = "Black Player";
        } else {
            winner = "White Player";
        }
        theGameIsOver(winner);
    }

    public void theGameIsOver(String winner) {
        helloController.winOrDrawMessage(winner);
    }

    public String enPassant(Piece pieceToMove, Coordinate newPosition) {
        String message = "";

        if (pieceToMove.getColor().equals("White")) {
            message = board.getSpecificSquare(newPosition.getX(), newPosition.getY() - 1).getPiece().colorAndNameToString();
            board.getSpecificSquare(newPosition.getX(), newPosition.getY() - 1).setPiece(null);
            helloController.enPassant(newPosition.getX(), newPosition.getY() - 1);
        } else {
            message = board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).getPiece().colorAndNameToString();
            board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).setPiece(null);
            helloController.enPassant(newPosition.getX(), newPosition.getY() + 1);
        }
        return message;
    }

    public void rockad(int direction) {
        Coordinate rookPosition;
        Piece rookToMove;

        if (direction == 2) {
            rookPosition = new Coordinate(selectedPiecePosition.getX() + 3, selectedPiecePosition.getY());
            rookToMove = board.getSpecificSquare(rookPosition).getPiece();
            Coordinate newRookPosition = new Coordinate(selectedPiecePosition.getX() + 1, selectedPiecePosition.getY());
            board.getSpecificSquare(newRookPosition).setPiece(rookToMove);
            board.getSpecificSquare(rookPosition).setPiece(null);
            rookToMove.addMoves();
            helloController.rockad(newRookPosition, rookPosition);
        }
        if (direction == -2) {
            rookPosition = new Coordinate(selectedPiecePosition.getX() - 4, selectedPiecePosition.getY());
            rookToMove = board.getSpecificSquare(rookPosition).getPiece();
            Coordinate newRookPosition = new Coordinate(selectedPiecePosition.getX() - 1, selectedPiecePosition.getY());
            board.getSpecificSquare(newRookPosition).setPiece(rookToMove);
            board.getSpecificSquare(rookPosition).setPiece(null);
            rookToMove.addMoves();
            helloController.rockad(newRookPosition, rookPosition);
        }
    }

    public char intToLetter(int position) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        return chars[position];
    }

    public ArrayList<Coordinate> updateOpponentsMoves(String color) {

        ArrayList<Coordinate> piecePositions = board.getPiecesPositions(color);
        HashSet<Coordinate> temporaryPlayersEveryMove = new HashSet<>();
        ArrayList<Coordinate> playersEveryMove = new ArrayList<>();

        for (Coordinate currentCoordinate : piecePositions) {
            ArrayList<Coordinate> currentPieceValidMoves = board.getValidMoves(currentCoordinate, opponentsMoves);
            for (Coordinate pieceMove : currentPieceValidMoves) {
                temporaryPlayersEveryMove.add(pieceMove);
            }
        }

        for (Coordinate coordinate : temporaryPlayersEveryMove) {
            playersEveryMove.add(coordinate);
        }
        return playersEveryMove;
    }


    public boolean checkForCheck(String color, ArrayList<Coordinate> opponentsMoves) {
        Coordinate kingsPosition = null;

        if (color.equals("White")) {
            kingsPosition = board.getKingPosition("Black");
        } else {
            kingsPosition = board.getKingPosition("White");
        }

        for (Coordinate coordinate : opponentsMoves) {
            if (coordinate.equals(kingsPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheck(Coordinate newPosition, Coordinate currentPiece) {
        Piece takenPiece = null;
        Piece pieceToMove = board.getSpecificSquare(currentPiece).getPiece();
        String color;

        if (board.getSpecificSquare(newPosition).hasPiece()) {
            takenPiece = board.getSpecificSquare(newPosition).getPiece();
        }

        board.getSpecificSquare(newPosition).setPiece(pieceToMove);
        board.getSpecificSquare(currentPiece).setPiece(null);

        if (pieceToMove.getColor().equals("White")) {
            color = "Black";
        } else {
            color = "White";
        }

        boolean isGoodMove = checkForCheck(color, updateOpponentsMoves(color));
        board.getSpecificSquare(newPosition).setPiece(null);
        board.getSpecificSquare(currentPiece).setPiece(pieceToMove);

        if (takenPiece != null) {
            board.getSpecificSquare(newPosition).setPiece(takenPiece);
        }
        return isGoodMove;
    }

    public boolean checkForMatt(Piece piece) {

        String color = "White";
        if (piece.getColor().equals("White")) {
            color = "Black";
        }

        ArrayList<Coordinate> allPlayersPieces = board.getPiecesPositions(color);
        for (Coordinate currentPosition : allPlayersPieces) {
            ArrayList<Coordinate> possibleMoves = board.getValidMoves(currentPosition, opponentsMoves);
            for (Coordinate move : possibleMoves) {
                if (!isCheck(move, currentPosition)) {
                    return false;
                }
            }
        }
        return true;
    }
    public ArrayList<Coordinate> getPossibleMoves(){
        return selectedPieceValidMoves;
    }
}
