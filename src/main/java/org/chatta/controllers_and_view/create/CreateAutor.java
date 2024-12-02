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
        autor.setNombre(nombre.getText());
        autor.setNacionalidad(nacionalidad.getText());
        autor.setFechaNacimiento(fecha_nacimiento.getValue());

        AutorDAO.build().store(autor);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("inserción correcta");
        alert.setHeaderText("la insertción a sido completada con exito");
        alert.setContentText("La inserción de autor a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
