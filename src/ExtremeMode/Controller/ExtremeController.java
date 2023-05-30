package ExtremeMode.Controller;

import ExtremeMode.Model.*;
import Model.*;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class ExtremeController {
    private MainFrame mainFrame;
    private ExtremeBoard board;
    private Coordinate selectedPiecePosition;
    private ArrayList<Coordinate> selectedPieceValidMoves;
    private int turnCounter;
    private Logger logger;
    private String whitePlayer;
    private String blackPlayer;
    private AudioPlayer audioPlayer;
    private ArrayList<Coordinate> opponentsMoves;
    private ExtremeRuleHandler ruleHandler;

    public ExtremeController(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.mainFrame = new MainFrame(this, whitePlayer, blackPlayer, gameMode, gameModeTime);
        this.board = new ExtremeBoard();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.logger = new Logger(whitePlayer, blackPlayer);
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
                mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
            } else {
                mainFrame.getMainPanel().getCenterPanel().setValidMove(possibleX, possibleY);
            }
        }
    }
    public void movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);
        PieceExtreme pieceToMove = board.getSpecificSquare(selectedPiecePosition).getPiece();

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiecePosition).getPiece().colorAndNameToString() + " moved from " +
                        intToLetter(selectedPiecePosition.getX()) + "," + (selectedPiecePosition.getY() + 1) + " to " + intToLetter(newPositionX) + "," + (newPositionY + 1);
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPosition).hasPiece()) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPosition).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                    if (audioPlayer != null) {
                        audioPlayer.playSound("src/Sounds/death.wav");
                    }
                }

                board.getSpecificSquare(selectedPiecePosition).setPiece(null);

                board.getSpecificSquare(newPosition).setPiece(pieceToMove);

                pieceToMove.addMoves();
                updateBoardView();
                String message = toPrint.toString();
                turnCounter++;
                mainFrame.getMainPanel().getEastPanel().setPlayersTurn(turnCounter);
                mainFrame.getMainPanel().getSouthPanel().insertText(message);
                logger.addEvent(message);
                board.setLastMovedPiece(pieceToMove);

              //  opponentsMoves = updateOpponentsMoves(pieceToMove.getColor());

            }
        }
    }
    public char intToLetter(int position) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        return chars[position];
    }
    public void playMarkingSound(int x, int y) {
        String filePath = board.getSpecificSquare(x, y).getPiece().getSoundFilePath();
        if (audioPlayer != null) {
            audioPlayer.playSound(filePath);
        }
    }


}

/**
 * VAD ÄR EXTREME MODE:
 * Pjäser ska ha poängvärde.
 * Spelare ska ha liv.
 * Pjäser kan skjuta.
 * Lådor som innehåller antingen bom eller ny pjäs/mer liv/extra move.
 * Spelet avslutas när spelare har 0 liv.
 * Knight kan göra en charge.
 * 
 * 
 *
 */
// TODO: 2023-05-25 check valid moves fixa.
// TODO: 2023-05-25 implementera shots.

