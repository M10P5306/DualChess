package View;

import Model.Coordinate;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
    private MainPanel mainPanel;
    private BoardButton[][] buttons;
    private BoardButton selectedButton;

    public CenterPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.buttons = new BoardButton[8][8];
        this.setLayout(new GridLayout(8, 8));

        setUpButtons();
    }

    private void setUpButtons() {
        for (int y = buttons.length - 1; y >= 0; y--) {
            for (int x = 0; x < buttons[y].length; x++) {
                buttons[x][y] = new BoardButton(x, y);
                buttons[x][y].setText(x + "," + y);
                final int f_x = x;
                final int f_y = y;
                buttons[x][y].addActionListener(e -> {
                    if (selectedButton != null) {
                        if (buttons[f_x][f_y] != selectedButton) {
                            mainPanel.getMainFrame().getController().movePiece(f_x, f_y);
                        }
                        selectedButton = null;
                        restoreDefaultColors();
                    } else {
                        if (mainPanel.getMainFrame().getController().boardButtonSelected(f_x, f_y)) {
                            selectedButton = buttons[f_x][f_y];
                            selectedButton.setBackground(Color.ORANGE);
                        }
                    }
                });
                this.add(buttons[x][y]);
            }
        }
    }

    public BoardButton[][] getButtons() {
        return buttons;
    }

    public void setValidMoves(ArrayList<Coordinate> validMoves) {
        for (Coordinate coordinate : validMoves) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            buttons[x][y].setBackground(Color.yellow);
        }
    }

    public void restoreDefaultColors() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y].restoreDefaultColor();
            }
        }
    }

}
