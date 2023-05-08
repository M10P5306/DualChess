package ExtremeMode.Model;

import Model.Coordinate;
import Model.Square;
import java.util.Random;

public class Box {
    private int counter;
    private Square square;
    private Random random = new Random();
    public Box() {

    }

    public void spawnKnight() {
         //square.setPiece();
    }

    public void spawnBomb() {

    }

    public void increaseHealth() {

    }

    public void generateBox() {
        counter = random.nextInt(3 - 1 + 1) + 1;
        switch (counter) {
            case 1 -> spawnKnight();
            case 2 -> spawnBomb();
            case 3 -> increaseHealth();
        }
    }

}
