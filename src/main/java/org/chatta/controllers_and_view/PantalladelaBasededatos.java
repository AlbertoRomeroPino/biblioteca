package org.chatta.controllers_and_view;

import org.chatta.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class PantalladelaBasededatos {

    @FXML
    private void SwitchToPantalladeInicio() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEINICIO);
    }
}
