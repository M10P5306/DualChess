package View;

import Controller.Controller;
import com.example.chesspiece3d.HelloApplication;

import javax.swing.*;

/**
 * The MenuFrame class is the frame which contains the main menu, and it's the first class called in the
 * program (by main).
 * @author Edin Jahic and Hugo Andersson.
 */
public class MenuFrame extends JFrame {

    /**
     * This is the constructor in the MenuFrame class which is called by the main class when the program
     * starts. It is also called when the players want to return to the main menu.
     * @author Edin Jahic.
     */
    public MenuFrame() {
        this.setResizable(false);
        MenuPanel menuPanel = new MenuPanel(this);
        this.setTitle("DualChess");
        this.setSize(700, 700);
        this.setContentPane(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method is called when a player presses the start button in the main menu. The parameters
     * contain all the inputs and information from the players and forwards all the information to the
     * controller.
     *
     * @author Edin Jahic.
     * @param whitePLayer  contains the white player name.
     * @param blackPlayer  contains the black player name.
     * @param gameMode     contains the chosen game modes name.
     * @param gameModeTime contains the chosen game modes time in seconds.
     */
    public void startGame(String whitePLayer, String blackPlayer, String gameMode, int gameModeTime) {
        boolean noSound = false;
        this.dispose();
        Controller controller = new Controller(whitePLayer, blackPlayer, gameMode, gameModeTime, noSound);
    }

    /**
     * This is the same method as the one above (startGame()), but it also has a boolean that is sent to the
     * controller which disables the sound effects in the game if chosen by the players.
     *
     * @author Edin Jahic.
     * @param whitePlayer  contains the white player name.
     * @param blackPlayer  contains the black player name.
     * @param gameMode     contains the chosen game modes name.
     * @param gameModeTime contains the chosen game modes time in seconds.
     */
    public void startGameWithoutSound(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        boolean noSound = true;
        this.dispose();
        Controller controller = new Controller(whitePlayer, blackPlayer, gameMode, gameModeTime, noSound);
    }

    /**
     * This method starts the 3D chess board, if a player selects the 3D radiobutton and presses
     * the start button in the main menu.
     *
     * @author Hugo Andersson.
     */
    public void start3D(){
        this.dispose();
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start();
    }
}
