package View;

import Controller.Controller;
import com.example.chesspiece3d.HelloApplication;

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
        boolean noSound = false;
        this.dispose();
        Controller controller = new Controller(whitePLayer, blackPlayer, gameMode, gameModeTime, noSound);
    }

    public void startGameWithoutSound(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        boolean noSound = true;
        this.dispose();
        Controller controller = new Controller(whitePlayer, blackPlayer, gameMode, gameModeTime, noSound);
    }

    public void start3D(){
        this.dispose();
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start();
    }
}
