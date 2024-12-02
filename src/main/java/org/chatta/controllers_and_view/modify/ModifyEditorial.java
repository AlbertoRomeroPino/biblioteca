package org.chatta.controllers_and_view.modify;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class ModifyEditorial {
    @FXML
    private Button closeButton;


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modificación correcta");
        alert.setHeaderText("la modificación a sido completada con exito");
        alert.setContentText("La modificación de editorial a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
