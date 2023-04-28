package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanelNorth extends JPanel {
    public MenuPanelNorth() {
        this.setLayout(new FlowLayout());
        JLabel dualChess = new JLabel("DualChess");
        dualChess.setFont(new Font("Verdana", Font.BOLD, 40));
        this.add(dualChess);
    }
}
