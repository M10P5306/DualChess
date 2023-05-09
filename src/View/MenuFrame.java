package View;

import Controller.Controller;

import javax.swing.*;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        this.setResizable(false);
        MenuPanel menuPanel = new MenuPanel(this);
        this.setTitle("DualChess");
        this.setSize(700, 700);
        this.setContentPane(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void startGame(String whitePLayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.dispose();
        Controller controller = new Controller(whitePLayer, blackPlayer, gameMode, gameModeTime);
    }
}
