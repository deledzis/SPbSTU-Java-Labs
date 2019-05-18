package ru.deledzis.labs.lab4;

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

public class Lab4Controller implements Initializable {

    @FXML
    private TextField threadsCountText;
    @FXML
    private TextField linesCountText;
    @FXML
    private Button runButton;
    @FXML
    private Button backButton;
    @FXML
    private Line errorThreadsCountLine;
    @FXML
    private Label errorThreadsCountDescriptionLabel;
    @FXML
    private Line errorLinesCountLine;
    @FXML
    private Label errorLinesCountDescriptionLabel;
    @FXML
    private TextArea resultTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BooleanBinding threadsPropertyEmpty = threadsCountText.textProperty().isEmpty();
        BooleanBinding linesPropertyEmpty = linesCountText.textProperty().isEmpty();
        runButton.disableProperty().bind(threadsPropertyEmpty.or(linesPropertyEmpty));
    }

    @FXML
    private void onRunButtonClicked(ActionEvent actionEvent) {
        int threadsCount;
        try {
            threadsCount = Integer.parseInt(threadsCountText.getText());
            if (threadsCount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            errorThreadsCountLine.setVisible(true);
            errorThreadsCountDescriptionLabel.setVisible(true);
            return;
        }
        errorThreadsCountLine.setVisible(false);
        errorThreadsCountDescriptionLabel.setVisible(false);

        int linesCount;
        try {
            linesCount = Integer.parseInt(linesCountText.getText());
            if (linesCount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            errorLinesCountLine.setVisible(true);
            errorLinesCountDescriptionLabel.setVisible(true);
            return;
        }
        errorLinesCountLine.setVisible(false);
        errorLinesCountDescriptionLabel.setVisible(false);

        WorkingThread[] threads = new WorkingThread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new WorkingThread(this, linesCount);
            if (i > 0) {
                threads[i].setLock(threads[i - 1]);
            }
        }
        threads[0].setLock(threads[threadsCount - 1]);
        threads[0].setFirst();
        threads[threadsCount - 1].setLast();

        Thread[] mainThreads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            mainThreads[i] = new Thread(threads[i]);
        }

        for (int i = threadsCount; i > 0; i--) {
            mainThreads[i - 1].start();
        }

        resultTextArea.clear();
        runButton.setText("В процессе...");
        threadsCountText.setEditable(false);
        linesCountText.setEditable(false);
    }

    synchronized void handleNewMessage(String message) {
        Platform.runLater(() -> resultTextArea.setText(resultTextArea.getText() + message));
    }

    void handleFinish() {
        Platform.runLater(() -> {
            resultTextArea.setText(resultTextArea.getText() + "\n==================\n\nЗавершено.");
            threadsCountText.clear();
            linesCountText.clear();
            threadsCountText.setEditable(true);
            linesCountText.setEditable(true);
            runButton.setText("Пуск");
        });
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
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
