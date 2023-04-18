package View;

import javax.swing.*;

public class MenuPanelWest extends JPanel {
    private MenuPanel menuPanel;
    private JRadioButton onePlayerALternative;
    private JRadioButton twoPlayerALternative;

    public MenuPanelWest(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setBorder(BorderFactory.createTitledBorder("NUMBER OF PLAYERS"));
        setUpNumberOfPLayers();
    }

    private void setUpNumberOfPLayers() {
        onePlayerALternative = new JRadioButton("One player");
        twoPlayerALternative = new JRadioButton("Two players");
        twoPlayerALternative.setSelected(true);
        ButtonGroup numberOfPLayersGroup = new ButtonGroup();

        onePlayerALternative.addActionListener(e -> {
            menuPanel.getCenterPanel().getNameInputTwo().setText("AI");
            menuPanel.getCenterPanel().getNameInputTwo().setEditable(false);
        });

        twoPlayerALternative.addActionListener(e -> {
            menuPanel.getCenterPanel().getNameInputTwo().setText("");
            menuPanel.getCenterPanel().getNameInputTwo().setEditable(true);
        });

        numberOfPLayersGroup.add(onePlayerALternative);
        numberOfPLayersGroup.add(twoPlayerALternative);

        this.add(onePlayerALternative);
        this.add(twoPlayerALternative);
    }
}
