package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.PrestamoDAO;
import model.entity.Prestamo;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeletePrestamo {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Prestamo> ComboBoxPrestamo;

    private PrestamoDAO prestamoDAO;

    public DeletePrestamo() {
        // Inicializar el DAO
        prestamoDAO = PrestamoDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList(prestamoDAO.findAll());
        ComboBoxPrestamo.setItems(prestamos);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Prestamo prestamoSeleccionado = ComboBoxPrestamo.getSelectionModel().getSelectedItem();
        if (prestamoSeleccionado != null) {
            // Eliminar el autor de la base de datos
            prestamoDAO.deleteEntity(prestamoSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            ComboBoxPrestamo.getItems().remove(prestamoSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación del prestamo se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un prestamo para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
