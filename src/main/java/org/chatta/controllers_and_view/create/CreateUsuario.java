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


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOS);

        Usuario usuario = new Usuario();

        if (nombre.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción a fallado");
            alert.setContentText("La inserción de nombre de usuario a fallado, es probable que sea un nombre repetido o este el campo vacio");
            alert.showAndWait();
        } else if (clave.getText().isEmpty() ) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción a fallado");
            alert.setContentText("La inserción de clave de usuario a fallado, es probable que este  campo este vacio");
            alert.showAndWait();
        } else if (email.getText().isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción a fallado");
            alert.setContentText("La inserción de email de usuario a fallado, es probable que este  campo este vacio");
            alert.showAndWait();
        } else if (!Validacion.validacionEmail(email.getText())) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción a fallado");
            alert.setContentText("La inserción de email de usuario a fallado, no cumple con los requisitos de validación");
            alert.showAndWait();

        }else {

            usuario.setNombre(nombre.getText());
            String hasPassword = Validacion.encryptClave(clave.getText());
            usuario.setClave(hasPassword);
            usuario.setEMAIL(email.getText());
        }

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
