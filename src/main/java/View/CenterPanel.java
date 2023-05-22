package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the board in the 2D GUI and contains all the buttons that users click on.
 * @author Hugo Andersson, Edin Jahic, Adel Mohammed Abo Khamis and Mikael Nilsson.
 */

public class CenterPanel extends JPanel {
    /**
     * The CenterPanel belongs to a MainPanel which is stored as an instance variable to enable communication.
     */
    private MainPanel mainPanel;

    /**
     * The board as GUI is represented by a 2D-array of BoardButton.
     */
    private BoardButton[][] buttons;

    /**
     * To keep track of which button the user has marked, the selected button is stored as an instance variable.
     */
    private BoardButton selectedButton;

    /**
     * The constructor for this class receives the instance of MainPanel to which it belongs.
     * The button array is instantiated, GridLayout is set and the buttons get set up.
     * @param mainPanel this MainPanel is the class who instantiated CenterPanel.
     */
    public CenterPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.buttons = new BoardButton[8][8];
        this.setLayout(new GridLayout(8, 8));
        setUpButtons();
    }

    /**
     * This method instantiates every button and adds an actionlistener to them. If the user has not marked a button it is
     * selected, its color is set to orange and controller plays marking sound. The controller is also notified to display information
     * accordingly to the button selected. If a button already is selected the user can click a new position for it, or deselect it.
     * Lastly all buttons are added to the instance of this class.
     */
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
                            mainPanel.getMainFrame().getController().playMarkingSound(finalX, finalY);
                        }
                    }
                });
                this.add(buttons[x][y]);
            }
        }
    }

    /**
     * This method returns all buttons and is used by the Controller-class to set the icon of a piece on a button.
     * @return BoardButtons array to change icons.
     */
    public BoardButton[][] getButtons() {
        return buttons;
    }

    /**
     * This method is used by controller to display which positions a piece can be moved to.
     * @param x represents together with y, the coordinate where the background of a button should be changed.
     * @param y represents together with x, the coordinate where the background of a button should be changed.
     */
    public void setValidMove(int x, int y) {
        buttons[x][y].setBackground(Color.yellow);
    }

    /**
     * When a user has the possibility to make a special move (i.e. en-passant, rocked or promotion) this is displayed
     * by setting the position of the special move to green.
     * @param x represents together with y, the coordinate where the background of a button should be changed.
     * @param y represents together with x, the coordinate where the background of a button should be changed.
     */
    public void setSpecialMove(int x, int y) {
        buttons[x][y].setBackground(Color.green);
    }

    /**
     * When a user has the possibility to attack a piece, the position of that piece is displayed by changing the color
     * of the button to red.
     * @param x represents together with y, the coordinate where the background of a button should be changed.
     * @param y represents together with x, the coordinate where the background of a button should be changed.
     */
    public void setPossibleAttack(int x, int y) {
        buttons[x][y].setBackground(Color.red);
    }

    /**
     * After every round this method is called to make sure that all buttons are reset to white or black color.
     */
    public void restoreDefaultColors() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y].restoreDefaultColor();
            }
        }
    }

}
