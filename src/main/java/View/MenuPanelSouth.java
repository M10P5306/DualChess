package View;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * This class is used in the MenuPanel in order to create the panel which is to be added to the
 * Borderlayout.SOUTH in the MenuPanels layout.
 * @author Edin Jahic.
 */
public class MenuPanelSouth extends JPanel {
    /**
     * The constructor which sets the layout and the background color. It then calls a method which
     * adds components to the panel.
     */
    public MenuPanelSouth() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.gray);
        addComponents();
    }

    /**
     * A method which adds all the necessary components to the class. It adds a label and a
     * turtorial button, which takes the user to a website explaining chess rules.
     */
    public void addComponents() {
        JLabel tutorialLabel = new JLabel("Are you new to chess?");
        tutorialLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        tutorialLabel.setSize(200, 200);
        tutorialLabel.setLocation(50, 370);

        JButton tutorialButton = new JButton("Take me to the tutorial!");
        tutorialButton.setSize(220, 40);
        tutorialButton.setLocation(50, 500);

        tutorialButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www.chess.com/learn-how-to-play-chess").toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.add(tutorialLabel);
        this.add(tutorialButton);
    }

}
