package View;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * This class represents the textarea used for communication and the reset button in the GUI.
 * @author Hugo Andersson
 */

public class SouthPanel extends JPanel {

    /**
     * This class belongs to a MainPanel, which instance is saved as a variable to enable communication.
     */
    private MainPanel mainPanel;

    /**
     * The JTextPane which displays information to users regarding moves and attacks.
     */
    private JTextPane jTextPane;

    /**
     * This enables scrolling the information displayed.
     */
    private JScrollBar verticalScrollBar;

    /**
     * The constructor receives the MainPanel this class will communicate through.
     * @param mainPanel the class which holds the instance of this class
     */
    public SouthPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.setLayout(new BorderLayout());

        setUp();
    }

    /**
     * This method sets up the properties of the textpane, scrollpane and reset button and adds them to the panel.
     * If a user presses the reset button a method in the controller class is called.
     */
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
            mainPanel.getMainFrame().getController().forfeit();
        });
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(resetButton, BorderLayout.EAST);
    }

    /**
     * This method is used by controller to display information about moves and attacks.
     * @param text the information that controller sends.
     */
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

    /**
     * This method is used by controller when a game is reset.
     * @return the textpane so controller can reset it.
     */
    public JTextPane getJTextPane() {
        return jTextPane;
    }

}