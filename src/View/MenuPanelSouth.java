package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

public class MenuPanelSouth extends JPanel {
    public MenuPanelSouth() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.gray);
        //this.setBorder(BorderFactory.createTitledBorder("TUTORIAL"));
        addComponents();
    }

    public void addComponents() {
        JLabel tutorialLabel = new JLabel("Are you new to chess?");
        tutorialLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        tutorialLabel.setSize(200,200);
        tutorialLabel.setLocation(50, 370);

        JButton tutorialButton = new JButton("Take me to the tutorial!");
        tutorialButton.setSize(220,40);
        tutorialButton.setLocation(50, 500);
        //tutorialButton.setBackground(new Color(153,204,204));

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
