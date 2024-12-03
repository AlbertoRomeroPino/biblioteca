package org.chatta.controllers_and_view.delete;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class DeleteUsuario {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Usuario> comboBoxUsuarios;

    private UsuarioDAO usuarioDAO;

    public DeleteUsuario() {
        // Inicializar el DAO
        usuarioDAO = UsuarioDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(usuarioDAO.findAll());
        comboBoxUsuarios.setItems(usuarios);
    }

    @FXML
    private void Close() throws IOException {
        // Eliminar el autor seleccionado
        Usuario ususarioSeleccionado = comboBoxUsuarios.getSelectionModel().getSelectedItem();
        if (ususarioSeleccionado != null) {
            // Eliminar el autor de la base de datos
            usuarioDAO.deleteEntity(ususarioSeleccionado);
            // Eliminar el autor de la lista del ComboBox
            comboBoxUsuarios.getItems().remove(ususarioSeleccionado);

            // Mostrar el mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación correcta");
            alert.setHeaderText("La eliminación ha sido completada con éxito");
            alert.setContentText("La eliminación del ususario se ha realizado correctamente.");
            alert.showAndWait();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección no válida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un ususario para eliminar.");
            alert.showAndWait();
        }

        // Cambiar la escena a la pantalla de autores
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

        // Cerrar la ventana actual
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
