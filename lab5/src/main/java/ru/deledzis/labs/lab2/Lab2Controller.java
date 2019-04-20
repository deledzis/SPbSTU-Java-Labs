package ru.deledzis.labs.lab2;

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

import static ru.deledzis.labs.utils.UtilsKt.getRandomInt;

public class Lab2Controller implements Initializable {

    /***** CLASSES *****/
    private static final int BOOK    = 1;
    private static final int SHOE    = 2;
    private static final int TOY     = 3;
    private static final int PICTURE = 4;

    @FXML
    private Button backButton;
    @FXML
    private Button printButton;
    @FXML
    private TextField countText;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private Label errorDescriptionLabel;
    @FXML
    private Line errorLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BooleanBinding countEmpty = countText.textProperty().isEmpty();
        printButton.disableProperty().bind(countEmpty);
    }

    @FXML
    private void onPrintButtonClicked(ActionEvent actionEvent) {
        resultTextArea.clear();
        int arraySize;
        try {
            arraySize = Integer.parseInt(countText.getText());
            if (arraySize <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            errorLine.setVisible(true);
            errorDescriptionLabel.setVisible(true);
            return;
        }
        errorLine.setVisible(false);
        errorDescriptionLabel.setVisible(false);

        StringBuilder productsSb = new StringBuilder("All Products:").append('\n');
        StringBuilder implementsPresentProducts = new StringBuilder("Products that implements Present interface:")
                .append("\n");

        Product[] products = new Product[arraySize];
        for (int i = 0; i < products.length; i++) {
            Product product = null;
            switch (getRandomInt(1, 4)) {
                case BOOK:
                    product = new Book();
                    break;
                case SHOE:
                    product = new Shoe();
                    break;
                case TOY:
                    product = new Toy();
                    break;
                case PICTURE:
                    product = new Picture();
                    break;
                default:
                    break;
            }
            assert product != null;

            if (product instanceof Present) {
                productsSb.append(((Present) product).itCanBePresented()).append('\n');
                implementsPresentProducts.append("Product #").append(i).append(" ")
                        .append("is a").append(" ").append(product.whoAmI()).append('\n');
            }
            products[i] = product;
            productsSb.append("Product #").append(i).append(" ")
                    .append("is a").append(" ").append(product.whoAmI()).append('\n');
        }

        productsSb.append('\n').append("============").append('\n');
        productsSb.append(implementsPresentProducts);

        resultTextArea.setText(productsSb.toString());
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
