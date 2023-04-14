package View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private MainFrame mainFrame;
    private CenterPanel centerPanel;
    private EastPanel eastPanel;
    private SouthPanel southPanel;

    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());
        setupPanels();
        addPanels();
    }

    private void setupPanels() {
        centerPanel = new CenterPanel(this);
        eastPanel = new EastPanel();
        southPanel = new SouthPanel(this);
    }

    private void addPanels() {
        this.add(new NorthPanel(), BorderLayout.NORTH);
        this.add(new WestPanel(), BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public SouthPanel getSouthPanel(){
        return southPanel;
    }

    public MainFrame getMainFrame(){
        return mainFrame;
    }

    public CenterPanel getCenterPanel(){
        return centerPanel;
    }

    public EastPanel getEastPanel(){
        return eastPanel;
    }
}

