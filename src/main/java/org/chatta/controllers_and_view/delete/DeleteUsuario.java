package org.chatta.controllers_and_view.delete;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteUsuario {
    @FXML
    private Button closeButton;


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOS);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("eliminación correcta");
        alert.setHeaderText("la eliminación a sido completada con exito");
        alert.setContentText("La eliminación de usuario a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
