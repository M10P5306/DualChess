package View;

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
    private JLabel whiteIconPicture;
    private JLabel blackIconPicture;
    private final Color active = Color.ORANGE;
    private final Color inactive = Color.LIGHT_GRAY;
    private String gameMode;
    private int gameModeTime;

    public EastPanel(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.gameMode = gameMode;
        this.gameModeTime = gameModeTime;

        this.setLayout(new GridLayout(6, 1));
        this.setPreferredSize(new Dimension(150, 900));
        this.setMaximumSize(new Dimension(150,900));

        setUpBlackPlayer(blackPlayer);
        setUpWhitePlayer(whitePlayer);
        whiteTimer.start();
    }

    private void setUpWhitePlayer(String whitePlayer){
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

    private void setUpBlackPlayer (String blackPlayer){
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
        if(playersTurn % 2 != 1){
            blackTimer.stop();
            whiteTimer.start();
        } else{
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
        } else {
            int minutes = blackTimeRemaining / 60;
            int seconds = blackTimeRemaining % 60;
            blackPlayerTime.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }

    public void resetTimers(){
        whiteTimeRemaining = gameModeTime;
        blackTimeRemaining = gameModeTime;
        blackPlayerTime.setText(gameMode);
        whitePlayerTime.setText(gameMode);
        blackTimer.stop();
        whiteTimer.stop();
        whiteTimer.start();
        changeColorToDisplayTurn();
    }

    private void changeColorToDisplayTurn(){
        if(whiteTimer.isRunning()){
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



