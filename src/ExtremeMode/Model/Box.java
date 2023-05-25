package ExtremeMode.Model;

import Model.Coordinate;
import Model.Square;
import java.util.Random;

public class Box {
    private int counter;
    private SquareExtreme square;
    private Random random = new Random();
    public Box() {
        generateBox();
    }

    public void spawnKnight() {
        // Must know who's turn is it to set the color of the knight.
       /*  PieceExtreme knight = new KnightExtreme("white");
         square.setPiece(knight);*/
    }

    public void spawnBomb() {
/*
        // Erase everything within one square of the bomb's location
        int x = square.getPosition().getX();
        int y = square.getPosition().getY();

        // Loop through the neighboring squares and remove the pieces
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isValidCoordinate(i, j)) {
                    Square neighborSquare = square.getBoard().getSquareAt(i, j);
                    neighborSquare.setPiece(null);
                }
            }
        }*/
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
