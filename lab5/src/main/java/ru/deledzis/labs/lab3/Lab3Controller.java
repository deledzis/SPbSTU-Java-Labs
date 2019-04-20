package ru.deledzis.labs.lab3;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Lab3Controller implements Initializable {

    @FXML
    private TextField stepsText;
    @FXML
    private TextField minValueText;
    @FXML
    private TextField maxValueText;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private Button runButton;
    @FXML
    private Button backButton;
    @FXML
    private Line errorStepsLine;
    @FXML
    private Label errorStepsDescriptionLabel;
    @FXML
    private Line errorMinLine;
    @FXML
    private Label errorMinDescriptionLabel;
    @FXML
    private Line errorMaxLine;
    @FXML
    private Label errorMaxDescriptionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BooleanBinding stepsPropertyEmpty = stepsText.textProperty().isEmpty();
        BooleanBinding minValuePropertyEmpty = minValueText.textProperty().isEmpty();
        BooleanBinding maxValuePropertyEmpty = maxValueText.textProperty().isEmpty();
        runButton.disableProperty().bind(stepsPropertyEmpty.or(minValuePropertyEmpty).or(maxValuePropertyEmpty));
    }

    @FXML
    private void onRunButtonClicked(ActionEvent actionEvent) {
        int steps;
        try {
            steps = Integer.parseInt(stepsText.getText());
            if (steps <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            errorStepsLine.setVisible(true);
            errorStepsDescriptionLabel.setVisible(true);
            return;
        }
        errorStepsLine.setVisible(false);
        errorStepsDescriptionLabel.setVisible(false);

        int minValue;
        try {
            minValue = Integer.parseInt(minValueText.getText());
        } catch (NumberFormatException ex) {
            errorMinLine.setVisible(true);
            errorMinDescriptionLabel.setVisible(true);
            return;
        }
        errorMinLine.setVisible(false);
        errorMinDescriptionLabel.setVisible(false);

        int maxValue;
        try {
            maxValue = Integer.parseInt(maxValueText.getText());
            if (maxValue < minValue) {
                throw new NumberFormatException("Less");
            }
        } catch (NumberFormatException ex) {
            if (ex.getMessage().contains("Less")) {
                errorMaxDescriptionLabel.setText("Максимальное значение не может быть меньше минимального");
            } else {
                errorMaxDescriptionLabel.setText("Максимальное значение должно быть целым числом");
            }
            errorMaxLine.setVisible(true);
            errorMaxDescriptionLabel.setVisible(true);
            return;
        }
        errorMaxLine.setVisible(false);
        errorMaxDescriptionLabel.setVisible(false);

        Producer producer = new Producer(this, steps, minValue, maxValue);
        Consumer consumer = new Consumer(this, producer);

        producer.start();
        consumer.start();

        resultTextArea.clear();
        runButton.setText("В процессе...");
        stepsText.setEditable(false);
        minValueText.setEditable(false);
        maxValueText.setEditable(false);
    }

    synchronized void handleNewMessage(String message) {
        Platform.runLater(() -> resultTextArea.setText(resultTextArea.getText() + message));
    }

    void handleFinish() {
        Platform.runLater(() -> {
            resultTextArea.setText(resultTextArea.getText() + "\n==================\n\nЗавершено.");
            stepsText.clear();
            minValueText.clear();
            maxValueText.clear();
            stepsText.setEditable(true);
            minValueText.setEditable(true);
            maxValueText.setEditable(true);
            runButton.setText("Пуск");
        });
    }

    @FXML
    private void onBackButtonClicked(ActionEvent actionEvent) {
        Stage mainStage = (Stage) backButton.getScene().getWindow();

        try {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("../main.fxml"));

            if (mainRoot != null) {
                Scene lab1Scene = new Scene(mainRoot);
                mainStage.setScene(lab1Scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
