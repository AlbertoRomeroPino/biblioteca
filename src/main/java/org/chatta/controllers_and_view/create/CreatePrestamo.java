package org.chatta.controllers_and_view.create;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class CreatePrestamo {
    @FXML
    private Button closeButton;

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
