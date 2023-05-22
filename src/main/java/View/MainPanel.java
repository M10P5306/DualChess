package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds all the separate panels, placing all of them in one panel.
 * @author Hugo Andersson
 */
public class MainPanel extends JPanel {

    /**
     * The MainFrame that holds the instance of this class.
     */
    private MainFrame mainFrame;

    /**
     * The Center Panel which this class instantiates and adds to its panel.
     */
    private CenterPanel centerPanel;

    /**
     * The East Panel which this class instantiates and adds to its panel.
     */
    private EastPanel eastPanel;

    /**
     *  The South Panel which this class instantiates and adds to its panel.
     */
    private SouthPanel southPanel;

    /**
     * The controller who enables communication between view and model is stored as an instance variable.
     */
    private Controller controller;

    /**
     * The constructor receives information relevant to display for the users and sets up the panels accordingly.
     * @param mainFrame The Main Frame which instantiates this class.
     * @param whitePlayer The name of the user who plays with white pieces.
     * @param blackPlayer The name of the user who plays with black pieces.
     * @param gameMode The name of the game mode selected by user.
     * @param gameModeTime The time selected by user.
     * @param controller The controller who enables for communication between view and model.
     */
    public MainPanel(MainFrame mainFrame, String whitePlayer, String blackPlayer, String gameMode, int gameModeTime, Controller controller) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.setLayout(new BorderLayout());

        setupPanels(whitePlayer, blackPlayer, gameMode, gameModeTime);
        addPanels();
    }

    /**
     * This method sets up all the panels belonging to this class. The names and game mode are sent to
     * east panel to be displayed.
     * @param whitePlayer The name of the user who plays with white pieces.
     * @param blackPlayer The name of the user who plays with black pieces.
     * @param gameMode The name of the game mode.
     * @param gameModeTime The chosen time which will control the timers in east panel.
     */
    private void setupPanels(String whitePlayer, String blackPlayer, String gameMode, int gameModeTime) {
        centerPanel = new CenterPanel(this);
        eastPanel = new EastPanel(whitePlayer, blackPlayer, gameMode, gameModeTime, controller);
        southPanel = new SouthPanel(this);
    }

    /**
     * This method adds all the panels to Main Panel.
     */
    private void addPanels() {
        this.add(new NorthPanel(), BorderLayout.NORTH);
        this.add(new WestPanel(), BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This method returns south panel and is used by controller to display information in south panels
     * text area.
     * @return the text area to display information.
     */
    public SouthPanel getSouthPanel(){
        return southPanel;
    }

    /**
     * This method is used by center panel and south panel to enable communication with controller.
     * @return
     */
    public MainFrame getMainFrame(){
        return mainFrame;
    }

    /**
     * This method is used by controller to be able to manipulate the buttons in center panel.
     * @return the instance of centerpanel so Controller can communicate with it.
     */
    public CenterPanel getCenterPanel(){
        return centerPanel;
    }

    /**
     * This method is used by controller to set which players turn it is.
     * @return east panel so controller can communicate which players turn it is.
     */
    public EastPanel getEastPanel(){
        return eastPanel;
    }

}

