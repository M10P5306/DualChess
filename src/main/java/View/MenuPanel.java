package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private MenuFrame menuFrame;

    public MenuPanel(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        this.setLayout(new BorderLayout());

        addPanels();
    }

    private void addPanels() {
        this.add(new MenuPanelNorth(), BorderLayout.NORTH);
        this.add(new MenuSidePanel(), BorderLayout.WEST);
        this.add(new MenuSidePanel(), BorderLayout.EAST);
        this.add(new MenuPanelSouth(), BorderLayout.SOUTH);
        this.add(new MenuPanelCenter(this), BorderLayout.CENTER);
    }

    public MenuFrame getMenuFrame() {
        return menuFrame;
    }

}
