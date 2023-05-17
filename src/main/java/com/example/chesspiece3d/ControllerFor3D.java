package com.example.chesspiece3d;

import Model.*;
import View.MenuFrame;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.abs;

public class ControllerFor3D {
    private Board board;
    private Coordinate selectedPiecePosition;
    private ArrayList<Coordinate> selectedPieceValidMoves;
    private int turnCounter;
    //private Logger logger;
    private String whitePlayer;
    private String blackPlayer;
    //private AudioPlayer audioPlayer;
    private ArrayList<Coordinate> opponentsMoves;
    private HelloController helloController;


    public ControllerFor3D(HelloController helloController, Board board) {
        this.helloController = helloController;
        this.board = board;
        //this.whitePlayer = whitePlayer;
        //this.blackPlayer = blackPlayer;
        //this.logger = new Logger(whitePlayer, blackPlayer);
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
        //this.audioPlayer = new AudioPlayer();

        //updateBoardView();
    }

    /*
    public Controller(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, boolean noSound) {
        this.mainFrame = new MainFrame(this, whitePlayer, blackPlayer, gameMode, gameModeTime);
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.logger = new Logger(whitePlayer, blackPlayer);
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
        this.audioPlayer = null;

        updateBoardView();
    }*/

    public void updateBoardView() {
        helloController.updateBoardView();
        helloController.restoreDefaultColors();
    }

    public boolean movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);
        Piece pieceToMove = board.getSpecificSquare(selectedPiecePosition).getPiece();


        if (isCheck(newPosition, selectedPiecePosition)) {
            System.out.println("Illegal move."); //UPPDATERA
            return false; //ändrat
        }

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiecePosition).getPiece().colorAndNameToString() + " moved from " +
                        intToLetter(selectedPiecePosition.getX()) + "," + (selectedPiecePosition.getY() + 1) + " to " + intToLetter(newPositionX) + "," + (newPositionY + 1);
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPosition).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                    /*if (audioPlayer != null) {
                        audioPlayer.playSound("src/Sounds/death.wav");
                    }*/
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
                //updateBoardView();
                String message = toPrint.toString();
                turnCounter++;
                helloController.setPlayersTurn(turnCounter);
                System.out.println(message); //UPPDATERA
                //logger.addEvent(message);
                board.setLastMovedPiece(pieceToMove);

                if (board.getSpecificSquare(newPosition).getPiece() instanceof BlackPawn ||
                        board.getSpecificSquare(newPosition).getPiece() instanceof WhitePawn) {
                    if (newPositionY == 0 || newPositionY == 7) {
                        String color = board.getSpecificSquare(newPositionX, newPositionY).getPiece().getColor();
                        board.getSpecificSquare(newPosition).setPiece(new Queen(color));
                        helloController.promoted(newPosition, color);
                        /*if (audioPlayer != null) {
                            audioPlayer.playSound("src/Sounds/yeah-boy.wav");
                        }*/
                       // updateBoardView();
                    }
                }

                opponentsMoves = updateOpponentsMoves(pieceToMove.getColor());

                if (checkForCheck(pieceToMove.getColor(), updateOpponentsMoves(pieceToMove.getColor()))) {
                    if (checkForMatt(pieceToMove)) {
                        win();
                        break;
                    }
                    System.out.println("Check!"); //UPPDATERA
                    //logger.addEvent("Check!");
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
        //logger.addEvent("the game was a draw!");
        //logger.writeHistoryToFile();
        //mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain("DRAW");
    }


    public void win() {
        String winner = "";
        if (turnCounter % 2 != 1) {
            winner = blackPlayer;
        } else {
            winner = whitePlayer;
        }
        //logger.addEvent(winner + " won the game!");
        //logger.writeHistoryToFile();
        //mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain(winner);
    }

    public void promptPlayAgain(String winner) {
        int answer = helloController.winOrDrawMessage(winner);

        if (answer == 0) {
            resetGame();
        }
        if (answer == 1) {
            returnToMainMenu();
        }
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
        /*if (audioPlayer != null) {
            audioPlayer.playSound("src/Sounds/Genius.wav");
        }*/
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
        /*if (audioPlayer != null) {
            audioPlayer.playSound("src/Sounds/wow-113128.wav");
        }*/
    }

    public char intToLetter(int position) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        return chars[position];
    }

    /*public void timesUp(String player) {
        String winner;

        if (player.equals("White")) {
            winner = blackPlayer;
        } else {
            winner = whitePlayer;
        }
        logger.addEvent(winner + " won the game!");
        logger.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain(winner);
    }*/

    /*public void forfeit() {
        int answer = mainFrame.forfeitMessage();

        if (answer == 0) {
            String winner = "";
            String loser = "";
            if (turnCounter % 2 != 1) {
                loser = whitePlayer;
                winner = blackPlayer;
            } else {
                loser = blackPlayer;
                winner = whitePlayer;
            }

            logger.addEvent(loser + " forfeited");
            mainFrame.promptWinner(winner);
            logger.writeHistoryToFile();
            returnToMainMenu();
        }
    }*/

    public void resetGame() {
        board = new Board();
        //logger = new Logger(whitePlayer, blackPlayer);
        turnCounter = 0;
        updateBoardView();
        //mainFrame.getMainPanel().getEastPanel().resetTimers();
        //mainFrame.getMainPanel().getSouthPanel().getJTextPane().setText("");
    }

    public void returnToMainMenu() {
        helloController.close();
        MenuFrame menuFrame = new MenuFrame();
    }

   /* public void playMarkingSound(int x, int y) {
        String filePath = board.getSpecificSquare(x, y).getPiece().getSoundFilePath();
        if (audioPlayer != null) {
            audioPlayer.playSound(filePath);
        }
    }*/

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
