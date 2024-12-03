package org.chatta.controllers_and_view.create;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.EditorialDAO;
import model.entity.Editorial;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class CreateEditorial {

    @FXML
    public TextField nombre;
    @FXML
    public TextField pais;
    @FXML
    public DatePicker fecha_fundacion;
    @FXML
    private Button closeButton;


    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    @FXML
    private void Close() throws IOException {

        // Validación de campos obligatorios
        if (nombre.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción fallida",
                    "El campo 'Nombre' está vacío.",
                    "Por favor, completa el campo 'Nombre' para continuar.");
            return;
        }

        if (pais.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción fallida",
                    "El campo 'País' está vacío.",
                    "Por favor, completa el campo 'País' para continuar.");
            return;
        }

        if (fecha_fundacion.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción fallida",
                    "El campo 'Fecha de Fundación' está vacío.",
                    "Por favor, selecciona una fecha de fundación para continuar.");
            return;
        }


        // Crear y almacenar la editorial
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre.getText());
        editorial.setPais(pais.getText());
        editorial.setFecha_fundacion(fecha_fundacion.getValue());

        EditorialDAO.build().store(editorial);

        // Mostrar confirmación y cerrar ventana
        mostrarAlerta(Alert.AlertType.INFORMATION, "Inserción correcta",
                "La inserción se ha completado con éxito.",
                "La nueva editorial ha sido añadida correctamente a la base de datos.");

        // Cambiar a la pantalla principal de editoriales
        App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
