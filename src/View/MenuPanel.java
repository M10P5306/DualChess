package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private MainFrame mainFrame;
    private MenuPanelWest westPanel;
    private MenuPanelCenter centerPanel;
    private MenuPanelEast eastPanel;

    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());

        setupPanels();
        addPanels();
    }

    public void setupPanels() {
        this.centerPanel = new MenuPanelCenter(this);
        this.eastPanel = new MenuPanelEast(this);
        this.westPanel = new MenuPanelWest(this);
    }

    public void addPanels() {
        this.add(new MenuPanelNorth(), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public MenuPanelCenter getCenterPanel() {
        return centerPanel;
    }

    public ButtonGroup getGamemodeGroup() {
        return eastPanel.getGameModeGroup();
    }

}
