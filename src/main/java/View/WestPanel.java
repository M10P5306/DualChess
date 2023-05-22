package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds the numbers used for the coordination system of the graphical board.
 * @author Hugo Andersson, Edin Jahic, Adel Mohammed Abo Khamis and Mikael Nilsson.
 */
public class WestPanel extends JPanel{

    /**
     * The constructor sets the proporties of the panel and adds the numbers to it.
     */
    public WestPanel() {
        this.setLayout(new GridLayout(8, 1));

        for (int i = 8; i > 0; i--) {
            JLabel label = new JLabel("" + i);
            this.add(label);
        }
    }

}
