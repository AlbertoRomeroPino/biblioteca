package org.chatta.controllers_and_view;


import javafx.scene.control.Alert;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.chatta.App;
import utils.Validacion;

import java.io.IOException;
import java.util.List;

import static utils.Alerta.mostrarAlerta;


public class PantalladeRegistro {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button closeButton;

    @FXML
    private void handleRegister() throws IOException {
        // Lógica para manejar el registro del usuario
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String hashedPassword = Validacion.encryptClave(password);

        // Validar formato del correo electrónico
        if (Validacion.validacionEmail(email)) {
            // Verificar si el correo ya está registrado
            Usuario usuarioExistente = UsuarioDAO.build().findByIdentificator(email, hashedPassword);

            if (usuarioExistente == null) {
                // Verificar si el nombre de usuario ya existe
                List<Usuario> usuarios = UsuarioDAO.build().findAll();
                boolean nombreExiste = usuarios.stream()
                        .anyMatch(usuario -> usuario.getNombre().equalsIgnoreCase(nombre));

                if (!nombreExiste) {
                    // Crear y almacenar el nuevo usuario
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombre(nombre);
                    nuevoUsuario.setEMAIL(email);
                    nuevoUsuario.setClave(hashedPassword);

                    UsuarioDAO.build().store(nuevoUsuario);

                    // Confirmar registro y cambiar pantalla
                    System.out.println("Usuario registrado exitosamente: " + nuevoUsuario);
                    App.setRoot(scenes.PANTALLADEBASADEDATOS);
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                } else {
                    // El nombre de usuario ya existe
                    System.out.println("El nombre de usuario ya está en uso.");
                    mostrarAlerta("Error de registro", "El nombre de usuario ya está en uso.", Alert.AlertType.ERROR);
                }
            } else {
                // El correo ya está registrado
                System.out.println("El correo electrónico ya está registrado.");
                mostrarAlerta("Error de registro", "El correo electrónico ya está registrado.", Alert.AlertType.ERROR);
            }
        } else {
            // Formato de correo inválido
            System.out.println("El correo electrónico no tiene un formato válido.");
            mostrarAlerta("Error de formato", "El correo electrónico no tiene un formato válido.", Alert.AlertType.ERROR);
        }

    }


    @FXML
    private void Close() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}



