package Controller;

import Model.*;
import View.*;
import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private MainFrame mainFrame;
    private Board board;
    private Coordinate selectedPiece;
    private ArrayList<Coordinate> selectedPieceValidMoves;
    private int turnCounter;
    private Logger log;
    private String whitePlayer;
    private String blackPlayer;

    public Controller(String whitePlayer, String blackPlayer) {
        this.mainFrame = new MainFrame(this, whitePlayer, blackPlayer);
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.log = new Logger(whitePlayer, blackPlayer);
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;

        updateBoardView();
    }

    public void updateBoardView() {
        for (int x = 0; x < board.getSquares().length; x++) {
            for (int y = 0; y < board.getSquares()[x].length; y++) {
                if (board.getSquares()[x][y].getPiece() != null) {
                    String text = board.getSquares()[x][y].getPiece().colorAndNameToString();
                    mainFrame.getMainPanel().getCenterPanel().getButtons()[x][y].setText(text);
                } else {
                    mainFrame.getMainPanel().getCenterPanel().getButtons()[x][y].setText("");
                }
            }
        }
    }

    public void movePiece(int newPositionX, int newPositionY) {
        Coordinate newPosition = new Coordinate(newPositionX, newPositionY);

        for (Coordinate coordinate : selectedPieceValidMoves) {
            if (coordinate.equals(newPosition)) {
                String event = board.getSpecificSquare(selectedPiece).getPiece().colorAndNameToString() + " moved from " + selectedPiece.getX() + "," + selectedPiece.getY() + " to " + newPositionX + "," + newPositionY;
                StringBuilder toPrint = new StringBuilder(event);

                if (board.getSpecificSquare(newPositionX, newPositionY).hasPiece() && board.getSpecificSquare(newPositionX, newPositionY).getPiece() != null) {
                    String takenPiece = " and took " + board.getSpecificSquare(newPositionX, newPositionY).getPiece().colorAndNameToString();
                    toPrint.append(takenPiece);
                }

                String message = toPrint.toString();
                Piece pieceToMove = board.getSpecificSquare(selectedPiece).getPiece();
                board.getSpecificSquare(selectedPiece).setPiece(null);
                board.getSpecificSquare(newPositionX, newPositionY).setPiece(pieceToMove);
                pieceToMove.addMoves();
                updateBoardView();
                turnCounter++;
                mainFrame.getMainPanel().getEastPanel().setPlayersTurn(turnCounter);
                mainFrame.getMainPanel().getSouthPanel().insertText(message);
                log.addEvent(message);
            }
        }
    }

    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x, y).getPiece() != null) {
            if (turnCounter % 2 != 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("White")) {
                this.selectedPiece = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiece);
                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    mainFrame.getMainPanel().getCenterPanel().setValidMoves(possibleX, possibleY);
                }
                return true;
            }
            if (turnCounter % 2 == 1 && board.getSpecificSquare(x, y).getPiece().getColor().equals("Black")) {
                this.selectedPiece = new Coordinate(x, y);
                selectedPieceValidMoves = board.getValidMoves(selectedPiece);
                for (Coordinate coordinate : selectedPieceValidMoves) {
                    int possibleX = coordinate.getX();
                    int possibleY = coordinate.getY();
                    mainFrame.getMainPanel().getCenterPanel().setValidMoves(possibleX, possibleY);
                }
                return true;
            }
        }
        return false;
    }

    public void resetBoard() {
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to forfeit?");
        if (answer == 0) {
            if (turnCounter % 2 != 1) {
                log.addEvent(whitePlayer + " forfeited");
            } else {
                log.addEvent(blackPlayer + " forfeited");
            }
            board = new Board();
            turnCounter = 0;
            updateBoardView();
            log.writeHistoryToFile();
        }
    }

}
