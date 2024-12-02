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


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOS);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.getText());
        usuario.setClave(clave.getText());
        usuario.setEMAIL(email.getText());

        UsuarioDAO.build().store(usuario);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("inserción correcta");
        alert.setHeaderText("la insertción a sido completada con exito");
        alert.setContentText("La inserción de usuario a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
