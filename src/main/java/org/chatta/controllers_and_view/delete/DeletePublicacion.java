package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.dao.PublicacionDAO;
import model.entity.Autor;
import model.entity.Publicacion;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeletePublicacion {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Publicacion> comboBoxPublicaciones;

    private PublicacionDAO publicacionDAO;

    public DeletePublicacion() {
        // Inicializar el DAO
        publicacionDAO = PublicacionDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Publicacion> publicaciones = FXCollections.observableArrayList(publicacionDAO.findAll());
        comboBoxPublicaciones.setItems(publicaciones);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Publicacion publicacionSeleccionado = comboBoxPublicaciones.getSelectionModel().getSelectedItem();
        if (publicacionSeleccionado != null) {
            // Eliminar el autor de la base de datos
            publicacionDAO.deleteEntity(publicacionSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            comboBoxPublicaciones.getItems().remove(publicacionSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación de la publicacion se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una publicacion para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSPUBLICACION);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
