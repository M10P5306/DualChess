package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;

public class Controller {

    private MainFrame mainFrame;

    private Board board;

    private Coordinate selectedPiece;

    private ArrayList<Coordinate> selectedPieceValidMoves;

    public Controller() {
        this.mainFrame = new MainFrame(this);
        this.board = new Board(this);
        this.selectedPieceValidMoves = new ArrayList<>();
        updateBoardView();

    }

    public void startGame() {
        mainFrame.startGame();
    }

    public void updateBoardView() {

        Square[][] temporarySquares = board.getSquares();

        for (int x = 0; x<temporarySquares.length; x++) {
            for (int y = 0; y<temporarySquares[x].length; y++) {
                if (temporarySquares[x][y].getPiece() != null) {
                    String text = temporarySquares[x][y].getPiece().colorAndNameToString();
                    mainFrame.getMainPanel().getButtons()[x][y].setText(text);
                }
                else {mainFrame.getMainPanel().getButtons()[x][y].setText(x + "," + y);}
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

                mainFrame.getMainPanel().insertText(message);
            }
        }
    }

    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x,y).getPiece() != null) {
            String toPrint = x + "," + y + " " + board.getSpecificSquare(x, y).getPiece().colorAndNameToString();
            this.selectedPiece = new Coordinate(x, y);
            selectedPieceValidMoves = board.getValidMoves(selectedPiece);
            mainFrame.getMainPanel().setValidMoves(selectedPieceValidMoves);
        return true;
        }
        return false;
    }

    public void resetBoard(){
        board = new Board(this);
        updateBoardView();
    }

}
