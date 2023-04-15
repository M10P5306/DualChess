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

    public EastPanel(String whitePlayer, String blackPlayer) {
        this.setLayout(new GridLayout(8, 1));
        setUp(whitePlayer, blackPlayer);
        whiteTimer.start();
    }

    private void setUp(String whitePlayer, String blackPlayer){
        JLabel whiteLabel = new JLabel(whitePlayer);
        whiteLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(whiteLabel);

        whitePlayerTime = new JLabel("30:00", SwingConstants.CENTER);
        whitePlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(whitePlayerTime);
        whiteTimeRemaining = 1800;

        whiteTimer = new Timer(1000, e -> {
            changeWhitePlayerTime();
        });

        JLabel blackLabel = new JLabel(blackPlayer);
        blackLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(blackLabel);

        blackPlayerTime = new JLabel("30:00", SwingConstants.CENTER);
        blackPlayerTime.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(blackPlayerTime);
        blackTimeRemaining = 1800;

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
}



