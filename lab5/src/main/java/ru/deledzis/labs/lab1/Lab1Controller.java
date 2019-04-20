package ru.deledzis.labs.lab1;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Lab1Controller implements Initializable {

    @FXML
    private Button calculateButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField firstArgText;
    @FXML
    private TextField secondArgText;
    @FXML
    private TextField resultText;
    @FXML
    private ChoiceBox<Character> operatorChoiceBox;
    @FXML
    private Line errorFirstLine;
    @FXML
    private Line errorSecondLine;
    @FXML
    private Label errorFirstDescriptionLabel;
    @FXML
    private Label errorSecondDescriptionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operatorChoiceBox.getItems().addAll('+', '-', '÷', '×');
        operatorChoiceBox.setValue(operatorChoiceBox.getItems().get(0));

        BooleanBinding firstPropertyEmpty = firstArgText.textProperty().isEmpty();
        BooleanBinding secondPropertyEmpty = secondArgText.textProperty().isEmpty();
        calculateButton.disableProperty().bind(firstPropertyEmpty.or(secondPropertyEmpty));
    }

    @FXML
    private void onCalculateButtonClicked(ActionEvent actionEvent) {
        double firstOperand;
        double secondOperand;
        try {
            firstOperand = Double.parseDouble(firstArgText.getText());
        } catch (NumberFormatException ex) {
            errorFirstLine.setVisible(true);
            errorFirstDescriptionLabel.setVisible(true);
            return;
        }
        errorFirstLine.setVisible(false);
        errorFirstDescriptionLabel.setVisible(false);

        try {
            secondOperand = Double.parseDouble(secondArgText.getText());
        } catch (NumberFormatException ex) {
            errorSecondLine.setVisible(true);
            errorSecondDescriptionLabel.setVisible(true);
            return;
        }
        errorSecondLine.setVisible(false);
        errorSecondDescriptionLabel.setVisible(false);

        resultText.setText(String.valueOf(doMathOperation(operatorChoiceBox.getValue(), firstOperand, secondOperand)));
    }

    private static double doMathOperation(char operator, double first, double second) {
        switch (operator) {
            case '+': return first + second;
            case '-': return first - second;
            case '×': return first * second;
            case '÷': return first / second;
            default: return first + second;
        }
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
