package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanelNorth extends JPanel {
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
