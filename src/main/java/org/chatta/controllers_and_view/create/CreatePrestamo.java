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


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOSPRESTAMOS);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("inserción correcta");
        alert.setHeaderText("la insertción a sido completada con exito");
        alert.setContentText("La inserción de prestamo a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
