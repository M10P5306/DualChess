package View;

import Controller.Controller;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Enumeration;

public class MenuPanelCenter extends JPanel {
    private Main main;
    private MenuPanel menuPanel;
    private JTextArea nameInputOne;
    private JTextArea nameInputTwo;
    private JButton startButton;
    private JLabel nameLabelTwo;
    private String whitePlayer;
    private String blackPLayer;
    private int gameModeTime;
    private String gameMode;

    public MenuPanelCenter(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        this.setBorder(BorderFactory.createTitledBorder("PLAYER SETTINGS"));
        this.setLayout(null);
        setUp();
    }

    public void setUp() {
        setUpPlayerSettings();
        setUpTutorialButton();
    }

    public void setUpPlayerSettings() {
        JLabel playerSettingsTitle = new JLabel("Enter player name(s) below");
        playerSettingsTitle.setSize(500,50);
        playerSettingsTitle.setLocation(50,50);
        playerSettingsTitle.setFont(new Font("Verdana", Font.BOLD, 14));
        this.add(playerSettingsTitle);

        JLabel nameLabelOne = new JLabel("Player 1 name: ");
        nameLabelOne.setSize(300,20);
        nameLabelOne.setLocation(50,100);
        this.add(nameLabelOne);

        nameLabelTwo = new JLabel("Player 2 name: ");
        nameLabelTwo.setSize(300,20);
        nameLabelTwo.setLocation(50, 150);
        this.add(nameLabelTwo);

        nameInputOne = new JTextArea();
        nameInputOne.setSize(100,20);
        nameInputOne.setLocation(170,102);
        this.add(nameInputOne);

        nameInputTwo = new JTextArea();
        nameInputTwo.setSize(100,20);
        nameInputTwo.setLocation(170,152);
        this.add(nameInputTwo);

        startButton = new JButton("Start game");
        startButton.setSize(220,40);
        startButton.setLocation(50,250);
        setupStartButtonActionListener();
        this.add(startButton);
    }

    public void setupStartButtonActionListener() {
        startButton.addActionListener(e -> {
            this.whitePlayer = nameInputOne.getText();
            this.blackPLayer = nameInputTwo.getText();

            if (menuPanel.getGamemodeGroup().getSelection() == null) {
                JOptionPane.showMessageDialog(null, "Please select game mode before continuing!");
            } else if (whitePlayer.equals("") || blackPLayer.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter your name(s) before continuing!");
            } else {
                checkWhichGameModeIsSelected();
                menuPanel.getMainFrame().startGame(whitePlayer, blackPLayer, gameMode, gameModeTime);
            }
        });
    }

    public void setUpTutorialButton() {
        JLabel tutorialLabel = new JLabel("New to chess?");
        tutorialLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        tutorialLabel.setSize(200,200);
        tutorialLabel.setLocation(50, 370);

        JButton tutorialButton = new JButton("Take me to the tutorial!");
        tutorialButton.setSize(220,40);
        tutorialButton.setLocation(50, 500);

        tutorialButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www.chess.com/learn-how-to-play-chess").toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.add(tutorialButton);
        this.add(tutorialLabel);
    }

    public void checkWhichGameModeIsSelected() {
        String selectedMode = "";

        for (Enumeration<AbstractButton> buttons = menuPanel.getGamemodeGroup().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()){
                selectedMode = button.getText();
            }
        }

        switch (selectedMode) {
            case "Classic" -> {
                this.gameMode = "20:00";
                this.gameModeTime = 1200;
            }
            case "Rapid" -> {
                this.gameMode = "10:00";
                this.gameModeTime = 600;
            }
            case "Bullet" -> {
                this.gameMode = "01:00";
                this.gameModeTime = 60;
            }
            case "Extreme" -> {
                this.gameMode = "30:00";
                this.gameModeTime = 1800;
            }
        }

    }

    public JTextArea getNameInputTwo() {
        return nameInputTwo;
    }
}
