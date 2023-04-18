package View;

import javax.swing.*;

public class MenuPanelEast extends JPanel {
    private MenuPanel menuPanel;
    private JRadioButton classicAlternative;
    private JRadioButton rapidAlternative;
    private JRadioButton bulletAlternative;
    private JRadioButton extremeAlternative;
    private ButtonGroup gameModeGroup;

    public MenuPanelEast(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        //this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("GAME MODE"));
        setUp();
    }

    private void setUp() {
        classicAlternative = new JRadioButton("Classic");
        rapidAlternative = new JRadioButton("Rapid");
        bulletAlternative = new JRadioButton("Bullet");
        extremeAlternative = new JRadioButton("Extreme");

        gameModeGroup = new ButtonGroup();
        gameModeGroup.add(classicAlternative);
        gameModeGroup.add(rapidAlternative);
        gameModeGroup.add(bulletAlternative);
        gameModeGroup.add(extremeAlternative);

        this.add(classicAlternative);
        this.add(rapidAlternative);
        this.add(bulletAlternative);
        this.add(extremeAlternative);
    }

    public ButtonGroup getGameModeGroup() {
        return gameModeGroup;
    }
}
