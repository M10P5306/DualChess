package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds the letters used for the coordination system of the graphical board.
 * @author Hugo Andersson, Edin Jahic, Adel Mohammed Abo Khamis and Mikael Nilsson.
 */

public class NorthPanel extends JPanel{

    /**
     * The constructor sets the proporties of the panel and adds the letters.
     */
    public NorthPanel() {
        this.setLayout(new GridLayout(1, 8));
        this.setPreferredSize(new Dimension(600,15));

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Character.toString('A' + i), SwingConstants.CENTER);
            this.add(new JLabel());
            this.add(label);
            this.add(new JLabel());
        }
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
    }
}