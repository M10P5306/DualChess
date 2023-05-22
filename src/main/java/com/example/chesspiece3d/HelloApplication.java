package com.example.chesspiece3d;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * This class loads the fxml file which makes up the scene of the 3D GUI.
 * @author Hugo Andersson
 */
public class HelloApplication extends Application {

    /**
     * The controller connected to the fxml file.
     */
    private HelloController helloController;

    /**
     * The stage which makes up the 3D GUI.
     */
    private Stage stage;

    /**
     * This method loads the fxml file, sets the name of the stage and sets which methods should be called if user
     * presses a key.
     * @param stage the stage which makes up the 3D GUI.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        helloController = loader.getController();
        helloController.sendHelloApplication(this);
        stage.setTitle("DualChess");

        Scene scene = new Scene(root, 1200, 800);

        stage.setScene(scene);
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.F){ helloController.keyPressed();}
            if(event.getCode() == KeyCode.C){helloController.changeToBlackPlayerView();}
        });
        stage.show();
    }

    /**
     * The method which launches the 3D GUI.
     */

    public void start(){
        launch();
    }

    /**
     * The method which terminates the 3D GUI.
     */
    public void close(){
        stage.close();
    }
}