package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.entity.Autor;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteAutor {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Autor> comboBoxAutores;

    private AutorDAO autorDAO;

    public DeleteAutor() {
        // Inicializar el DAO
        autorDAO = AutorDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Autor> autores = FXCollections.observableArrayList(autorDAO.findAll());
        comboBoxAutores.setItems(autores);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Autor autorSeleccionado = comboBoxAutores.getSelectionModel().getSelectedItem();
        if (autorSeleccionado != null) {
            // Eliminar el autor de la base de datos
            autorDAO.deleteEntity(autorSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            comboBoxAutores.getItems().remove(autorSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación del autor se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un autor para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
