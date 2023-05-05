package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class MenuPanelCenterNew extends JPanel {
    private MenuPanelNew menuPanel;
    private JTextArea nameInputOne;
    private JTextArea nameInputTwo;
    private JButton startButton;
    private JLabel nameLabelTwo;
    private String whitePlayer;
    private String blackPLayer;
    private int gameModeTime;
    private String gameMode;

    public MenuPanelCenterNew(MenuPanelNew menuPanel) {
        this.menuPanel = menuPanel;
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        setUpPlayerSettings();
        setUpGameModeSettings();
        setUpHelp();
    }

    public void setUpPlayerSettings() {
        JLabel playerSettingsTitle = new JLabel("Enter player names below");
        playerSettingsTitle.setSize(500,50);
        playerSettingsTitle.setLocation(130,10);
        playerSettingsTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(playerSettingsTitle);

        JLabel nameLabelOne = new JLabel("Player 1 name (White): ");
        nameLabelOne.setSize(300,20);
        nameLabelOne.setLocation(150,60);
        this.add(nameLabelOne);

        nameLabelTwo = new JLabel("Player 2 name (Black): ");
        nameLabelTwo.setSize(300,20);
        nameLabelTwo.setLocation(150, 100);
        this.add(nameLabelTwo);

        nameInputOne = new JTextArea("Enter name");
        nameInputOne.setSize(100,20);
        nameInputOne.setLocation(300,60);

        nameInputTwo = new JTextArea("Enter name");
        nameInputTwo.setSize(100,20);
        nameInputTwo.setLocation(300,100);

        clearText();
        this.add(nameInputOne);
        this.add(nameInputTwo);

        startButton = new JButton("Start game");
        startButton.setSize(170,40);
        startButton.setLocation(190,500);
        //setupStartButtonActionListener();
        this.add(startButton);
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
        playerSettingsTitle.setSize(500,50);
        playerSettingsTitle.setLocation(130,150);
        playerSettingsTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        this.add(playerSettingsTitle);
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
        helpIconForMode.setLocation(430, 165);
        helpIconForMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpIconForMode.setToolTipText("<html> GAME MODE EXPLANATION: <br> Classic - 20 minute game <br> Rapid - 10 minute game <br> Bullet - 1 min game <br> Extreme - Custom game mode </html>");
            }
        });
        this.add(helpIconForMode);
    }


}
