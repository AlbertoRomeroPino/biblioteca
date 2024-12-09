package org.chatta.controllers_and_view.create;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.PublicacionDAO;
import model.dao.RevistaDAO;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Publicacion;
import model.entity.Revista;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class CreateRevista {

    @FXML
    private ComboBox<Periodicidad_Enum> comboboxperiodicidadenum;
    @FXML
    private ComboBox<Publicacion> comboboxPublicacion;
    @FXML
    public TextField ISSN;
    @FXML
    private Button closeButton;




    public CreateRevista() {
        // Inicializar el DAO

    }

    private Publicacion publicacion;

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Periodicidad_Enum> periodicidad = FXCollections.observableArrayList(Periodicidad_Enum.values());
        comboboxperiodicidadenum.setItems(periodicidad);

    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void Close() throws IOException {

        Publicacion publicacionSeleccionado = publicacion;
        if (ISSN.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'ISSN' está vacío. Por favor, ingréselo.");
            return;
        }

        // Si todos los campos están correctamente completados

        Revista revista = new Revista();
        revista.setId(publicacionSeleccionado.getId());
        revista.setTitulo(publicacionSeleccionado.getTitulo());
        revista.setFecha_publicacion(publicacionSeleccionado.getFecha_publicacion());
        revista.setTipo(publicacionSeleccionado.getTipo());
        revista.setCategoria(publicacionSeleccionado.getCategoria());
        revista.setEditorial(publicacionSeleccionado.getEditorial());
        revista.setPrestamos(publicacionSeleccionado.getPrestamos());
        revista.setISSN(ISSN.getText());
        revista.setPeriodicidad(comboboxperiodicidadenum.getSelectionModel().getSelectedItem());

        RevistaDAO.build().store(revista);

        showAlert(Alert.AlertType.INFORMATION, "Inserción Correcta", "La inserción se ha completado con éxito",
                "La revista ha sido registrado correctamente.");

        // Cambiar a la pantalla de autores después de insertar el autor
        App.setRoot(scenes.PANTALLADEBASADEDATOSREVISTA);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
