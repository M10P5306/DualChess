package View;

import Controller.Controller;
import javax.swing.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private MainPanel mainPanel;

    private MenuPanel menuPanel;

    public MainFrame(Controller controller, String whitePlayer, String blackPlayer) {
        this.controller = controller;
        this.mainPanel = new MainPanel(this, whitePlayer, blackPlayer);
        this.menuPanel = new MenuPanel(this);
        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setContentPane(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGame() {
        menuPanel.setVisible(false);
        this.setContentPane(mainPanel);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public Controller getController(){
        return controller;
    }

}