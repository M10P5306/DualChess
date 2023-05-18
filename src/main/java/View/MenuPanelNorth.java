package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to create the panel which is then used in the
 * MenuPanel class (Borderlayout.NORTH) in order to show the name of the game and two chess icons.
 * @author Edin Jahic.
 */
public class MenuPanelNorth extends JPanel {
    /**
     * This is the constructor in the MenuPanelNorth class. It sets the layout to flow and
     * the background to gray. It also creates the necessary labels and image icons
     * and adds them to the panel.
     */
    public MenuPanelNorth() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.gray);
        JLabel dualChess = new JLabel("DualChess");
        dualChess.setFont(new Font("Verdana", Font.BOLD, 40));

        ImageIcon img1 = new ImageIcon("src/main/java/Icons/BlackKing.png");
        ImageIcon img2 = new ImageIcon("src/main/java/Icons/WhiteQueen.png");
        JLabel icon1 = new JLabel(img1);
        JLabel icon2 = new JLabel(img2);

        this.add(icon1);
        this.add(dualChess);
        this.add(icon2);
    }
}
