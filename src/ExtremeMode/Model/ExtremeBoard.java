package ExtremeMode.Model;

import ExtremeMode.Controller.ExtremeController;
import Model.Knight;
import Model.Square;

public class ExtremeBoard {
    private SquareExtreme[][] squares;
    //private RuleHandler ruleHandler;
    private PieceExtreme lastMovedPiece;

    public ExtremeBoard() {
        this.squares = new SquareExtreme[8][8];
        //initiera egen rulehandler

        setupSquares();
        setupPieces();
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

    public SquareExtreme[][] getSquares() {
        return squares;
    }

}
