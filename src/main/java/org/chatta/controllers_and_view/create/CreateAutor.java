package org.chatta.controllers_and_view.create;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.entity.Autor;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class CreateAutor {

    @FXML
    public DatePicker fecha_nacimiento;
    @FXML
    public TextField nombre;
    @FXML
    public TextField nacionalidad;

    @FXML
    private Button closeButton;

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void Close() throws IOException {
        if (nombre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Nombre' está vacío. Por favor, ingréselo.");
            return;
        }

        if (nacionalidad.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Nacionalidad' está vacío. Por favor, ingréselo.");
            return;
        }

        if (fecha_nacimiento.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Fecha de Nacimiento' está vacío. Por favor, ingréselo.");
            return;
        }

        // Si todos los campos están correctamente completados
        Autor autor = new Autor();
        autor.setNombre(nombre.getText());
        autor.setNacionalidad(nacionalidad.getText());
        autor.setFechaNacimiento(fecha_nacimiento.getValue());

        AutorDAO.build().store(autor);

        showAlert(Alert.AlertType.INFORMATION, "Inserción Correcta", "La inserción se ha completado con éxito",
                "El autor ha sido registrado correctamente.");

        // Cambiar a la pantalla de autores después de insertar el autor
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

