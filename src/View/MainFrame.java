package View;

import Controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private MainPanel mainPanel;

    public MainFrame(Controller controller) {
        this.controller = controller;
        this.mainPanel = new MainPanel(controller);

        this.setTitle("DualChess");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

}