package View;

import Controller.Controller;
import ExtremeMode.Controller.ExtremeController;

import javax.swing.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private ExtremeController extremeController;
    private MainPanel mainPanel;

    public MainFrame(Controller controller, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.controller = controller;
        this.mainPanel = new MainPanel(this, whitePlayer, blackPlayer, gameMode, gameModeTime, controller);
        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MainFrame(ExtremeController controller, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.extremeController = controller;
        this.mainPanel = new MainPanel(this, whitePlayer, blackPlayer, gameMode, gameModeTime, controller);
        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public Controller getController(){
        return controller;
    }

    public void promptWinner(String winner) {
        JOptionPane.showMessageDialog(null, winner + " won the game!");
    }
    public int forfeitMessage() {
        return JOptionPane.showConfirmDialog(null, "Do you want to forfeit?");
    }
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