package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private Controller controller;
    private BoardButton[][] buttons;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JTextPane jTextPane;
    private BoardButton selectedButton;

    private JScrollBar verticalScrollBar;

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
        this.southPanel = new JPanel(new BorderLayout());
        jTextPane = new JTextPane();
        jTextPane.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextPane);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        verticalScrollBar = jScrollPane.getVerticalScrollBar();

        jScrollPane.setPreferredSize(new Dimension(600,60));
        jScrollPane.setMaximumSize(new Dimension(600,60));

        JButton resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setSize(100,50);
        resetButton.setLocation(800,100); //trying to decide location ???
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetBoard();
            }
        });
        southPanel.add(jScrollPane,BorderLayout.CENTER);
        southPanel.add(resetButton, BorderLayout.EAST);

    }

    public void insertText(String text) {
        StyledDocument styleDocument = jTextPane.getStyledDocument();
        Style style = styleDocument.addStyle("Style", null);
        StyleConstants.setForeground(style, Color.BLACK);
        StyleConstants.setFontSize(style, 15);
        try {
            styleDocument.insertString(styleDocument.getLength(), text + "\n", style);
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

