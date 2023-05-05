package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.abs;

public class Controller {

    private MainFrame mainFrame;
    private Board board;
    private Coordinate selectedPiece;
    private ArrayList<Coordinate> selectedPieceValidMoves;
    private int turnCounter;
    private Logger log;
    private String whitePlayer;
    private String blackPlayer;
    private AudioPlayer audioPlayer;
    private ArrayList<Coordinate> opponentsMoves;


    public Controller(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.mainFrame = new MainFrame(this, whitePlayer, blackPlayer, gameMode, gameModeTime);
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.log = new Logger(whitePlayer, blackPlayer);
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
        this.audioPlayer = new AudioPlayer();

        updateBoardView();
    }

    public void updateBoardView() {
        for (int x = 0; x < board.getSquares().length; x++) {
            for (int y = 0; y < board.getSquares()[x].length; y++) {
                if (board.getSquares()[x][y].getPiece() != null) {
                    ImageIcon icon = board.getSquares()[x][y].getPiece().getIcon();
                    mainFrame.getMainPanel().getCenterPanel().getButtons()[x][y].setIcon(icon);
                } else {
                    mainFrame.getMainPanel().getCenterPanel().getButtons()[x][y].setIcon(null);

                }
            }
        }
    }

    public void movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);
        Piece pieceToMove = board.getSpecificSquare(selectedPiece).getPiece();

        if (isCheck(newPosition, selectedPiece)) {
            return;
        }

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiece).getPiece().colorAndNameToString() + " moved from " +
                        intToLetter(selectedPiece.getX()) + "," + (selectedPiece.getY() + 1) + " to " + intToLetter(newPositionX) + "," + (newPositionY + 1);
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPosition).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                    audioPlayer.playSound("src/Sounds/death.wav");

                }

                if (board.getSpecificSquare(newPosition).getPiece() instanceof King) {
                    String message = toPrint.toString();
                    board.getSpecificSquare(selectedPiece).setPiece(null);
                    board.getSpecificSquare(newPosition).setPiece(pieceToMove);
                    pieceToMove.addMoves();
                    updateBoardView();
                    mainFrame.getMainPanel().getSouthPanel().insertText(message);
                    log.addEvent(message);
                    win();
                } else {
                    board.getSpecificSquare(selectedPiece).setPiece(null);
                    if ((pieceToMove instanceof WhitePawn || pieceToMove instanceof BlackPawn) &&
                            !(selectedPiece.getX() == newPositionX) &&
                            !board.getSpecificSquare(newPosition).hasPiece()) {
                        String takenPiece = " and took " + enPassant(pieceToMove, newPosition);
                        toPrint.append(takenPiece);
                    }
                    board.getSpecificSquare(newPosition).setPiece(pieceToMove);
                    if (pieceToMove instanceof King && abs(selectedPiece.getX() - newPositionX) == 2) {
                        rockad(newPositionX - selectedPiece.getX());
                    }
                    pieceToMove.addMoves();
                    updateBoardView();
                    String message = toPrint.toString();
                    turnCounter++;
                    mainFrame.getMainPanel().getEastPanel().setPlayersTurn(turnCounter);
                    mainFrame.getMainPanel().getSouthPanel().insertText(message);
                    log.addEvent(message);
                    board.setLastMovedPiece(pieceToMove);
                    if (board.getSpecificSquare(newPosition).getPiece() instanceof BlackPawn ||
                            board.getSpecificSquare(newPosition).getPiece() instanceof WhitePawn) {
                        if (newPositionY == 0 || newPositionY == 7) {
                            String color = board.getSpecificSquare(newPositionX, newPositionY).getPiece().getColor();
                            board.getSpecificSquare(newPosition).setPiece(new Queen(color));
                            audioPlayer.playSound("src/Sounds/yeah-boy.wav");
                            updateBoardView();
                        }
                    }
                    opponentsMoves = updateOpponentsMoves(pieceToMove.getColor());

                    if (checkForCheck(pieceToMove.getColor(), updateOpponentsMoves(pieceToMove.getColor()))) {
                        if (checkForMatt(pieceToMove)) {
                            System.out.println("Schackmatt!");
                         }
                        mainFrame.getMainPanel().getSouthPanel().insertText("Check!");
                        log.addEvent("Check!");
                    }
                }
            }
        }
    }

    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x, y).getPiece() != null) {
            if (turnCounter % 2 != 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("White")) {
                this.selectedPiece = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiece, opponentsMoves);
                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    if (board.getSpecificSquare(possibleX, possibleY).getPiece() != null && board.getSpecificSquare(possibleX, possibleY).getPiece().getColor().equals("Black")) {
                        mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
                    } else {
                        mainFrame.getMainPanel().getCenterPanel().setValidMoves(possibleX, possibleY);
                    }
                }
                return true;
            }
            if (turnCounter % 2 == 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("Black")) {
                this.selectedPiece = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiece, opponentsMoves);
                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    if (board.getSpecificSquare(possibleX, possibleY).getPiece() != null && board.getSpecificSquare(possibleX, possibleY).getPiece().getColor().equals("White")) {
                        mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
                    } else {
                        mainFrame.getMainPanel().getCenterPanel().setValidMoves(possibleX, possibleY);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void win() {
        String winner = "";
        if (turnCounter % 2 != 1) {
            winner = whitePlayer;
        } else {
            winner = blackPlayer;
        }
        log.addEvent(winner + " won the game!");
        log.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        int answer = JOptionPane.showConfirmDialog(null, winner + " won the game! Would you like to play again?");
        if (answer == 0) {
            resetGame();
        }
    }

    public String enPassant(Piece pieceToMove, Coordinate newPosition) {
        String message = "";

        if (pieceToMove.getColor().equals("White")) {
            message = board.getSpecificSquare(new Coordinate(newPosition.getX(), newPosition.getY() - 1)).getPiece().colorAndNameToString();
            board.getSpecificSquare(new Coordinate(newPosition.getX(), newPosition.getY() - 1)).setPiece(null);
        } else {
            message = board.getSpecificSquare(new Coordinate(newPosition.getX(), newPosition.getY() + 1)).getPiece().colorAndNameToString();
            board.getSpecificSquare(new Coordinate(newPosition.getX(), newPosition.getY() + 1)).setPiece(null);
        }
        audioPlayer.playSound("src/Sounds/Genius.wav");
        return message;
    }

    public void rockad(int direction) {
        Coordinate rookPosition;
        Piece rookToMove;

        if (direction == 2) {
            rookPosition = new Coordinate(selectedPiece.getX() + 3, selectedPiece.getY());
            rookToMove = board.getSpecificSquare(rookPosition).getPiece();
            Coordinate newRookPosition = new Coordinate(selectedPiece.getX() + 1, selectedPiece.getY());
            board.getSpecificSquare(newRookPosition).setPiece(rookToMove);
            board.getSpecificSquare(rookPosition).setPiece(null);
            rookToMove.addMoves();
        }
        if (direction == -2) {
            rookPosition = new Coordinate(selectedPiece.getX() - 4, selectedPiece.getY());
            rookToMove = board.getSpecificSquare(rookPosition).getPiece();
            Coordinate newRookPosition = new Coordinate(selectedPiece.getX() - 1, selectedPiece.getY());
            board.getSpecificSquare(newRookPosition).setPiece(rookToMove);
            board.getSpecificSquare(rookPosition).setPiece(null);
            rookToMove.addMoves();
        }
        audioPlayer.playSound("src/Sounds/wow-113128.wav");
    }

    public char intToLetter(int position) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        return chars[position];
    }

    public void resetBoard() {
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to forfeit?");
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
            log.addEvent(loser + " forfeited");
            JOptionPane.showMessageDialog(null, winner + " won the game!");
            resetGame();
            log.writeHistoryToFile();

        }
    }

    public void resetGame() {
        board = new Board();
        turnCounter = 0;
        updateBoardView();
        mainFrame.getMainPanel().getEastPanel().resetTimers();
        mainFrame.getMainPanel().getSouthPanel().getJTextPane().setText("");
    }

    public void playMarkingSound(int x , int y) {
        String filePath = board.getSpecificSquare(x, y).getPiece().getSoundFilePath();
        audioPlayer.playSound(filePath);
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
        }
        else {
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
        }
        else {
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
            for(Coordinate move : possibleMoves) {
                if (!isCheck(move, currentPosition)) {
                    System.out.println("Det fanns drag kvar att göra");
                    return false;
                }
            }
        }

        return true;
    }
}
