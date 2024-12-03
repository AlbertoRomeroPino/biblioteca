package org.chatta.controllers_and_view.create;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;
import utils.Validacion;

import java.io.IOException;

public class CreateUsuario {

    @FXML
    public TextField nombre;
    @FXML
    public TextField clave;
    @FXML
    public TextField email;
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
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción incorrecta",
                    "La inserción ha fallado",
                    "El campo 'Nombre' está vacío o el nombre es repetido.");
            return;
        }

        if (clave.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción incorrecta",
                    "La inserción ha fallado",
                    "El campo 'Clave' está vacío.");
            return;
        }

        if (email.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción incorrecta",
                    "La inserción ha fallado",
                    "El campo 'Email' está vacío.");
            return;
        }

        // Validación del formato de email
        if (!Validacion.validacionEmail(email.getText())) {
            mostrarAlerta(Alert.AlertType.ERROR, "Inserción incorrecta",
                    "La inserción ha fallado",
                    "El email no cumple con los requisitos de validación.");
            return;
        }

        // Creación y almacenamiento del usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.getText());
        usuario.setClave(Validacion.encryptClave(clave.getText()));
        usuario.setEMAIL(email.getText());

        UsuarioDAO.build().store(usuario);

        // Mostrar mensaje de éxito
        mostrarAlerta(Alert.AlertType.INFORMATION, "Inserción correcta",
                "La inserción ha sido completada con éxito.",
                "El usuario ha sido registrado correctamente.");

        // Cambiar a la pantalla principal de base de datos
        App.setRoot(scenes.PANTALLADEBASADEDATOS);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
