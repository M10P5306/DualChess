package View;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel{

    public WestPanel() {
        this.setLayout(new GridLayout(8, 1));
        for (int i = 8; i > 0; i--) {
            JLabel label = new JLabel("" + i);
            this.add(label);
        }
    }
}
