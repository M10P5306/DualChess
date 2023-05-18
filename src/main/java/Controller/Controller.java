package Controller;

import Model.*;
import View.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import static java.lang.Math.abs;

/**
 * Controller linking the View and Model parts of the program.
 * @Author Mikael Nilsson.
 */
public class Controller {
    /**
     * Frame containing the GUI and a few messaging methods.
     */
    private MainFrame mainFrame;
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
     * Keeps a record of every event in the match and saves it for future reference.
     */
    private Logger logger;
    /**
     * Name of the player with the white pieces.
     */
    private String whitePlayer;
    /**
     * Name of the player with the black pieces.
     */
    private String blackPlayer;
    /**
     * Used for playing different sounds according to specific game events.
     */
    private AudioPlayer audioPlayer;
    /**
     * Array of moves available to the opponent which is used for checking if a move would result in check or checkmate.
     */
    private ArrayList<Coordinate> opponentsMoves;

    /**
     * This is the constructor in the controller class. It's called by the MenuFrame in order
     * to start the MainFrame and switch to it. The constructor also assigns values to the
     * instance variables.
     *
     * @author Edin Jahic.
     * @param whitePlayer  contains the name of the white player, which has been inputted by one of the players.
     * @param blackPlayer  contains the name of the black player, which has been inputted by one of the players.
     * @param gameMode     contains the name of the game mode chosen by the players.
     * @param gameModeTime contains the time (in seconds) for the chosen game mode.
     * @param noSound      a boolean which turn on/off the sound effects in the game based on the players' choice.
     */
    public Controller(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, boolean noSound) {
        this.mainFrame = new MainFrame(this, whitePlayer, blackPlayer, gameMode, gameModeTime);
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.logger = new Logger(whitePlayer, blackPlayer);
        this.selectedPieceValidMoves = new ArrayList<>();
        this.turnCounter = 0;
        if (noSound) {
            this.audioPlayer = null;
        } else {
            this.audioPlayer = new AudioPlayer();
        }

        updateBoardView();
    }

    /**
     * Loops through the array of Squares within Board and if there's a Piece the Pieces imageIcon will be displayed on the corresponding button on the GUI.
     */
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

