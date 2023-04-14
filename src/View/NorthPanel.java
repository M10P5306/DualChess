package View;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel{

    public NorthPanel() {
        this.setLayout(new GridLayout(1, 8));
        this.setPreferredSize(new Dimension(600,15));
        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Character.toString('A' + i), SwingConstants.CENTER);
            this.add(label);
        }
    }
}
