package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private Controller controller;
    private BoardButton[][] buttons;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;

    private BoardButton selectedButton;

    public MainPanel(Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        setupPanels();
        addPanels();
    }

    public BoardButton[][] getButtons() {
        return buttons;
    }

    private void addPanels() {
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void setupPanels() {
        northPanel();
        westPanel();
        centerPanel();
        eastPanel();
        southPanel();
    }

    private void northPanel() {
        this.northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 8));
        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Character.toString((char) 'A' + i));
            northPanel.add(label);
        }
    }

    private void westPanel() {
        this.westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(8, 1));
        for (int i = 8; i > 0; i--) {
            JLabel label = new JLabel("" + i);
            westPanel.add(label);
        }
    }

    private void centerPanel() {

        this.buttons = new BoardButton[8][8];

        this.centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(8, 8));

        for (int x = buttons.length - 1; x >= 0; x--) {
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y] = new BoardButton(x, y);
                buttons[x][y].setText(x + "," + y);

                final int f_x = x;
                final int f_y = y;

                buttons[x][y].addActionListener(e -> {

                    if (selectedButton != null) {
                        if (buttons[f_x][f_y] == selectedButton) {
                            selectedButton.restoreDefaultColor();
                            selectedButton = null;
                        }
                        else {
                            controller.movePiece(f_x, f_y);
                            selectedButton.restoreDefaultColor();
                            selectedButton = null;
                        }
                    }
                    else { if(controller.boardButtonSelected(f_x, f_y)) {
                        selectedButton = buttons[f_x][f_y];
                        selectedButton.setBackground(Color.ORANGE);
                        }
                    }

                });

                centerPanel.add(buttons[x][y]);
            }
        }

    }

    private void eastPanel() {
        JPanel eastPanel = new JPanel();
        this.eastPanel = eastPanel;
        JLabel label = new JLabel("Hej hej hej hej");
        eastPanel.add(label);
    }

    private void southPanel() {
        JPanel southPanel = new JPanel();
        this.southPanel = southPanel;
        JTextPane jTextPane = new JTextPane();
        jTextPane.setEditable(false);
        jTextPane.setText("hej\nhej\nhej\nhej");
        southPanel.add(jTextPane);
    }

}

