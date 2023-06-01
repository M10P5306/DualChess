package ExtremeMode.Model;

import Model.Coordinate;
import Model.Square;

import javax.swing.*;
import java.util.Random;

public class Box {
    private ImageIcon icon;

    public Box() {
        this.icon = new ImageIcon("src/Icons/Box.png");
    }

    public int generateBox() {
        Random random = new Random();
        return random.nextInt(3)+1;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
