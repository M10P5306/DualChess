package View;

import Controller.Controller;
import ExtremeMode.Controller.ExtremeController;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private MainFrame mainFrame;
    private CenterPanel centerPanel;
    private EastPanel eastPanel;
    private SouthPanel southPanel;
    private Controller controller;
    private ExtremeController extremeController;

    public MainPanel(MainFrame mainFrame, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, Controller controller) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());

        setupPanels(whitePlayer, blackPlayer, gameMode, gameModeTime);
        addPanels();
    }

    public MainPanel(MainFrame mainFrame, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, ExtremeController extremeController) {
        this.extremeController = extremeController;
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());

        setupExtremeModePanels(whitePlayer, blackPlayer, 100, 100, gameMode, gameModeTime, extremeController);
        addPanels();
    }

    public void setupExtremeModePanels(String whitePlayer, String blackPlayer, int whitePlayerHealth, int blackPlayerHealth, String gameMode, int gameModeTime, ExtremeController extremeController) {
        centerPanel = new CenterPanel(this);
        eastPanel = new EastPanel(whitePlayer, blackPlayer, whitePlayerHealth, blackPlayerHealth, gameMode, gameModeTime, extremeController);
        southPanel = new SouthPanel(this);
    }

    private void setupPanels(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        centerPanel = new CenterPanel(this);
        eastPanel = new EastPanel(whitePlayer, blackPlayer, gameMode, gameModeTime, controller);
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

