package com.scoutingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene0.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(size.getWidth());
        primaryStage.setHeight(size.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.centerOnScreen();
        primaryStage.show();

//        primaryStage.setFullScreen(true);

    }

    public static void main(String[] args) {
        launch();
    }

}