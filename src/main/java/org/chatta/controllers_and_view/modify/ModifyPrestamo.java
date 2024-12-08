package org.chatta.controllers_and_view.modify;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.dao.PrestamoDAO;
import model.dao.PublicacionDAO;
import model.dao.UsuarioDAO;
import model.entity.Enum.Estado_Enum;
import model.entity.Prestamo;
import model.entity.Publicacion;
import model.entity.Usuario;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class ModifyPrestamo {
    @FXML
    private ComboBox<Estado_Enum> ComboBoxestadoEnum;
    @FXML
    private ComboBox<Publicacion> comboboxPublicacion;
    @FXML
    private ComboBox<Usuario> comboboxUsuarios;
    @FXML
    public DatePicker fechaPrestamo;
    @FXML
    private Button closeButton;

    private PublicacionDAO publicacionDAO;
    private UsuarioDAO userDAO;

    public ModifyPrestamo() {
        // Inicializar el DAO

        userDAO = UsuarioDAO.build();
        publicacionDAO=PublicacionDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio

        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(userDAO.findAll());
        comboboxUsuarios.setItems(usuarios);
        if (comboboxUsuarios.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay usuarios disponibles",
                    "No se encontraron usuarios en la base de datos.");
            return;}
        ObservableList<Publicacion> publicaciones = FXCollections.observableArrayList(publicacionDAO.findAll());
        comboboxPublicacion.setItems(publicaciones);
        if (comboboxPublicacion.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay publicaciones disponibles",
                    "No se encontraron publicaciones en la base de datos.");
            return;}
        ObservableList<Estado_Enum> estados = FXCollections.observableArrayList(Estado_Enum.values());
        ComboBoxestadoEnum.setItems(estados);
        if (ComboBoxestadoEnum.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay estados disponibles",
                    "No se encontraron estados en la base de datos.");
            return;}
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

        Publicacion publicacionSeleccionado = comboboxPublicacion.getSelectionModel().getSelectedItem();
        Usuario usuarioSeleccionado = comboboxUsuarios.getSelectionModel().getSelectedItem();

        if (publicacionSeleccionado == null || usuarioSeleccionado == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "Por favor, selecciona un autor y un libro.");
            return;
        }
        if (fechaPrestamo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'fecha de Prestamo' está vacío. Por favor, ingréselo.");
            return;
        }
        // Si todos los campos están correctamente completados

        Prestamo prestamo = new Prestamo();
        prestamo.setPublicacion(publicacionSeleccionado);
        prestamo.setUsuario(usuarioSeleccionado);
        prestamo.setFechaPrestamo(fechaPrestamo.getValue());
        prestamo.setFechaDevolucion((fechaPrestamo.getValue().plusDays(30)));
        prestamo.setEstado(ComboBoxestadoEnum.getValue());

        PrestamoDAO.build().store(prestamo);

        showAlert(Alert.AlertType.INFORMATION, "Inserción Correcta", "La inserción se ha completado con éxito",
                "El prestamo ha sido registrado correctamente.");

        // Cambiar a la pantalla de autores después de insertar el autor
        App.setRoot(scenes.PANTALLADEBASADEDATOSPRESTAMOS);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
