package View;

import Controller.Controller;
import ExtremeMode.Controller.ExtremeController;

import javax.swing.*;
import java.awt.*;

public class EastPanel extends JPanel {
    private int whiteTimeRemaining;
    private int blackTimeRemaining;
    private JLabel whitePlayerTime;
    private JLabel blackPlayerTime;
    private Timer whiteTimer;
    private Timer blackTimer;
    private JLabel whiteLabel;
    private JLabel blackLabel;
    private JLabel whiteHealth;
    private JLabel blackHealth;
    private JLabel whiteIconPicture;
    private JLabel blackIconPicture;
    private final Color active = Color.ORANGE;
    private final Color inactive = Color.LIGHT_GRAY;
    private String gameMode;
    private int gameModeTime;
    private Controller controller;
    private ExtremeController extremeController;
    private int whitePlayerHealth;
    private int blackPlayerHealth;

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
     * This constructor is used in the extreme mode.
     * @param whitePlayer
     * @param blackPlayer
     * @param whitePlayerHealth
     * @param blackPlayerHealth
     * @param gameMode
     * @param gameModeTime
     * @param extremeController
     */
    public EastPanel(String whitePlayer, String blackPlayer, int whitePlayerHealth, int blackPlayerHealth, String gameMode, int gameModeTime, ExtremeController extremeController) {
        this.extremeController = extremeController;
        this.gameMode = gameMode;
        this.gameModeTime = gameModeTime;

        this.setLayout(new GridLayout(6, 1));
        this.setPreferredSize(new Dimension(150, 900));
        this.setMaximumSize(new Dimension(150, 900));

        setUpBlackPlayerExtreme(blackPlayer, blackPlayerHealth);
        setUpWhitePlayerExtreme(whitePlayer, whitePlayerHealth);
        whiteTimer.start();
    }

    /**
     * Sets up white player for extreme mode.
     * @param whitePlayer
     * @param whitePlayerHealth
     */
    private void setUpWhitePlayerExtreme(String whitePlayer, int whitePlayerHealth) {
        whitePlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        whitePlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        whitePlayerTime.setBackground(active);
        whitePlayerTime.setOpaque(true);
        whiteTimeRemaining = gameModeTime;

        whiteLabel = new JLabel(whitePlayer, SwingConstants.CENTER);
        whiteLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        whiteLabel.setBackground(active);
        whiteLabel.setOpaque(true);
        this.add(whiteLabel);

        this.whitePlayerHealth = whitePlayerHealth;
        whiteHealth = new JLabel(String.valueOf(whitePlayerHealth), SwingConstants.CENTER);
        whiteHealth.setFont(new Font("Arial", Font.BOLD, 24));
        whiteHealth.setBackground(active);
        whiteHealth.setOpaque(true);
        this.add(whiteHealth);

        ImageIcon icon = new ImageIcon("src/PlayerIcons/WhitePlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(53, 114, java.awt.Image.SCALE_SMOOTH);
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
     * Sets up black player for extreme mode.
     * @param blackPlayer
     * @param blackPlayerHealth
     */
    private void setUpBlackPlayerExtreme(String blackPlayer, int blackPlayerHealth) {
        ImageIcon icon = new ImageIcon("src/PlayerIcons/BlackPlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(52, 114, java.awt.Image.SCALE_SMOOTH);
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

        this.blackPlayerHealth = blackPlayerHealth;
        blackHealth = new JLabel(String.valueOf(blackPlayerHealth), SwingConstants.CENTER);
        blackHealth.setFont(new Font("Arial", Font.BOLD, 24));
        blackHealth.setBackground(inactive);
        blackHealth.setOpaque(true);
        this.add(blackHealth);

        blackPlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        blackPlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        blackPlayerTime.setBackground(inactive);
        blackPlayerTime.setOpaque(true);
        blackTimeRemaining = gameModeTime;

        blackTimer = new Timer(1000, e -> {
            changeBlackPlayerTime();
        });
    }

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

        ImageIcon icon = new ImageIcon("src/PlayerIcons/WhitePlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(53, 114, java.awt.Image.SCALE_SMOOTH);
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

    private void setUpBlackPlayer(String blackPlayer) {
        ImageIcon icon = new ImageIcon("src/PlayerIcons/BlackPlayerIcon.png");
        Image image = icon.getImage().getScaledInstance(52, 114, java.awt.Image.SCALE_SMOOTH);
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

    private void changeColorToDisplayTurn() {
        if (whiteTimer.isRunning()) {
            blackLabel.setBackground(inactive);
            blackPlayerTime.setBackground(inactive);
            blackIconPicture.setBackground(inactive);

            whiteLabel.setBackground(active);
            whitePlayerTime.setBackground(active);
            whiteIconPicture.setBackground(active);

            if (extremeController != null) {
                blackHealth.setBackground(inactive);
                whiteHealth.setBackground(active);
            }
        } else {
            whiteLabel.setBackground(inactive);
            whitePlayerTime.setBackground(inactive);
            whiteIconPicture.setBackground(inactive);

            blackLabel.setBackground(active);
            blackPlayerTime.setBackground(active);
            blackIconPicture.setBackground(active);

            if (extremeController != null) {
                blackHealth.setBackground(active);
                whiteHealth.setBackground(inactive);
            }
        }
    }

    public void increaseWhitePlayerHealth(int points) {
        whitePlayerHealth = whitePlayerHealth+points;
        whiteHealth.setText(String.valueOf(whitePlayerHealth));
    }

    public void decreaseWhitePlayerHealth(int points) {
        whitePlayerHealth = whitePlayerHealth-points;
        whiteHealth.setText(String.valueOf(whitePlayerHealth));
    }

    public void increaseBlackPlayerHealth(int points) {
        blackPlayerHealth = blackPlayerHealth+points;
        blackHealth.setText(String.valueOf(blackPlayerHealth));
    }

    public void decreaseBlackPlayerHealth(int points) {
        blackPlayerHealth = blackPlayerHealth-points;
        blackHealth.setText(String.valueOf(blackPlayerHealth));
    }

    public int getWhitePlayerHealth() {
        return whitePlayerHealth;
    }

    public int getBlackPlayerHealth() {
        return blackPlayerHealth;
    }
}



