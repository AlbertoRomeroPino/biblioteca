package org.chatta.controllers_and_view;


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
        Validacion.validacionEmail(email);
        String password = passwordField.getText();
        String hasPassword = Validacion.encryptClave(password);

        if (Validacion.validacionEmail(email)) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEMAIL(email);
            usuario.setClave(hasPassword);

            Usuario usuarioDao = UsuarioDAO.build().findByIdentificator(email, hasPassword);



            if (usuarioDao == null){
                UsuarioDAO.build().store(usuario);

                // Aquí puedes guardar el usuario en la base de datos o realizar otras acciones
                System.out.println("Usuario registrado: " + usuario);

                App.setRoot(scenes.PANTALLADEBASADEDATOS);
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Usuario ya existente");
            }
        } else {
            System.out.println("Correo electronico fallido");
        }
    }


    @FXML
    private void Close() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}



