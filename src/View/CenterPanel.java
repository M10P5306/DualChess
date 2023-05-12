package View;

import javax.swing.*;
import java.awt.*;

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
                final int finalX = x;
                final int finalY = y;
                buttons[x][y].addActionListener(e -> {
                    if (selectedButton != null) {
                        if (buttons[finalX][finalY] != selectedButton) {
                            mainPanel.getMainFrame().getController().movePiece(finalX, finalY);

                        }
                        selectedButton = null;
                        restoreDefaultColors();
                    } else {
                        if (mainPanel.getMainFrame().getController().boardButtonSelected(finalX, finalY)) {
                            selectedButton = buttons[finalX][finalY];
                            selectedButton.setBackground(Color.ORANGE);
                            mainPanel.getMainFrame().getController().playMarkingSound(finalX,finalY);
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

    public void setValidMoves(int x, int y) {
        buttons[x][y].setBackground(Color.yellow);
    }

    public void setSpecialMove(int x, int y) { buttons[x][y].setBackground(Color.green);}

    public void setPossibleAttack(int x, int y) {buttons[x][y].setBackground(Color.red);}

    public void restoreDefaultColors() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y].restoreDefaultColor();
            }
        }
    }
}
