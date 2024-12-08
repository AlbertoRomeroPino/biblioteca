package org.chatta.controllers_and_view;

import model.dao.UsuarioDAO;
import model.entity.Usuario;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;
import utils.Validacion;
import model.connection.ConnectionMariaDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PantalladeIdentificacion {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;



    @FXML
    private Button closeButton;


    @FXML
    private void handleIdentificacion() throws IOException {
        // Obtener los datos introducidos por el usuario
        String email = emailField.getText();
        String password = passwordField.getText();
        String passwordCifrado = Validacion.encryptClave(password);

        // Validar formato del correo electrónico
        if (Validacion.validacionEmail(email)) {
            // Buscar el usuario en la base de datos
            Usuario usuario = UsuarioDAO.build().findByIdentificator(email, passwordCifrado);

            if (usuario != null) {
                // Usuario encontrado y autenticado
                App.setRoot(scenes.PANTALLADEBASADEDATOS);
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.close();
            } else {
                // Usuario no encontrado
                mostrarAlerta("Error de autenticación", "El correo electrónico o la contraseña son incorrectos.", AlertType.ERROR);
            }
        } else {
            // Formato de correo inválido
            mostrarAlerta("Error de formato", "El correo electrónico no tiene un formato válido.", AlertType.ERROR);
        }

    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void Close() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