    /**
     * This method checks if a requested move will result in check, if not it will move the Piece from its current Square to another and possibly replacing another
     * Piece already present there. The even will be logged accordingly and if the move is a special move a specific sound will be played. Lastly checks if the move
     * resulted in a game-ending board-state and if so saves all the games event to file or else the GUI will be updated with the new board-state.
     * @param newPositionX the x position of the requested move.
     * @param newPositionY the y position of the requested move.
     */
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
                    if (audioPlayer != null) {
                        audioPlayer.playSound("src/main/java/Sounds/death.wav");
                    }
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
                    castling(newPositionX - selectedPiecePosition.getX());
                }

                pieceToMove.addMoves();
                updateBoardView();
                String message = toPrint.toString();
                turnCounter++;
                mainFrame.getMainPanel().getEastPanel().setPlayersTurn(turnCounter);
                mainFrame.getMainPanel().getSouthPanel().insertText(message);
                logger.addEvent(message);
                board.setLastMovedPiece(pieceToMove);

                if (board.getSpecificSquare(newPosition).getPiece() instanceof BlackPawn ||
                        board.getSpecificSquare(newPosition).getPiece() instanceof WhitePawn) {
                    if (newPositionY == 0 || newPositionY == 7) {
                        String color = board.getSpecificSquare(newPositionX, newPositionY).getPiece().getColor();
                        board.getSpecificSquare(newPosition).setPiece(new Queen(color));
                        if (audioPlayer != null) {
                            audioPlayer.playSound("src/main/java/Sounds/yeah-boy.wav");
                        }
                        updateBoardView();
                    }
                }

                opponentsMoves = updateOpponentsMoves(pieceToMove.getColor());

                if (checkForCheck(pieceToMove.getColor(), updateOpponentsMoves(pieceToMove.getColor()))) {
                    if (checkForMate(pieceToMove)) {
                        win();
                        break;
                    }
                    mainFrame.getMainPanel().getSouthPanel().insertText("Check!");
                    logger.addEvent("Check!");
                }
                if (checkForMate(pieceToMove)) {
                    draw();
                }
            }
        }
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
                mainFrame.getMainPanel().getCenterPanel().setPossibleAttack(possibleX, possibleY);
            } else if (specialMove(coordinate)) {
                mainFrame.getMainPanel().getCenterPanel().setSpecialMove(possibleX, possibleY);
            } else {
                mainFrame.getMainPanel().getCenterPanel().setValidMove(possibleX, possibleY);
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
        logger.addEvent("the game was a draw!");
        logger.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain("DRAW");
    }

    /**
     * Method for ending the game if it was a win.
     */
    public void win() {
        String winner = "";
        if (turnCounter % 2 != 1) {
            winner = blackPlayer;
        } else {
            winner = whitePlayer;
        }
        logger.addEvent(winner + " won the game!");
        logger.writeHistoryToFile();
        mainFrame.getMainPanel().getCenterPanel().restoreDefaultColors();

        promptPlayAgain(winner);
    }

    /**
     * Sends a message to the MainFrame for communication with the players.
     * @param winner the name of the player who won the game.
     */
    public void promptPlayAgain(String winner) {
        int answer = mainFrame.winOrDrawMessage(winner);

        if (answer == 0) {
            resetGame();
        }
        if (answer == 1) {
            returnToMainMenu();
        }
    }

    /**
     * Method that returns an string of the event and plays the sound associated with the special move en-passant.
     * @param pieceToMove the piece making the move.
     * @param newPosition the Coordinate that the piece will occupy once the move is made.
     * @return String containing information about the event.
     */
    public String enPassant(Piece pieceToMove, Coordinate newPosition) {
        String message = "";

        if (pieceToMove.getColor().equals("White")) {
            message = board.getSpecificSquare(newPosition.getX(), newPosition.getY() - 1).getPiece().colorAndNameToString();
            board.getSpecificSquare(newPosition.getX(), newPosition.getY() - 1).setPiece(null);
        } else {
            message = board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).getPiece().colorAndNameToString();
            board.getSpecificSquare(newPosition.getX(), newPosition.getY() + 1).setPiece(null);
        }
        if (audioPlayer != null) {
            audioPlayer.playSound("src/main/java/Sounds/Genius.wav");
        }
        return message;
    }

    /**
     * Method for making the special move castling where two pieces is moved at the same time, also plays a sound associated with this move.
     * @param direction determines if the move is being made to the left or right of the king.
     */
    public void castling(int direction) {
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
        if (audioPlayer != null) {
            audioPlayer.playSound("src/main/java/Sounds/wow-113128.wav");
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
     * if the times reaches zero this method will be triggered, ending the game, logging the match history, displaying the winner and offer the players a new game.
     * @param player
     */
    public void timesUp(String player) {
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
    }

    /**
     * Method used when a player clicks the forfiet button in the GUI. Will end the game, logg the match history, display the winner and offer the players to play again.
     */
    public void forfeit() {
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
    }

    /**
     * Restores the game to it's starting game-state after a game-ending event.
     */
    public void resetGame() {
        board = new Board();
        logger = new Logger(whitePlayer, blackPlayer);
        turnCounter = 0;
        updateBoardView();
        mainFrame.getMainPanel().getEastPanel().resetTimers();
        mainFrame.getMainPanel().getSouthPanel().getJTextPane().setText("");
    }

    /**
     * Restarts the game and returns the players to the main menu.
     */
    public void returnToMainMenu() {
        mainFrame.dispose();
        MenuFrame menuFrame = new MenuFrame();
    }

    /**
     * This method is called when you press on a square which contains a Piece. It then plays the
     * sound stored in the specific piece class.
     *
     * @author Edin Jahic and Adel Mohammed Abo Khamis.
     * @param x contains the x coordinate of the square. Used to get a specific square on the board.
     * @param y contains the y coordinate of the square. Used to get a specific square on the board.
     */
    public void playMarkingSound(int x, int y) {
        String filePath = board.getSpecificSquare(x, y).getPiece().getSoundFilePath();
        if (audioPlayer != null) {
            audioPlayer.playSound(filePath);
        }
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
    public boolean checkForMate(Piece piece) {

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
