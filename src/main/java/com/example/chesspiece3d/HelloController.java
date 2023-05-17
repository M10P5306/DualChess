package com.example.chesspiece3d;

import Model.*;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchorPane;
    private boolean flip = true;
    private Group selectedPiece = null;
    private Button[][] buttons = new Button[8][8];
    private boolean displayBlackPlayerView = true;
    private boolean promoted = false;
    private Queen3D promotedPawn;
    private Board board = new Board();
    private Button selectedButton;
    private ControllerFor3D controllerFor3D;
    private HelloApplication helloApplication;
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
                            //handleEvent((Group) selectedButton.getGraphic());

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
        //gridPane.translateZProperty().set(100);
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


    public void handleButton(Button button) {
        if (selectedPiece != null) {
            button.setGraphic(selectedPiece);
        }
    }

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
                    if (piece instanceof WhiteQueen3D || piece instanceof BlackQueen3D) {
                        Rotate rotatePieceY = new Rotate(-35, Rotate.Y_AXIS);
                        Rotate rotatePieceX = new Rotate(-75, Rotate.X_AXIS);
                        piece.getTransforms().addAll(rotatePieceY, rotatePieceX);
                        piece.translateYProperty().set(0);
                    } else if (piece != null) {
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
                    if (piece instanceof WhiteQueen3D) {
                        piece.getTransforms().clear();
                        ((WhiteQueen3D) piece).restorePosition();
                    }
                    if (piece instanceof BlackQueen3D) {
                        piece.getTransforms().clear();
                        ((BlackQueen3D) piece).restorePosition();
                    }
                    /*if (piece instanceof BlackPawn3D) {
                        ((BlackPawn3D) piece).restorePosition();
                    }*/
                }
            }

            flip = true;
        }
    }

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


    public void handleEvent(Group group) {
        //group.setTranslateZ(-100);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), group);
        rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(-360);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), group);
        //translateTransition.setFromX(0);
        //translateTransition.setFromY(0);
        translateTransition.setToZ(-100);
        //translateTransition.setToY(200);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            rotateTransition.play(); //
        });

        //
        translateTransition.play();
    }


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

    public void updateBoard(String info, int x, int y) {
        switch (info) {
            case ("BlackKnight"):
                BlackKnight3D blackKnight3D = new BlackKnight3D(this);
                buttons[x][y].setGraphic(blackKnight3D);
                blackKnight3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackBishop"):
                BlackBishop3D blackBishop3D = new BlackBishop3D(this);
                buttons[x][y].setGraphic(blackBishop3D);
                blackBishop3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackKing"):
                BlackKing3D blackKing3D = new BlackKing3D(this);
                buttons[x][y].setGraphic(blackKing3D);
                blackKing3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackPawn"):
                BlackPawn3D blackPawn3D = new BlackPawn3D(this);
                buttons[x][y].setGraphic(blackPawn3D);
                blackPawn3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackQueen"):
                BlackQueen3D blackQueen3D = new BlackQueen3D(this);
                buttons[x][y].setGraphic(blackQueen3D);
                blackQueen3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("BlackRook"):
                BlackRook3D blackRook3D = new BlackRook3D(this);
                buttons[x][y].setGraphic(blackRook3D);
                blackRook3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteKnight"):
                WhiteKnight3D whiteKnight3D = new WhiteKnight3D(this);
                buttons[x][y].setGraphic(whiteKnight3D);
                whiteKnight3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteBishop"):
                WhiteBishop3D whiteBishop3D = new WhiteBishop3D(this);
                buttons[x][y].setGraphic(whiteBishop3D);
                whiteBishop3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteKing"):
                WhiteKing3D whiteKing3D = new WhiteKing3D(this);
                buttons[x][y].setGraphic(whiteKing3D);
                whiteKing3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhitePawn"):
                WhitePawn3D whitePawn3D = new WhitePawn3D(this);
                buttons[x][y].setGraphic(whitePawn3D);
                whitePawn3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteQueen"):
                WhiteQueen3D whiteQueen3D = new WhiteQueen3D(this);
                buttons[x][y].setGraphic(whiteQueen3D);
                whiteQueen3D.setOnMouseClicked(event -> {
                    buttons[x][y].getStyleClass().add("pressed");
                });
                break;
            case ("WhiteRook"):
                WhiteRook3D whiteRook3D = new WhiteRook3D(this);
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

    public void setValidMoves(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: yellow;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: yellow;"));
    }

    public void setSpecialMove(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: green;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: green;"));

    }

    public void setPossibleAttack(int x, int y) {
        buttons[x][y].setStyle("-fx-background-color: red;");
        buttons[x][y].setOnMouseEntered(event -> buttons[x][y].setStyle("-fx-background-color: grey;"));
        buttons[x][y].setOnMouseExited(event -> buttons[x][y].setStyle("-fx-background-color: red;"));
    }

    public void enPassant(int x, int y) {
        buttons[x][y].setGraphic(null);
    }

    public void promoted(Coordinate position, String color) {
        int x = position.getX();
        int y = position.getY();
        if(color.equals("White")){
            WhiteQueen3D queen = new WhiteQueen3D(this);
            promotedPawn = queen;
            queen.setOnMouseClicked(event -> {
                buttons[x][y].getStyleClass().add("pressed");
            });
        } else {
            BlackQueen3D queen = new BlackQueen3D(this);
            promotedPawn = queen;
            queen.setOnMouseClicked(event -> {
                buttons[x][y].getStyleClass().add("pressed");
            });
        }
        promoted = true;
    }

    public void rockad(Coordinate newRookPosition, Coordinate rookPosition) {
        int x = rookPosition.getX();
        int y = rookPosition.getY();
        Group rookToMove = (Group) buttons[x][y].getGraphic();
        buttons[x][y].setGraphic(null);
        int newX = newRookPosition.getX();
        int newY = newRookPosition.getY();
        buttons[newX][newY].setGraphic(rookToMove);
    }

    public int winOrDrawMessage(String winner) {
        int answer = 0;

        if (winner.equals("DRAW")) {
            answer = JOptionPane.showConfirmDialog(null, "the Game was a draw! Would you like to play again?");
        }
        else {
            answer = JOptionPane.showConfirmDialog(null, winner + " won the game! Would you like to play again?");
        }
        return answer;
    }

    public void close(){
        helloApplication.close();
    }

    public void sendHelloApplication(HelloApplication helloApplication){
        this.helloApplication = helloApplication;
    }
}