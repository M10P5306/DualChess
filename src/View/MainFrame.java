package View;

import Controller.Controller;
import javax.swing.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private MainPanel mainPanel;
    private MenuPanel menuPanel;

    public MainFrame() {
        this.setResizable(false);
        this.menuPanel = new MenuPanel(this);
        this.setTitle("DualChess");
        this.setSize(700, 700);
        this.setContentPane(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MainFrame(Controller controller, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.controller = controller;
        this.mainPanel = new MainPanel(this, whitePlayer, blackPlayer, gameMode, gameModeTime);
        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void startGame(String whitePLayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.dispose();
        controller = new Controller(whitePLayer, blackPlayer, gameMode, gameModeTime);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public Controller getController(){
        return controller;
    }

}