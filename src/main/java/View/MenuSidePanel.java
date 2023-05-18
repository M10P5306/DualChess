package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class' only purpose is to set the background color of the side panels to grey.
 * It is used in the MenuPanel class.
 * @author Edin Jahic.
 */
public class MenuSidePanel extends JPanel {
    /**
     * This is the constructor which sets the background color of the panel to grey, and creates a label
     * which is then added to the panel. The constructor is called by the MenuPanel class.
     */
    public MenuSidePanel() {
        this.setBackground(Color.gray);
        JLabel label = new JLabel("                ");
        this.add(label);
    }
}
