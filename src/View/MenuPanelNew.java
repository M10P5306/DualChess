package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanelNew extends JPanel {
    private MainFrame mainFrame;

    public MenuPanelNew(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        
        setupPanels();
        addPanels();
    }

    private void setupPanels() {
    }

    private void addPanels() {
        this.add(new MenuPanelNorth(), BorderLayout.NORTH);
        this.add(new MenuPanelEastAndWest(), BorderLayout.WEST);
        this.add(new MenuPanelEastAndWest(), BorderLayout.EAST);
        this.add(new MenuPanelSouth(), BorderLayout.SOUTH);
        this.add(new MenuPanelCenterNew(this), BorderLayout.CENTER);
    }


}
