package com.example.chesspiece3d;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller connected to the stage used in javafx, which makes up the 3D GUI in this application.
 * @author Hugo Andersson
 */
public class HelloController implements Initializable {

    /**
     * This is a grid pane loaded from an fxml-file, which holds all buttons.
     */
    @FXML
    private GridPane gridPane;

    /**
     * The anchor pane to which the grid pane is put on, and which makes up the scene.
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * This boolean is used to determine how the board should be displayed when user presses a key to change perspective.
     */
    private boolean flip = true;

    /**
     * This group is used to set what piece the user has selected.
     */
    private Group selectedPiece = null;

    /**
     * The array of buttons which makes up the board that the users see.
     */
    private Button[][] buttons = new Button[8][8];

    /**
     * The boolean which determines how the pieces should be placed on the board.
     */
    private boolean displayBlackPlayerView = true;

    /**
     * This boolean is used to keep track of when a user has made the special move promotion.
     */
    private boolean promoted = false;

    /**
     * This instance is used to set a new queen on the board when a user has made the special move promotion.
     */
    private Queen3D promotedPawn;

    /**
     * The board from model which contains all the pieces and their possible moves.
     */
    private Board board = new Board();

    /**
     * This button is used to keep track of which button the user has selected.
     */
    private Button selectedButton;

    /**
     * This controller enables for communication between model and this class, the controller for the GUI.
     */
    private ControllerFor3D controllerFor3D;

    /**
     * The class which loads the fxml file and contains the stage.
     */
    private HelloApplication helloApplication;

    /**
     * This method sets the stage for the 3D GUI by instantiating all relevant GUI components. The grid pane is moved and
     * rotated to create a nicer view of the board.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resource bundle containing locale-specific objects, or null if the resource bundle is not known.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int y = buttons.length - 1; y >= 0; y--) {
            for (int x = 0; x < buttons[y].length; x++) {
                buttons[x][y] = new Button();
                buttons[x][y].setPrefSize(70, 70);
                buttons[x][y].setAlignment(Pos.CENTER);
                if ((x + y) % 2 == 0) {
                    buttons[x][y].setStyle("-fx-background-color: black;");
                } else {
                    buttons[x][y].setStyle("-fx-background-color: white;");
                }
                int finalX = x;
                int finalY = y;
                buttons[x][y].setOnMouseClicked(event -> {
                    if (selectedButton != null) {
                        Coordinate coordinate = new Coordinate(finalX, finalY);
                        if (buttons[finalX][finalY] != selectedButton && controllerFor3D.getPossibleMoves().contains(coordinate)) {
                            if (controllerFor3D.movePiece(finalX, finalY)) {
                                if (buttons[finalX][finalY].getGraphic() != null) {
                                    buttons[finalX][finalY].setGraphic(null);
                                }
                                if (promoted) {
                                    selectedPiece = (Group) promotedPawn;
                                    promoted = false;
                                } else {
                                    selectedPiece = (Group) selectedButton.getGraphic();
                                }
                                selectedButton.setGraphic(null);
                                buttons[finalX][finalY].setGraphic(selectedPiece);
                            }
                        }
                            selectedButton = null;
                            restoreDefaultColors();
                    } else {
                        if (controllerFor3D.boardButtonSelected(finalX, finalY)) {
                            selectedButton = buttons[finalX][finalY];
                            selectedButton.setStyle("-fx-background-color: #FFA500;");
                        }
                    }
                });
            }
        }
        int j = 0;
        for (int i = 7; i >= 0; i--, j++) {
            for (int k = 0; k < 8; k++) {
                gridPane.add(buttons[k][i], k, j);
            }
        }

        Label eastLabel = new Label();
        eastLabel.setPrefSize(46, 550);
        eastLabel.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
        eastLabel.translateZProperty().set(46);
        GridPane.setColumnSpan(eastLabel, 1); // 1 column
        GridPane.setRowSpan(eastLabel, 8); // 9 rows
        eastLabel.setStyle("-fx-background-color: #000000;");
        gridPane.add(eastLabel, 8, 0);

        Label southLabel = new Label();
        southLabel.setPrefSize(550, 50);
        southLabel.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
        GridPane.setColumnSpan(southLabel, 8);
        GridPane.setRowSpan(southLabel, 1);
        southLabel.setStyle("-fx-background-color: #000000;");
        gridPane.add(southLabel, 0, 8);

        gridPane.translateXProperty().set(400); //400
        gridPane.translateYProperty().set(150); //200
        Rotate rotateY = new Rotate(12, Rotate.Y_AXIS); //10
        gridPane.getTransforms().add(rotateY);
        Rotate rotateX = new Rotate(-45, Rotate.X_AXIS); //55
        gridPane.getTransforms().add(rotateX);
        Rotate rotateZ = new Rotate(9, Rotate.Z_AXIS); //5
        gridPane.getTransforms().add(rotateZ);

        controllerFor3D = new ControllerFor3D(this, board);

        updateBoardView();
        anchorPane.setStyle("-fx-background-color: lavender;");

    }

    /**
     * If the user wants to change the perspective of the 3D board, this method is called which moves and rotates the
     * grid pane between two different modes. The placement of the pieces is also adjusted.
     */

