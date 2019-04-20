package ru.deledzis.labs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button lab1Button;
    @FXML
    private Button lab2Button;
    @FXML
    private Button lab3Button;
    @FXML
    private Button lab4Button;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    private void startLab1(javafx.event.ActionEvent actionEvent) {
        startLab(1);
    }

    @FXML
    private void startLab2(ActionEvent actionEvent) {
        startLab(2);
    }

    @FXML
    private void startLab3(ActionEvent actionEvent) {
        startLab(3);
    }

    @FXML
    private void startLab4(ActionEvent actionEvent) {
        startLab(4);
    }

    private void startLab(int n) {
        Stage mainStage = (Stage) lab1Button.getScene().getWindow();

        try {
            Parent lab1Root = FXMLLoader.load(getClass().getResource("lab" + n + ".fxml"));

            if (lab1Root != null) {
                Scene lab1Scene = new Scene(lab1Root);
                mainStage.setScene(lab1Scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
