package View;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel{

    public NorthPanel() {
        this.setLayout(new GridLayout(1, 8));
        this.setPreferredSize(new Dimension(600,15));

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Character.toString('A' + i), SwingConstants.CENTER);
            if(i == 7){
                label = new JLabel(Character.toString('A' + i), SwingConstants.RIGHT);
            }
            this.add(new JLabel());
            this.add(label);
            this.add(new JLabel());
        }
        this.add(new JLabel());
        this.add(new JLabel());
    }

}
