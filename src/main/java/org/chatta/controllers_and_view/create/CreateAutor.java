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



    @FXML
    private void Close() throws IOException {



        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        Autor autor= new Autor();
        if (nombre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción no a sido completada con exito");
            alert.setContentText("La inserción del nombre del autor no a sido realizada con exito,\n el campo nobre se encuentra vacio");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();

        } else if (nacionalidad.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción no a sido completada con exito");
            alert.setContentText("La inserción de la nacionalidad del autor no a sido realizada con exito,\n el campo nacionalidad se encuentra vacio");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();

        } else if (fecha_nacimiento.getValue().equals(null)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción incorrecta");
            alert.setHeaderText("la insertción no a sido completada con exito");
            alert.setContentText("La inserción de la fecha de nacimiento del autor no a sido realizada con exito,\n el campo fecha de nacimiento se encuentra vacio");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();

        } else {


            autor.setNombre(nombre.getText());
            autor.setNacionalidad(nacionalidad.getText());
            autor.setFechaNacimiento(fecha_nacimiento.getValue());

            AutorDAO.build().store(autor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("inserción correcta");
            alert.setHeaderText("la insertción a sido completada con exito");
            alert.setContentText("La inserción de autor a sido realizada con exito");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
