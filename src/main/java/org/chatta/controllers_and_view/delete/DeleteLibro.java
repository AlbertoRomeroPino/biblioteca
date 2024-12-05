package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.LibroDAO;
import model.entity.Libro;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteLibro {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Libro> ComboBoxLibros;

    private LibroDAO libroDAO;

    public DeleteLibro() {
        // Inicializar el DAO
        libroDAO = LibroDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Libro> libros = FXCollections.observableArrayList(libroDAO.findAll());
        ComboBoxLibros.setItems(libros);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Libro libroselecionado = ComboBoxLibros.getSelectionModel().getSelectedItem();
        if (libroselecionado != null) {
            // Eliminar el autor de la base de datos
            libroDAO.deleteEntity(libroselecionado);
            // Eliminar el autor de la lista del ComboBox
            ComboBoxLibros.getItems().remove(libroselecionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación del libro se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un libro para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
