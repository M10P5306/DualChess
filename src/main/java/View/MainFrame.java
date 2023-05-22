package View;

import Controller.Controller;

import javax.swing.*;

/**
 * This class is the frame for the 2D GUI. It holds a MainPanel, which holds all panels.
 * @author Hugo Andersson, Edin Jahic and Mikael Nilsson.
 */
public class MainFrame extends JFrame {

    /**
     * The class has a controller to enable communication between view and model.
     */
    private Controller controller;

    /**
     * The MainPanel holds all the different panels that together make up the 2D GUI.
     */
    private MainPanel mainPanel;

    /**
     * This is the constructor in the MainFrame class. It's called by the controller after the players
     * start the game from the main menu. It gives instance variables values and sets up the MainPanel
     * with the correct information from the players (names, chosen game mode etc.)
     * @author Hugo Andersson and Edin Jahic.
     * @param controller   controller as a parameter in order to give the instance variable "controller"
     *                     the correct value.
     * @param whitePlayer  contains the white players name.
     * @param blackPlayer  contains the black players name.
     * @param gameMode     contains the name of the game mode chosen by the players.
     * @param gameModeTime contains the chosen game mode time in seconds.
     */
    public MainFrame(Controller controller, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.controller = controller;
        this.mainPanel = new MainPanel(this, whitePlayer, blackPlayer, gameMode, gameModeTime, controller);
        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method is used by controller to access the different parts of the GUI that needs information from model.
     * @return MainPanel, which holds all panels that together make up the 2D GUI.
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * This method is used by the different panels that need to be able to send information to controller.
     * @return Controller, so communication is enabled.
     */
    public Controller getController(){
        return controller;
    }

    /**
     * When a user has won the game, this method displays the winner.
     * @param winner, name of the player who won.
     */
    public void promptWinner(String winner) {
        JOptionPane.showMessageDialog(null, winner + " won the game!");
    }

    /**
     * When a user forfeits, a confirmation dialog is created through this method.
     * @return users answer to whether or not they would like to forfeit.
     */
    public int forfeitMessage() {
        return JOptionPane.showConfirmDialog(null, "Do you want to forfeit?");
    }

    /**
     * This method is used when a user won or when the game ends with a draw. The user is presented with the option to either play again
     * or return to main menu.
     * @param winner, the name of the user who won.
     * @return users choice of whether to play again or not.
     */
    public int winOrDrawMessage(String winner) {
        int answer = 0;

        if (winner.equals("DRAW")) {
            answer = JOptionPane.showConfirmDialog(null, "the Game was a draw! Would you like to play again?");
        }
        else {
            answer = JOptionPane.showConfirmDialog(null, winner + " won the game! Would you like to play again?");
        }
        return answer;
    }
}