    public void keyPressed() {
        if (flip) {
            Rotate rotateX = new Rotate(10, Rotate.X_AXIS); //10
            Rotate rotateY = new Rotate(-15, Rotate.Y_AXIS); //-15
            Rotate rotateZ = new Rotate(40, Rotate.Z_AXIS); //45
            gridPane.getTransforms().addAll(rotateZ, rotateX, rotateY);
            gridPane.translateXProperty().set(625); //600
            gridPane.translateYProperty().set(100); //150
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    Group piece = (Group) buttons[i][j].getGraphic();
                    if (piece != null) {
                        piece.translateXProperty().set(-5); //5
                        piece.translateYProperty().set(18); //18
                    }
                }
            }
            flip = false;
        } else {
            gridPane.getTransforms().clear();
            Rotate rotateY = new Rotate(12, Rotate.Y_AXIS); //10
            gridPane.getTransforms().add(rotateY);
            Rotate rotateX = new Rotate(-45, Rotate.X_AXIS); //55
            gridPane.getTransforms().add(rotateX);
            Rotate rotateZ = new Rotate(9, Rotate.Z_AXIS); //5
            gridPane.getTransforms().add(rotateZ);
            gridPane.translateXProperty().set(400); //400
            gridPane.translateYProperty().set(150); //200
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    Group piece = (Group) buttons[i][j].getGraphic();
                    if (piece != null){
                        piece.translateXProperty().set(-2); //5
                        piece.translateYProperty().set(10);
                    }
                }
            }
            flip = true;
        }
    }

    /**
     * If the user wants to change if the black players pieces should be displayed closest to the the user this method
     * is used. The user can turn it back and alter between these two modes.
     */
    public void changeToBlackPlayerView() {
        if (displayBlackPlayerView) {

            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    gridPane.getChildren().remove(buttons[i][j]);
                }
            }
            for (int i = 0; i < 8; i++) {
                int k = 7;
                for (int j = 0; j < 8; j++) {
                    gridPane.add(buttons[k--][i], j, i);
                }
            }
            displayBlackPlayerView = false;
        } else {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    gridPane.getChildren().remove(buttons[i][j]);
                }
            }
            int j = 0;
            for (int i = 7; i >= 0; i--, j++) {
                for (int k = 0; k < 8; k++) {
                    gridPane.add(buttons[k][i], k, j);
                }
            }
            displayBlackPlayerView = true;
        }
    }


    /**
     * This method is used to set the pieces on the board when starting a game. The method checks if a piece is placed
     * in a given position in board. The information is then passed on to the method updateBoard which sets 3D-pieces
     * on the buttons.
     */
    public void updateBoardView() {
        for (int x = 0; x < board.getSquares().length; x++) {
            for (int y = 0; y < board.getSquares()[x].length; y++) {
                if (board.getSquares()[x][y].getPiece() != null) {
                    String info = board.getSquares()[x][y].getPiece().colorAndNameToString();
                    updateBoard(info, x, y);
                } else {
                    updateBoard("NoPiece", x, y);
                }
            }
        }
    }

    /**
     * This method receives information about whether or not to place a piece on a button. If a piece is added, it is also
     * set that if a user clicks on the piece, its parents (button) it notified.
     * @param info the information about piece and color that should be set.
     * @param x part of the coordination of where the piece should be placed.
     * @param y part of the coordination of where the piece should be placed.
     */
    public void updateBoard(String info, int x, int y) {
        switch (info) {
            case ("BlackKnight"):
                BlackKnight3D blackKnight3D = new BlackKnight3D();
                buttons[x][y].setGraphic(blackKnight3D);
                blackKnight3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackBishop"):
                BlackBishop3D blackBishop3D = new BlackBishop3D();
                buttons[x][y].setGraphic(blackBishop3D);
                blackBishop3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackKing"):
                BlackKing3D blackKing3D = new BlackKing3D();
                buttons[x][y].setGraphic(blackKing3D);
                blackKing3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackPawn"):
                BlackPawn3D blackPawn3D = new BlackPawn3D();
                buttons[x][y].setGraphic(blackPawn3D);
                blackPawn3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackQueen"):
                BlackQueen3D blackQueen3D = new BlackQueen3D();
                buttons[x][y].setGraphic(blackQueen3D);
                blackQueen3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackRook"):
                BlackRook3D blackRook3D = new BlackRook3D();
                buttons[x][y].setGraphic(blackRook3D);
                blackRook3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteKnight"):
                WhiteKnight3D whiteKnight3D = new WhiteKnight3D();
                buttons[x][y].setGraphic(whiteKnight3D);
                whiteKnight3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteBishop"):
                WhiteBishop3D whiteBishop3D = new WhiteBishop3D();
                buttons[x][y].setGraphic(whiteBishop3D);
                whiteBishop3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteKing"):
                WhiteKing3D whiteKing3D = new WhiteKing3D();
                buttons[x][y].setGraphic(whiteKing3D);
                whiteKing3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhitePawn"):
                WhitePawn3D whitePawn3D = new WhitePawn3D();
                buttons[x][y].setGraphic(whitePawn3D);
                whitePawn3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteQueen"):
                WhiteQueen3D whiteQueen3D = new WhiteQueen3D();
                buttons[x][y].setGraphic(whiteQueen3D);
                whiteQueen3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteRook"):
                WhiteRook3D whiteRook3D = new WhiteRook3D();
                buttons[x][y].setGraphic(whiteRook3D);
                whiteRook3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("NoPiece"):
                buttons[x][y].setGraphic(null);
                break;
        }
    }

    /**
     * After a round the colors of the buttons are reset to either black or white.
     */
    public void restoreDefaultColors() {
        for (int y = buttons.length - 1; y >= 0; y--) {
            for (int x = 0; x < buttons[y].length; x++) {
                if ((x + y) % 2 == 0) {
                    buttons[x][y].setStyle("-fx-background-color: black;");
                } else {
                    buttons[x][y].setStyle("-fx-background-color: white;");
                }
                buttons[x][y].setOnMouseEntered(null);
                buttons[x][y].setOnMouseExited(null);
            }
        }
    }

    /**
     * This method sets the boolean in the pieces of if their mouseEntered or mouseExited methods should do something or not.
     * @param playersTurn the value which determines whose turn it is.
     */
    public void setPlayersTurn(int playersTurn) {
        if (playersTurn % 2 != 1) {
            for (int y = buttons.length - 1; y >= 0; y--) {
                for (int x = 0; x < buttons[y].length; x++) {
                    Group piece = (Group) buttons[x][y].getGraphic();
                    if (piece instanceof White) {
                        ((White) piece).changePlayersTurn(true);
                    }
                    if (piece instanceof Black) {
                        ((Black) piece).changePlayersTurn(false);
                    }
                }
            }
        } else {
            for (int y = buttons.length - 1; y >= 0; y--) {
                for (int x = 0; x < buttons[y].length; x++) {
                    Group piece = (Group) buttons[x][y].getGraphic();
                    if (piece instanceof White) {
                        ((White) piece).changePlayersTurn(false);
                    }
                    if (piece instanceof Black) {
                        ((Black) piece).changePlayersTurn(true);
                    }
                }
            }
        }
    }

    /**
     * This method changes the color of the positions where a user could place the piece which they have selected.
     * @param x part of the coordination for which button to change color of.
     * @param y part of the coordination for which button to change color of.
     */
    public void setValidMoves(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: yellow;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: yellow;"));
    }

    /**
     * This method changes the color of the positions where a user could make a special move for a piece
     * which they have selected.
     * @param x part of the coordination for which button to change color of.
     * @param y part of the coordination for which button to change color of.
     */
    public void setSpecialMove(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: green;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: green;"));

    }

    /**
     * This method changes the color of the positions where a user could attack with a piece which they have selected.
     * @param x part of the coordination for which button to change color of.
     * @param y part of the coordination for which button to change color of.
     */
    public void setPossibleAttack(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: red;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: red;"));
    }

    /**
     * If a user does the special move en Passant this method makes sure that the piece that was attacked is removed
     * from the board.
     * @param x part of the coordination for which button to remove a piece from.
     * @param y part of the coordination for which button to remove a piece from.
     */
    public void enPassant(int x, int y) {
        buttons[x][y].setGraphic(null);
    }

    /**
     * If a user does the special move promotion, this method makes sure that the pawn is promoted to a queen.
     * @param position the position to place the new queen on.
     * @param color the color of the queen.
     */
    public void promoted(Coordinate position, String color) {
        int x = position.getX();
        int y = position.getY();
        if(color.equals("White")){
            WhiteQueen3D queen = new WhiteQueen3D();
            promotedPawn = queen;
            queen.setOnMouseClicked(event -> {
                buttons[x][y].getStyleClass().add("pressed");
            });
        } else {
            BlackQueen3D queen = new BlackQueen3D();
            promotedPawn = queen;
            queen.setOnMouseClicked(event -> {
                buttons[x][y].getStyleClass().add("pressed");
            });
        }
        promoted = true;
    }

    /**
     * If a user makes the special move rocked, this method is called which makes sure that the rook is placed on
     * correct button.
     * @param newRookPosition the position where the rook should be placed after the move.
     * @param rookPosition the position of where the rook was before the move.
     */
    public void rockad(Coordinate newRookPosition, Coordinate rookPosition) {
        int x = rookPosition.getX();
        int y = rookPosition.getY();
        Group rookToMove = (Group) buttons[x][y].getGraphic();
        buttons[x][y].setGraphic(null);
        int newX = newRookPosition.getX();
        int newY = newRookPosition.getY();
        buttons[newX][newY].setGraphic(rookToMove);
    }

    /**
     * This method is used to display who won or if the game was a draw.
     * @param winner name of the winner or information about game ending with a draw.
     */
    public void winOrDrawMessage(String winner) {
        String result;

        if (winner.equals("DRAW")) {
            result = "The game was a draw!";
        }
        else {
            result = winner + " won the game!";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.setOnCloseRequest(event -> {
            helloApplication.close();
        });

        alert.showAndWait();
    }

    /**
     * A method to receive the instance of helloApplication which loaded the fxml file and instantiated this class.
     * @param helloApplication the instance of helloApplication which loaded the fxml file and instantiated this class
     */
    public void sendHelloApplication(HelloApplication helloApplication){
        this.helloApplication = helloApplication;
    }
}