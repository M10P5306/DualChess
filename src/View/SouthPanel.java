package View;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class SouthPanel extends JPanel {

    private MainPanel mainPanel;
    private JTextPane jTextPane;
    private JScrollBar verticalScrollBar;

    public SouthPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.setLayout(new BorderLayout());

        setUp();
    }

    private void setUp() {
        jTextPane = new JTextPane();
        jTextPane.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextPane);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        verticalScrollBar = jScrollPane.getVerticalScrollBar();

        jScrollPane.setPreferredSize(new Dimension(600, 70));
        jScrollPane.setMaximumSize(new Dimension(600, 70));

        JButton resetButton = new JButton();
        resetButton.setText("Forfeit");
        resetButton.setSize(100, 60);
        resetButton.addActionListener(e -> {
            mainPanel.getMainFrame().getController().resetBoard();
        });
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(resetButton, BorderLayout.EAST);
    }

    public void insertText(String text) {
        StyledDocument styleDocument = jTextPane.getStyledDocument();
        Style style = styleDocument.addStyle("Style", null);
        StyleConstants.setForeground(style, Color.BLACK);
        StyleConstants.setFontSize(style, 20);
        try {
            styleDocument.insertString(styleDocument.getLength(), text + "\n", style);
            verticalScrollBar.setValue(5);
            verticalScrollBar.setLocation(5, 5);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JTextPane getJTextPane() {
        return jTextPane;
    }

}