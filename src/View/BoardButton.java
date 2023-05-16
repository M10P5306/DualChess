package View;

import javax.swing.*;
import java.awt.*;

/**
 * The BoardButton class represents a customized button used in the board of the chess game.
 * @author Adel Mohammed Abo Khamis.
 * @author Hugo Andersson.
 * @author Edin Jahic.
 * @author Mikael Nilsson.
 */
public class BoardButton extends JButton {

    private Color defaultColor;

    /**
     * Constructs a BoardButton with the specified coordinates on the chess board.
     * @param x the x-coordinate of the button on the chess board.
     * @param y the y-coordinate of the button on the chess board.
     */

    public BoardButton(int x, int y) {
        if ((x + y) % 2 == 0) {
            defaultColor = new Color(255, 255, 255);
        } else {
            defaultColor = new Color(100, 100, 100);
        }

        this.setBackground(defaultColor);
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    /**
     * Restores the default background color of the button.
     */
    public void restoreDefaultColor() {
        this.setBackground(defaultColor);
    }

}
