package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.EditorialDAO;
import model.entity.Editorial;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteEditorial {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Editorial> comboBoxEditoriales;

    private EditorialDAO editorialDAO;

    public DeleteEditorial() {
        // Inicializar el DAO
        editorialDAO = EditorialDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Editorial> editorials = FXCollections.observableArrayList(editorialDAO.findAll());
        comboBoxEditoriales.setItems(editorials);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Editorial editorialSeleccionado = comboBoxEditoriales.getSelectionModel().getSelectedItem();
        if (editorialSeleccionado != null) {
            // Eliminar el autor de la base de datos
            editorialDAO.deleteEntity(editorialSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            comboBoxEditoriales.getItems().remove(editorialSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación de la editorial se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una editorial para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
