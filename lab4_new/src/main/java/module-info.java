module lab {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.deledzis.lab to javafx.fxml;
    exports ru.deledzis.lab;
}