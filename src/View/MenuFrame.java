package View;

import Controller.Controller;
import ExtremeMode.Controller.ExtremeController;

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

    public void startGameWithoutSound(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        boolean noSound = true;
        this.dispose();
        Controller controller = new Controller(whitePlayer, blackPlayer, gameMode, gameModeTime, noSound);
    }

    public void startExtremeMode(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.dispose();
        ExtremeController controller = new ExtremeController(whitePlayer, blackPlayer, gameMode, gameModeTime, false);
    }

    public void startExtremeModeWithoutSound(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.dispose();
        ExtremeController controller = new ExtremeController(whitePlayer, blackPlayer, gameMode, gameModeTime, true);
    }
}
