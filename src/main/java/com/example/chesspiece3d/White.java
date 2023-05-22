package com.example.chesspiece3d;

/**
 * This interface is used to be able to loop through all buttons and see which has a piece that implements the white
 * interface. The method changePlayersTurn is used to control which pieces should change color when hovering the mouse
 * over them.
 */
public interface White {
    void changePlayersTurn(boolean bool);
}
