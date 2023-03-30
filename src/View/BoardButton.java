package View;

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {

    private Color defaultColor;

    public BoardButton(int x , int y) {
        if ((x + y) % 2 == 0) {
            defaultColor = new Color(255,255,255);
        }
        else {
            defaultColor = new Color(100, 100, 100);
        }
        this.setBackground(defaultColor);
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    public void restoreDefaultColor() {
        this.setBackground(defaultColor);
    }

}
