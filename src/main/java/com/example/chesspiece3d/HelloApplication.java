package com.example.chesspiece3d;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private HelloController helloController;
    private Stage stage;

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


    public void start(){
        launch();
    }

    public void close(){
        stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}