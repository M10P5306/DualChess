package Controller;

import Model.*;
import View.*;

public class Controller {

    private MainFrame mainFrame;

    private Board board;

    private Coordinate selectedPiece;

    public Controller() {
        this.mainFrame = new MainFrame(this);
        this.board = new Board(this);
        updateBoardView();

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
        Piece pieceToMove = board.getSpecificSquare(selectedPiece).getPiece();
        board.getSpecificSquare(selectedPiece).setPiece(null);
        board.getSpecificSquare(newPositionX, newPositionY).setPiece(pieceToMove);
        updateBoardView();
    }

    public boolean boardButtonSelected(int x, int y) {
        if (board.getSpecificSquare(x,y).getPiece() != null) {
            String toPrint = x + "," + y + " " + board.getSpecificSquare(x, y).getPiece().colorAndNameToString();
            mainFrame.getMainPanel().insertText(toPrint);
            this.selectedPiece = new Coordinate(x, y);
        return true;
        }
        return false;
    }

}
