package ExtremeMode.Controller;

import Model.AudioPlayer;
import Model.Board;
import Model.Coordinate;
import Model.Logger;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;

public class ExtremeController {
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

    public ExtremeController(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
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

    
}

/**
 * VAD ÄR EXTREME MODE:
 * Pjäser ska ha poängvärde.
 * Spelare ska ha liv.
 * Pjäser kan skjuta.
 * Lådor som innehåller antingen bom eller ny pjäs/mer liv/extra move.
 * Spelet avslutas när spelare har 0 liv.
 * Knight kan göra en charge.
 */