package Controller;

import Model.*;
import View.*;

public class Controller {

    private MainFrame mainFrame;

    private Board board;

    public Controller() {
        this.mainFrame = new MainFrame(this);
    }

    public void boardButtonSelected(int x, int y) {
        System.out.println("x: " + x + " and y: " + y);
    }





}
