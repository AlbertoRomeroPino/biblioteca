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

        if (nombre.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción correcta");
            alert.setHeaderText("la insertción no a sido completada con exito");
            alert.setContentText("La inserción del nombre de la editorial  no a sido realizada con exito,\n el campo nombre se encuentra vacio");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } else if (pais.getText().isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("inserción correcta");
            alert.setHeaderText("la insertción no a sido completada con exito");
            alert.setContentText("La inserción del pais de la editorial  no a sido realizada con exito,\n el campo pais se encuentra vacio");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
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
