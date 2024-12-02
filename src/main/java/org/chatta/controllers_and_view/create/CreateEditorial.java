package org.chatta.controllers_and_view.create;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.EditorialDAO;
import model.entity.Editorial;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class CreateEditorial {

    @FXML
    public TextField nombre;
    @FXML
    public TextField pais;
    @FXML
    public DatePicker fecha_fundacion;
    @FXML
    private Button closeButton;


    @FXML
    private void Close() throws IOException {

        App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);

        Editorial editorial =new Editorial();
        editorial.setNombre(nombre.getText());
        editorial.setPais(pais.getText());
        editorial.setFecha_fundacion(fecha_fundacion.getValue());

        EditorialDAO.build().store(editorial);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("inserción correcta");
        alert.setHeaderText("la insertción a sido completada con exito");
        alert.setContentText("La inserción de editorial a sido realizada con exito");
        alert.showAndWait();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
