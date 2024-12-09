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
    public DatePicker fechaDevolucion;
    @FXML
    private ComboBox<Prestamo> comboBoxPrestamo;
    @FXML
    private Button closeButton;

    private PrestamoDAO prestamoDAO;
    public ModifyPrestamo() {
        // Inicializar el DAO
        prestamoDAO = PrestamoDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio

        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList(prestamoDAO.findAll());

        comboBoxPrestamo.setItems(prestamos);

        if (comboBoxPrestamo.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay Prestamos disponibles",
                    "No se encontraron Prestamo en la base de datos.");
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

        Prestamo prestamo = comboBoxPrestamo.getSelectionModel().getSelectedItem();

        if (fechaDevolucion.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'fecha de Prestamo' está vacío. Por favor, ingréselo.");
            return;
        }
        // Si todos los campos están correctamente completados


        prestamo.setFechaDevolucion(fechaDevolucion.getValue());
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
