package com.example.chesspiece3d;

import Model.*;
import java.util.ArrayList;
import java.util.HashSet;
import static java.lang.Math.abs;

/**
 * This class is another version of controller, which has been adjusted for the 3D GUI of this application.
 * @author Hugo Andersson and Mikael Nilsson.
 */
public class ControllerFor3D {

    /**
     * Board containing the logic and information of the board-state of the game.
     */
    private Board board;

    /**
     * Position of the currently selected button in the GUI.
     */
    private Coordinate selectedPiecePosition;

    /**
     * the moves available to the selected Piece that will be displayed in the GUI when marking a button with an image of a Piece on it.
     */
    private ArrayList<Coordinate> selectedPieceValidMoves;

    /**
     * Used to keeping track of which player's turn it is.
     */
    private int turnCounter;

    /**
     * Array of moves available to the opponent which is used for checking if a move would result in check or checkmate.
     */
    private ArrayList<Coordinate> opponentsMoves;

    /**
     * The instance of the controller connected to the GUI.
     */
    private HelloController helloController;


    /**
     * The constructor which sets up the variables.
     * @param helloController The instance of the controller connected to the GUI.
     * @param board Board containing the logic and information of the board-state of the game.
     */
    public ControllerFor3D(HelloController helloController, Board board) {
        this.helloController = helloController;
        this.board = board;
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
    }

    /**
     * This method checks if a requested move will result in check, if not it will move the Piece from its current Square to another and possibly replacing another
     * Piece already present there. The boolean returned lets helloController know if the movement was allowed. Lastly checks if the move resulted in a game-ending board-state.
     * @param newPositionX the x position of the requested move.
     * @param newPositionY the y position of the requested move.
     */
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


    /**
     * Method checking if the button clicked in the GUI is valid depending on what players turn it is and if the buttons corresponding Square has a Piece.
     * @param x position of the button.
     * @param y position of the button.
     * @return true if the clicked button is valid in the above-mentioned conditions.
     */
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

    /**
     * Method used when clicking on a valid button in the GUI which will change the color of the buttons in the GUI depending on information given by
     * the RuleHandler in Board.
     * @param x position of the button.
     * @param y position of the button.
     * @param color Used for setting the correct color depending on the selected Pieces color.
     */
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

    /**
     * If the move results in any of the special moves en-passant, castling or promotion the return will be true resulting in the coloring of the GUI button being green.
     * @param newPosition the Square being checked for special moves condition.
     * @return true if conditions match those mentioned above.
     */
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

    /**
     * Method for ending the game if it was a draw.
     */
    public void draw() {
        theGameIsOver("DRAW");
    }


    /**
     * Method for ending the game if it was a win.
     */
    public void win() {
        String winner;
        if (turnCounter % 2 != 1) {
            winner = "Black Player";
        } else {
            winner = "White Player";
        }
        theGameIsOver(winner);
    }

    /**
     * A method to display the winner or if the game was a draw.
     * @param winner the name of the winner or information about the game ending in a draw.
     */
    public void theGameIsOver(String winner) {
        helloController.winOrDrawMessage(winner);
    }

    /**
     * Method that returns a string of the event.
     * @param pieceToMove the piece making the move.
     * @param newPosition the Coordinate that the piece will occupy once the move is made.
     * @return String containing information about the event.
     */
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

    /**
     * Method for making the special move castling where two pieces is moved at the same time.
     * @param direction determines if the move is being made to the left or right of the king.
     */
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

    /**
     * When communication the event with the player the x Coordinate will be displayed as a char instead of a number.
     * @param position x value of the Coordinate.
     * @return a char corresponding to the ints value.
     */
    public char intToLetter(int position) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        return chars[position];
    }

    /**
     * Creates an array of all possible moves available a player bases on their remaining Pieces
     * @param color of the player ending their turn.
     * @return array of Coordinates containing the opponents moves.
     */
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


    /**
     * Compares the position of the king to the array of opponents moves.
     * @param color the color of the player who made the last move.
     * @param opponentsMoves an array of all possible moves for every piece of the current player.
     * @return true if the opponents king is in check after a move has been made.
     */
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

    /**
     * Checks if a requested move will result in check and therefor will be invalid.
     * @param newPosition the position where the piece will end up if the move is valid.
     * @param currentPiece the current position of the Piece.
     * @return true if the move does not result in check.
     */
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

    /**
     * Method checking if the board-state is checkMate.
     * @param piece the last piece moved.
     * @return true if the latest move created the board-state checkMate.
     */
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

    /**
     * Method to return all possible moves of a selected piece for GUI to know if a movement was allowed or not.
     * @return all possible moves of a certain piece.
     */
    public ArrayList<Coordinate> getPossibleMoves(){
        return selectedPieceValidMoves;
    }
}
