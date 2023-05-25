package ExtremeMode.Model;

import ExtremeMode.Controller.ExtremeController;
import Model.Coordinate;
import Model.Knight;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExtremeBoard {
    private SquareExtreme[][] squares;
    private ExtremeRuleHandler ruleHandler;
    private PieceExtreme lastMovedPiece;

    private List<Box> boxes = new ArrayList<Box>();

    public ExtremeBoard() {
        this.squares = new SquareExtreme[8][8];
        this.ruleHandler = new ExtremeRuleHandler(this);
        setupSquares();
        setupPieces();
        setupBoxes();
    }

    private void setupSquares() {
        for (int y = squares.length - 1; y >= 0; y--) {
            for (int x = 0; x < squares[y].length; x++) {
                squares[x][y] = new SquareExtreme();
            }
        }
    }

    private void setupPieces() {
        for (int x = 0; x < squares.length; x++) {
            squares[x][1].setPiece(new WhitePawnExtreme());
            squares[x][6].setPiece(new BlackPawnExtreme());
        }

        squares[0][0].setPiece(new RookExtreme("White"));
        squares[1][0].setPiece(new KnightExtreme("White"));
        squares[2][0].setPiece(new BishopExtreme("White"));
        squares[3][0].setPiece(new QueenExtreme("White"));
        squares[4][0].setPiece(new KingExtreme("White"));
        squares[5][0].setPiece(new BishopExtreme("White"));
        squares[6][0].setPiece(new KnightExtreme("White"));
        squares[7][0].setPiece(new RookExtreme("White"));

        squares[0][7].setPiece(new RookExtreme("Black"));
        squares[1][7].setPiece(new KnightExtreme("Black"));
        squares[2][7].setPiece(new BishopExtreme("Black"));
        squares[3][7].setPiece(new QueenExtreme("Black"));
        squares[4][7].setPiece(new KingExtreme("Black"));
        squares[5][7].setPiece(new BishopExtreme("Black"));
        squares[6][7].setPiece(new KnightExtreme("Black"));
        squares[7][7].setPiece(new RookExtreme("Black"));
    }

    private void setupBoxes(){
        Random random = new Random();
        for (int i = 0; i < 4 ; i++) {
            int x = random.nextInt(8);
            int y = random.nextInt(3,6);
            Box box = new Box();
            squares[x][y].setBox(box);
            boxes.add(box);
        }
    }
    public ArrayList<Coordinate> getValidMoves(Coordinate coordinate, ArrayList<Coordinate> opponentsMoves) {
        PieceExtreme selectedPiece = getSpecificSquare(coordinate).getPiece();
        ArrayList<Coordinate> validMoves;

        if (selectedPiece instanceof WhitePawnExtreme) {
            validMoves = ruleHandler.pawnValidMoves(coordinate);
        } else if (selectedPiece instanceof BlackPawnExtreme) {
            validMoves = ruleHandler.pawnValidMoves(coordinate);
        }else if (selectedPiece instanceof KingExtreme) {
            validMoves = ruleHandler.kingValidMoves(coordinate, opponentsMoves);
        } else {
            validMoves = ruleHandler.knightValidMoves(coordinate);
        }
        return validMoves;
    }

    public SquareExtreme[][] getSquares() {
        return squares;
    }

    public SquareExtreme getSpecificSquare(int x, int y) {
        return squares[x][y];
    }
    public SquareExtreme getSpecificSquare(Coordinate coordinate) {
        return squares[coordinate.getX()][coordinate.getY()];
    }
    public PieceExtreme getLastMovedPiece() {
        return lastMovedPiece;
    }
    public void setLastMovedPiece(PieceExtreme lastMovedPiece) {
        this.lastMovedPiece = lastMovedPiece;
    }

}
