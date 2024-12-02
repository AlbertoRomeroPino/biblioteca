package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantalladeCreditos {

    @FXML
    private Button closeButton;

    @FXML
    private void Close() throws IOException {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
