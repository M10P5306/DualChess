package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used in the MenuFrame in order to display this panels contents on the frame (the main menu).
 * This panel creates all the necessary panels and adds them to its layout, creating a complete menu.
 *
 * @author Edin Jahic.
 */
public class MenuPanel extends JPanel {
    /**
     * instance variable containing the MenuFrame.
     */
    private MenuFrame menuFrame;

    /**
     * This is the constructor which assigns value to instance variable and sets the
     * layout for the panel. It also creates and adds all necessary panels to this panels'
     * layout in the method addPanels().
     *
     * @param menuFrame the menuFrame as a parameter.
     */
    public MenuPanel(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        this.setLayout(new BorderLayout());

        addPanels();
    }

    /**
     * This method is used to create and add all the panels into this MenuPanel and its layout.
     */
    private void addPanels() {
        this.add(new MenuPanelNorth(), BorderLayout.NORTH);
        this.add(new MenuSidePanel(), BorderLayout.WEST);
        this.add(new MenuSidePanel(), BorderLayout.EAST);
        this.add(new MenuPanelSouth(), BorderLayout.SOUTH);
        this.add(new MenuPanelCenter(this), BorderLayout.CENTER);
    }

    /**
     * A getter which returns the menuFrame (instance variable).
     * @return the menuFrame instance variable.
     */
    public MenuFrame getMenuFrame() {
        return menuFrame;
    }

}
