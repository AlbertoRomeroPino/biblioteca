package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.RevistaDAO;
import model.entity.Revista;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteRevista {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Revista> ComboBoxRevista;

    private RevistaDAO revistaDAO;

    public DeleteRevista() {
        // Inicializar el DAO
        revistaDAO = RevistaDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Revista> revistas = FXCollections.observableArrayList(revistaDAO.findAll());
        ComboBoxRevista.setItems(revistas);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Revista revistaSeleccionado = ComboBoxRevista.getSelectionModel().getSelectedItem();
        if (revistaSeleccionado != null) {
            // Eliminar el autor de la base de datos
            revistaDAO.deleteEntity(revistaSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            ComboBoxRevista.getItems().remove(revistaSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación de la revista se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una revista para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
