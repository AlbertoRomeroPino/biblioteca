package org.chatta.controllers_and_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.chatta.App;

import java.io.IOException;

public class PantalladeIdentificacion {


    @FXML
    private Button closeButton;


    @FXML
    private void SwitchToPantalladelaBasededatos() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOS);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    

}