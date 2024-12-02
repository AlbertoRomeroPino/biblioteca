package org.chatta.controllers_and_view;

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
    private void handleIdentificacion() {
        // Obtener los datos introducidos por el usuario
        String email = emailField.getText();
        String password = passwordField.getText();
        String passwordCifrado = Validacion.encryptClave(password);

        if (Validacion.validacionEmail(email)) {
            try (Connection connection = ConnectionMariaDB.getConnection()) {
                if (connection != null && !connection.isClosed()) {
                    String query = "SELECT * FROM usuario WHERE email = ? AND clave = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, email);
                    statement.setString(2, passwordCifrado);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Usuario encontrado y autenticado, cambiar a la pantalla de base de datos
                        App.setRoot(scenes.PANTALLADEBASADEDATOS);
                        // Cerrar la ventana actual
                        Stage stage = (Stage) emailField.getScene().getWindow();
                        stage.close();
                    } else {
                        // Usuario no encontrado o credenciales incorrectas
                        mostrarAlerta("Error de autenticación", "El correo electrónico o la contraseña son incorrectos.", AlertType.ERROR);
                    }
                } else {
                    mostrarAlerta("Error", "No se pudo conectar a la base de datos.", AlertType.ERROR);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo conectar a la base de datos.", AlertType.ERROR);
            }
        } else {
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
