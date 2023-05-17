package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class MenuPanelCenter extends JPanel {
    private MenuPanel menuPanel;
    private JTextArea nameInputOne;
    private JTextArea nameInputTwo;
    private ButtonGroup gameModeGroup;
    private ButtonGroup dimensionGroup;
    private ButtonGroup soundGroup;
    private JRadioButton soundEffectsYes;
    private JRadioButton soundEffectsNo;
    private int gameModeTime;
    private String gameMode;
    private JRadioButton threeDRadiobutton;
    private JRadioButton twoDRadioButton;

    public MenuPanelCenter(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        setUpPlayerSettings();
        setUpGameModeSettings();
        setupBoardSettings();
        setupStartButton();
        setUpHelp();
    }

    public void setUpPlayerSettings() {
        JLabel playerSettingsTitle = new JLabel("Enter player names below");
        playerSettingsTitle.setSize(500, 50);
        playerSettingsTitle.setLocation(130, 10);
        playerSettingsTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(playerSettingsTitle);

        JLabel nameLabelOne = new JLabel("Player 1 name (White): ");
        nameLabelOne.setSize(300, 20);
        nameLabelOne.setLocation(150, 60);
        this.add(nameLabelOne);

        JLabel nameLabelTwo = new JLabel("Player 2 name (Black): ");
        nameLabelTwo.setSize(300, 20);
        nameLabelTwo.setLocation(150, 100);
        this.add(nameLabelTwo);

        nameInputOne = new JTextArea("Enter name");
        nameInputOne.setSize(100, 20);
        nameInputOne.setLocation(300, 60);

        nameInputTwo = new JTextArea("Enter name");
        nameInputTwo.setSize(100, 20);
        nameInputTwo.setLocation(300, 100);

        clearText();
        this.add(nameInputOne);
        this.add(nameInputTwo);
    }

    public void clearText() {
        nameInputOne.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameInputOne.getText().equals("Enter name")) {
                    nameInputOne.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameInputOne.getText().equals("")) {
                    nameInputOne.setText("Enter name");
                }
            }

        });

        nameInputTwo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameInputTwo.getText().equals("Enter name")) {
                    nameInputTwo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameInputTwo.getText().equals("")) {
                    nameInputTwo.setText("Enter name");
                }
            }

        });
    }

    private void setUpGameModeSettings() {
        JLabel playerSettingsTitle = new JLabel("Choose game mode below");
        playerSettingsTitle.setSize(500, 50);
        playerSettingsTitle.setLocation(130, 130);
        playerSettingsTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(playerSettingsTitle);

        JRadioButton classicAlternative = new JRadioButton("Classic");
        classicAlternative.setBackground(Color.lightGray);
        classicAlternative.setSize(100, 20);
        classicAlternative.setLocation(150, 180);
        classicAlternative.setSelected(true);

        JRadioButton rapidAlternative = new JRadioButton("Rapid");
        rapidAlternative.setBackground(Color.lightGray);
        rapidAlternative.setSize(70, 20);
        rapidAlternative.setLocation(150, 200);

        JRadioButton bulletAlternative = new JRadioButton("Bullet");
        bulletAlternative.setBackground(Color.lightGray);
        bulletAlternative.setSize(70, 20);
        bulletAlternative.setLocation(150, 220);

        JRadioButton extremeAlternative = new JRadioButton("Extreme");
        extremeAlternative.setBackground(Color.lightGray);
        extremeAlternative.setSize(90, 20);
        extremeAlternative.setLocation(150, 240);

        gameModeGroup = new ButtonGroup();
        gameModeGroup.add(classicAlternative);
        gameModeGroup.add(rapidAlternative);
        gameModeGroup.add(bulletAlternative);
        gameModeGroup.add(extremeAlternative);

        this.add(classicAlternative);
        this.add(rapidAlternative);
        this.add(bulletAlternative);
        this.add(extremeAlternative);
    }

    public void setupBoardSettings() {
        JLabel boardSetting = new JLabel("Choose 2D or 3D board below");
        boardSetting.setSize(500, 50);
        boardSetting.setLocation(115, 260);
        boardSetting.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(boardSetting);

        twoDRadioButton = new JRadioButton("2D Board");
        twoDRadioButton.setBackground(Color.lightGray);
        twoDRadioButton.setSize(90, 20);
        twoDRadioButton.setLocation(150, 310);
        twoDRadioButton.setSelected(true);

        threeDRadiobutton = new JRadioButton("3D Board");
        threeDRadiobutton.setBackground(Color.lightGray);
        threeDRadiobutton.setSize(90, 20);
        threeDRadiobutton.setLocation(150, 330);

        dimensionGroup = new ButtonGroup();
        dimensionGroup.add(twoDRadioButton);
        dimensionGroup.add(threeDRadiobutton);

        this.add(twoDRadioButton);
        this.add(threeDRadiobutton);
    }

    public void setUpHelp() {
        ImageIcon helpIcon = new ImageIcon("src/MenuIcons/help_icon.png");

        JLabel helpIconForPlayers = new JLabel(helpIcon);
        helpIconForPlayers.setSize(24, 24);
        helpIconForPlayers.setLocation(430, 23);
        helpIconForPlayers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpIconForPlayers.setToolTipText("<html> Player 1 will play with the white pieces, and therefore make the opening move.<br> Player 2 will play with the black pieces. </html>");
            }
        });
        this.add(helpIconForPlayers);

        JLabel helpIconForMode = new JLabel(helpIcon);
        helpIconForMode.setSize(24, 24);
        helpIconForMode.setLocation(430, 144);
        helpIconForMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpIconForMode.setToolTipText("<html> GAME MODE EXPLANATION: <br> Classic - 20 minute game <br> Rapid - 10 minute game <br> Bullet - 1 minute game <br> Extreme - Custom game mode </html>");
            }
        });
        this.add(helpIconForMode);

        JLabel helpIconForBoard = new JLabel(helpIcon);
        helpIconForBoard.setSize(24, 24);
        helpIconForBoard.setLocation(455, 275);
        helpIconForBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpIconForBoard.setToolTipText("<html> Do you wish to play the game in 3D or 2D? </html>");
            }
        });
        this.add(helpIconForBoard);
    }

    public void setupStartButton() {
        JLabel soundEffect = new JLabel("Choose sound effects below");
        soundEffect.setSize(500, 50);
        soundEffect.setLocation(130, 350);
        soundEffect.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(soundEffect);

        soundEffectsYes = new JRadioButton("With sound effects");
        soundEffectsYes.setBackground(Color.lightGray);
        soundEffectsYes.setSize(150, 20);
        soundEffectsYes.setLocation(150, 390);
        soundEffectsYes.setSelected(true);
        this.add(soundEffectsYes);

        soundEffectsNo = new JRadioButton("Without sound effects");
        soundEffectsNo.setBackground(Color.lightGray);
        soundEffectsNo.setSize(150, 20);
        soundEffectsNo.setLocation(150, 410);
        this.add(soundEffectsNo);

        soundGroup = new ButtonGroup();
        soundGroup.add(soundEffectsYes);
        soundGroup.add(soundEffectsNo);

        JButton startButton = new JButton("Start game");
        startButton.setSize(170, 40);
        startButton.setLocation(190, 470);
        startButton.addActionListener(e -> {
            checkInput();
        });
        this.add(startButton);
    }

    public void checkInput() {
        if (nameInputOne.getText().equals("Enter name") || nameInputOne.getText().equals("") || nameInputTwo.getText().equals("Enter name") || nameInputTwo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter both player names before continuing!");
        } else if (gameModeGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please select game mode before continuing!");
        } else if (dimensionGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please select 2D or 3D before continuing!");
        } else if (nameInputOne.getText().equals(nameInputTwo.getText())) {
            JOptionPane.showMessageDialog(null, "Please enter two different names!");
        } else if (soundGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please select if you want sound effects or not");
        } else {
            int reply = JOptionPane.showConfirmDialog(null, "You will not be able to edit the choices later. \nDo you wish to start the game?", "Confirm start", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION && soundEffectsYes.isSelected() && twoDRadioButton.isSelected()) {
                String whitePlayerName = nameInputOne.getText();
                String blackPLayerName = nameInputTwo.getText();
                checkWhichGameModeIsSelected();
                menuPanel.getMenuFrame().startGame(whitePlayerName, blackPLayerName, gameMode, gameModeTime);
            } else if (reply == JOptionPane.YES_OPTION && soundEffectsNo.isSelected() && twoDRadioButton.isSelected()){
                String whitePlayerName = nameInputOne.getText();
                String blackPlayerName = nameInputTwo.getText();
                checkWhichGameModeIsSelected();
                menuPanel.getMenuFrame().startGameWithoutSound(whitePlayerName, blackPlayerName, gameMode, gameModeTime);
            } else if(reply == JOptionPane.YES_OPTION && threeDRadiobutton.isSelected()){
                menuPanel.getMenuFrame().start3D();
            }
        }
    }

    public void checkWhichGameModeIsSelected() {
        String selectedMode = "";

        for (Enumeration<AbstractButton> buttons = gameModeGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
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

}
