package ru.deledzis.labs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent mainRoot;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            mainRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Scene mainScene = new Scene(mainRoot);

        primaryStage.setTitle("Лабораторная работа №5");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
