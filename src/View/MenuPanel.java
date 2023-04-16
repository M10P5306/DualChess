package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuPanel extends JPanel {
    private Controller controller;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;

    public MenuPanel(Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        setupPanels();
        addPanels();
    }

    public void setupPanels() {
        northPanel();
        centerPanel();
        eastPanel();
        westPanel();
        southPanel();
    }

    public void northPanel() {
        this.northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JLabel("DualChess"));
    }

    public void centerPanel() {
        JLabel nameLabelOne;
        JLabel nameLabelTwo;
        JTextPane nameInputOne;
        JTextPane nameInputTwo;
        JRadioButton nbrOfPlayers;
        JButton startButton;

        this.centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder("ENTER PLAYER NAME/NAMES"));
        centerPanel.setLayout(null);

        nameLabelOne = new JLabel("Player 1 name: ");
        nameLabelOne.setSize(300,20);
        nameLabelOne.setLocation(50,50);
        centerPanel.add(nameLabelOne);

        nameLabelTwo = new JLabel("Player 2 name: ");
        nameLabelTwo.setSize(300,20);
        nameLabelTwo.setLocation(50, 100);
        //nameLabelTwo.setVisible(false); TODO ta bort kommentar
        centerPanel.add(nameLabelTwo);

        nameInputOne = new JTextPane();
        nameInputOne.setSize(100,20);
        nameInputOne.setLocation(150,52);
        centerPanel.add(nameInputOne);

        nameInputTwo = new JTextPane();
        nameInputTwo.setSize(100,20);
        nameInputTwo.setLocation(150,102);
        centerPanel.add(nameInputTwo);

        startButton = new JButton("Start game");
        startButton.setSize(100,40);
        startButton.setLocation(150,150);
        startButton.addActionListener(e -> {
            controller.startGame();
        });
        centerPanel.add(startButton);

    }

    public void eastPanel() {
        this.eastPanel = new JPanel();
        eastPanel.setBorder(BorderFactory.createTitledBorder("CHOOSE GAME MODE"));

        JRadioButton classicAlternative = new JRadioButton("Classic");
        JRadioButton rapidAlternative = new JRadioButton("Rapid");
        JRadioButton bulletAlternative = new JRadioButton("Bullet");
        JRadioButton extremeAlternative = new JRadioButton("Extreme");
        ButtonGroup gameModeGroup = new ButtonGroup();
        gameModeGroup.add(classicAlternative);
        gameModeGroup.add(rapidAlternative);
        gameModeGroup.add(bulletAlternative);
        gameModeGroup.add(extremeAlternative);

        eastPanel.add(classicAlternative);
        eastPanel.add(rapidAlternative);
        eastPanel.add(bulletAlternative);
        eastPanel.add(extremeAlternative);
    }

    public void westPanel() {
        this.westPanel = new JPanel();
        westPanel.setBorder(BorderFactory.createTitledBorder("CHOOSE NUMBER OF PLAYERS"));

        JRadioButton onePlayerALternative = new JRadioButton("One player");
        onePlayerALternative.setSelected(true);
        JRadioButton twoPlayerALternative = new JRadioButton("Two players");
        ButtonGroup nbrOfPLayersGroup = new ButtonGroup();
        nbrOfPLayersGroup.add(onePlayerALternative);
        nbrOfPLayersGroup.add(twoPlayerALternative);
        //JRadioButton onePlayerALternative = new JRadioButton("One player");

        westPanel.add(onePlayerALternative);
        westPanel.add(twoPlayerALternative);

    }

    public void southPanel() {
        this.southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createTitledBorder("HOW TO PLAY"));
        JButton tutorialButton = new JButton("How to play chess");
        tutorialButton.setSize(100,30);
        tutorialButton.addActionListener(e -> {
            //TODO koden nedan ska ske i en entity-klass, här endast för test
            try {
                Desktop.getDesktop().browse(new URL("https://www.chess.com/learn-how-to-play-chess").toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        southPanel.add(tutorialButton);
    }

    public void addPanels() {
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

}
