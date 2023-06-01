package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the graphical representation of a square on the board. By interacting with this button the Square class in model is modified.
 * @see Model.Square
 * @author Hugo Andersson, Edin Jahic, Adel Mohammed Abo Khamis and Mikael Nilsson.
 */

public class BoardButton extends JButton {

    /**
     * Color of the button used to reset it after a move has been made or a piece has been deselected.
     */
    private Color defaultColor;

    /**
     * Constructor where the default color of the button is decided.
     * @param x position of the button.
     * @param y position of the button.
     */
    public BoardButton(int x, int y) {
        if ((x + y) % 2 == 0) {
            defaultColor = new Color(100, 100, 100);
        } else {
            defaultColor = new Color(255, 255, 255);

        }

        this.setBackground(defaultColor);
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    /**
     * method for resetting the color of the button.
     */
    public void restoreDefaultColor() {
        this.setBackground(defaultColor);
    }

}
