package org.chatta.controllers_and_view;


import model.entity.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.chatta.App;

import java.io.IOException;

import java.time.LocalDate;


public class PantalladeRegistro {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private DatePicker fechaNacimientoPicker;

    @FXML
    private Button closeButton;

    @FXML
    private void handleRegister() throws IOException {
        // Lógica para manejar el registro del usuario
        String nombre = nombreField.getText();
        System.out.println(nombre);
        String email = emailField.getText();
        System.out.println(email);
        String password = passwordField.getText();
        System.out.println(password);
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        System.out.println(fechaNacimiento);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEMAIL(email);
        usuario.setClave(password);
        // Aquí puedes guardar el usuario en la base de datos o realizar otras acciones
        System.out.println("Usuario registrado: " + usuario);

        App.setRoot(scenes.PANTALLADEBASADEDATOS);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


@FXML
    private void Close() throws IOException {
        App.setRoot(scenes.PANTALLADEINICIO);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}



