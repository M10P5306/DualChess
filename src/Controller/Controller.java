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
    private Coordinate selectedPiecePosition;
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
                    mainFrame.getMainPanel().getCenterPanel().getButton()[x][y].setIcon(icon);
                } else {
                    mainFrame.getMainPanel().getCenterPanel().getButton()[x][y].setIcon(null);
                }
            }
        }
    }

    public void movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);
        Piece pieceToMove = board.getSpecificSquare(selectedPiecePosition).getPiece();

        if (isCheck(newPosition, selectedPiecePosition)) {
            mainFrame.getMainPanel().getSouthPanel().insertText("Illegal move.");
            return;
        }

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiecePosition).getPiece().colorAndNameToString() + " moved from " +
                        intToLetter(selectedPiecePosition.getX()) + "," + (selectedPiecePosition.getY() + 1) + " to " + intToLetter(newPositionX) + "," + (newPositionY + 1);
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPosition).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                    audioPlayer.playSound("src/Sounds/death.wav");
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
                        win();
                        break;
                    }
                    mainFrame.getMainPanel().getSouthPanel().insertText("Check!");
                    log.addEvent("Check!");
                }
                if (checkForMatt(pieceToMove)) {
                    draw();
                }
            }
        }
    }


    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x, y).getPiece() != null) {
            if (turnCounter % 2 != 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("White")) {
                this.selectedPiecePosition = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiecePosition, opponentsMoves);

                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    if (board.getSpecificSquare(possibleX, possibleY).getPiece() != null && board.getSpecificSquare(possibleX, possibleY).getPiece().getColor().equals("Black")) {
                        mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
                    } else if (specialMove(coordinate)) {
                        mainFrame.getMainPanel().getCenterPanel().setSpecialMove(possibleX, possibleY);
                    } else {
                        mainFrame.getMainPanel().getCenterPanel().setValidMove(possibleX, possibleY);
                    }
                }
                return true;
            }

            if (turnCounter % 2 == 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("Black")) {
                this.selectedPiecePosition = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiecePosition, opponentsMoves);

                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    if (board.getSpecificSquare(possibleX, possibleY).getPiece() != null && board.getSpecificSquare(possibleX, possibleY).getPiece().getColor().equals("White")) {
                        mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
                    } else if (specialMove(coordinate)) {
                        mainFrame.getMainPanel().getCenterPanel().setSpecialMove(possibleX, possibleY);
                    } else {
                        mainFrame.getMainPanel().getCenterPanel().setValidMove(possibleX, possibleY);
                    }
                }
                return true;
            }
        }
        return false;
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
        log.addEvent("the game was a draw!");
        log.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain("DRAW");
    }

    public void win() {
        String winner = "";
        if (turnCounter % 2 != 1) {
            winner = blackPlayer;
        } else {
            winner = whitePlayer;
        }
        log.addEvent(winner + " won the game!");
        log.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain(winner);
    }

    public void promptPlayAgain(String winner) {
        int answer;
        if (winner.equals("DRAW")) {
            answer = JOptionPane.showConfirmDialog(null, "the Game was a draw! Would you like to play again?");
        } else {
            answer = JOptionPane.showConfirmDialog(null, winner + " won the game! Would you like to play again?");
        }
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
        } else {
            message = board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).getPiece().colorAndNameToString();
            board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).setPiece(null);
        }
        audioPlayer.playSound("src/Sounds/Genius.wav");
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
        }
        if (direction == -2) {
            rookPosition = new Coordinate(selectedPiecePosition.getX() - 4, selectedPiecePosition.getY());
            rookToMove = board.getSpecificSquare(rookPosition).getPiece();
            Coordinate newRookPosition = new Coordinate(selectedPiecePosition.getX() - 1, selectedPiecePosition.getY());
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

    public void timesUp(String player) {
        String winner;

        if (player.equals("White")) {
            winner = blackPlayer;
        } else {
            winner = whitePlayer;
        }
        log.addEvent(winner + " won the game!");
        log.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain(winner);
    }

    public void forfeit() {
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
            log.writeHistoryToFile();
            returnToMainMenu();
        }
    }

    public void resetGame() {
        board = new Board();
        turnCounter = 0;
        updateBoardView();
        mainFrame.getMainPanel().getEastPanel().resetTimers();
        mainFrame.getMainPanel().getSouthPanel().getJTextPane().setText("");
    }

    public void returnToMainMenu() {
        mainFrame.dispose();
        MainFrame newMainFrame = new MainFrame();
    }

    public void playMarkingSound(int x, int y) {
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

}
