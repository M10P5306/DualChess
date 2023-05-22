package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the right side of the 2D GUI that holds the players names, times and shows which players turn it is.
 * @author Hugo Andersson
 */
public class EastPanel extends JPanel {
    /**
     * This variable is to keep track of how much time the white player has left.
     */
    private int whiteTimeRemaining;

    /**
     * This variable is to keep track of how much time the black player has left.
     */
    private int blackTimeRemaining;

    /**
     * To display how much time the white player has left.
     */
    private JLabel whitePlayerTime;

    /**
     * To display how much time the black player has left.
     */
    private JLabel blackPlayerTime;

    /**
     * This timer runs the time for the white player.
     */
    private Timer whiteTimer;

    /**
     * This timer runs the time for the black player.
     */
    private Timer blackTimer;

    /**
     * This label displays the name of the user who plays with white pieces.
     */
    private JLabel whiteLabel;

    /**
     * This label displays the name of the user who plays with black pieces.
     */
    private JLabel blackLabel;

    /**
     * This label displays an image of a white piece, which clarifies which user who plays with white pieces.
     */
    private JLabel whiteIconPicture;

    /**
     * This label displays an image of a black piece, which clarifies which user who plays with black pieces.
     */
    private JLabel blackIconPicture;

    /**
     * This color is used to display which users turn it is.
     */
    private final Color active = Color.ORANGE;

    /**
     * This color is used to display which user who has to wait for their turn.
     */
    private final Color inactive = Color.LIGHT_GRAY;

    /**
     * The time of the chosen game mode is displayed in the GUI through this string.
     */
    private String gameMode;

    /**
     * The time of the chosen game mode is counted down, beginning from this value.
     */
    private int gameModeTime;

    /**
     * The controller which enables communication between view and model.
     */
    private Controller controller;

    /**
     * The constructor sets up the labels for each player and displays their names. The timer for white player is started.
     * @param whitePlayer the name of the user who plays with white pieces.
     * @param blackPlayer the name of the user who plays with black pieces.
     * @param gameMode the starting time of the selected game mode.
     * @param gameModeTime the value used to count down the time each player has left.
     * @param controller the controller who enables for communication between model and view.
     */
    public EastPanel(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, Controller controller) {
        this.controller = controller;
        this.gameMode = gameMode;
        this.gameModeTime = gameModeTime;

        this.setLayout(new GridLayout(6, 1));
        this.setPreferredSize(new Dimension(150, 900));
        this.setMaximumSize(new Dimension(150, 900));

        setUpBlackPlayer(blackPlayer);
        setUpWhitePlayer(whitePlayer);
        whiteTimer.start();
    }

    /**
     * This method sets up the labels and time for white player.
     * @param whitePlayer name of the user who plays with white pieces.
     */
    private void setUpWhitePlayer(String whitePlayer) {
        whitePlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        whitePlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        whitePlayerTime.setBackground(active);
        whitePlayerTime.setOpaque(true);
        whiteTimeRemaining = gameModeTime;
        this.add(whitePlayerTime);

        whiteLabel = new JLabel(whitePlayer, SwingConstants.CENTER);
        whiteLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        whiteLabel.setBackground(active);
        whiteLabel.setOpaque(true);
        this.add(whiteLabel);

        ImageIcon icon = new ImageIcon("src/main/java/PlayerIcons/WhitePlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(53, 114, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        whiteIconPicture = new JLabel(icon);
        whiteIconPicture.setSize(53, 114);
        whiteIconPicture.setBackground(active);
        whiteIconPicture.setOpaque(true);
        this.add(whiteIconPicture);
        whiteTimer = new Timer(1000, e -> {
            changeWhitePlayerTime();
        });
    }

    /**
     * This method sets up the labels and time for black player.
     * @param blackPlayer name of the user who plays with black pieces.
     */
    private void setUpBlackPlayer(String blackPlayer) {
        ImageIcon icon = new ImageIcon("src/main/java/PlayerIcons/BlackPlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(52, 114, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        blackIconPicture = new JLabel(icon);
        blackIconPicture.setSize(53, 114);
        blackIconPicture.setBackground(inactive);
        blackIconPicture.setOpaque(true);
        this.add(blackIconPicture);

        blackLabel = new JLabel(blackPlayer, SwingConstants.CENTER);
        blackLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        blackLabel.setBackground(inactive);
        blackLabel.setOpaque(true);
        this.add(blackLabel);

        blackPlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        blackPlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        blackPlayerTime.setBackground(inactive);
        blackPlayerTime.setOpaque(true);
        blackTimeRemaining = gameModeTime;
        this.add(blackPlayerTime);

        blackTimer = new Timer(1000, e -> {
            changeBlackPlayerTime();
        });
    }

    /**
     * This method is called by controller to change which players turn it is. When called the time of the user who
     * ended their turn is stopped and the other users time is started.
     * @param playersTurn a value which determines which players turn it is.
     */
    public void setPlayersTurn(int playersTurn) {
        if (playersTurn % 2 != 1) {
            blackTimer.stop();
            whiteTimer.start();
        } else {
            whiteTimer.stop();
            blackTimer.start();
        }
        changeColorToDisplayTurn();
    }

    /**
     * This method is used to count down the white players time. If time hits 0, the controller is notified.
     */
    private void changeWhitePlayerTime() {
        whiteTimeRemaining--;
        if (whiteTimeRemaining == 0) {
            whiteTimer.stop();
            whitePlayerTime.setText("Time's up!");
            controller.timesUp("White");
        } else {
            int minutes = whiteTimeRemaining / 60;
            int seconds = whiteTimeRemaining % 60;
            whitePlayerTime.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }

    /**
     * This method is used to count down the black players time. If time hits 0, the controller is notified.
     */
    private void changeBlackPlayerTime() {
        blackTimeRemaining--;
        if (blackTimeRemaining == 0) {
            blackTimer.stop();
            blackPlayerTime.setText("Time's up!");
            controller.timesUp("Black");
        } else {
            int minutes = blackTimeRemaining / 60;
            int seconds = blackTimeRemaining % 60;
            blackPlayerTime.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }

    /**
     * This method resets time, timers and the display of which players turn it is.
     */
    public void resetTimers() {
        whiteTimeRemaining = gameModeTime;
        blackTimeRemaining = gameModeTime;
        blackPlayerTime.setText(gameMode);
        whitePlayerTime.setText(gameMode);
        blackTimer.stop();
        whiteTimer.stop();
        whiteTimer.start();
        changeColorToDisplayTurn();
    }

    /**
     * This method clarifies which players turn it is, by changing color of the background of both players. If it is a
     * players turn, the labels of their respective label is set to active, else it is set to inactive.
     */
    private void changeColorToDisplayTurn() {
        if (whiteTimer.isRunning()) {
            blackLabel.setBackground(inactive);
            blackPlayerTime.setBackground(inactive);
            blackIconPicture.setBackground(inactive);

            whiteLabel.setBackground(active);
            whitePlayerTime.setBackground(active);
            whiteIconPicture.setBackground(active);
        } else {
            whiteLabel.setBackground(inactive);
            whitePlayerTime.setBackground(inactive);
            whiteIconPicture.setBackground(inactive);

            blackLabel.setBackground(active);
            blackPlayerTime.setBackground(active);
            blackIconPicture.setBackground(active);
        }
    }
}



