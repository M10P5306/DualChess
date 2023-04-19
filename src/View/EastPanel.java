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
    private String gameMode;
    private int gameModeTime;



    public EastPanel(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        this.gameMode = gameMode;
        this.gameModeTime = gameModeTime;

        this.setLayout(new GridLayout(8, 1));
        this.setPreferredSize(new Dimension(150, 900));
        this.setMaximumSize(new Dimension(150,900));

        setUpWhitePlayer(whitePlayer);
        setUpBlackPlayer(blackPlayer);
        whiteTimer.start();
    }

    private void setUpWhitePlayer(String whitePlayer){
        whiteLabel = new JLabel(whitePlayer, SwingConstants.CENTER);
        whiteLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        whiteLabel.setForeground(Color.ORANGE);
        this.add(whiteLabel);

        whitePlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        whitePlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(whitePlayerTime);
        whiteTimeRemaining = gameModeTime;

        whiteTimer = new Timer(1000, e -> {
            changeWhitePlayerTime();
        });
    }

    private void setUpBlackPlayer (String blackPlayer){
        blackLabel = new JLabel(blackPlayer, SwingConstants.CENTER);
        blackLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(blackLabel);

        blackPlayerTime = new JLabel(gameMode, SwingConstants.CENTER);
        blackPlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(blackPlayerTime);
        blackTimeRemaining = gameModeTime;

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
        blackLabel.setForeground(Color.BLACK);
        whiteLabel.setForeground(Color.ORANGE);
        whiteTimeRemaining = gameModeTime;
        blackTimeRemaining = gameModeTime;
        blackPlayerTime.setText("30:00");
        whitePlayerTime.setText("30:00");
        blackTimer.stop();
        whiteTimer.stop();
        whiteTimer.start();
    }

    public void changeColorOfTimer(){
        if(whiteTimer.isRunning()){
            blackLabel.setForeground(Color.BLACK);
            whiteLabel.setForeground(Color.ORANGE);
        } else {
            whiteLabel.setForeground(Color.BLACK);
            blackLabel.setForeground(Color.ORANGE);
        }
    }

}



