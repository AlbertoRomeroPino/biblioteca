package org.chatta.controllers_and_view.modify;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dao.EditorialDAO;
import model.entity.Editorial;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;
import java.util.List;

public class ModifyEditorial {

    @FXML
    private TextField nombre;
    @FXML
    private TextField pais;
    @FXML
    private DatePicker fecha_fundacion;
    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<Editorial> comboBoxEditoriales;

    private Editorial selectedEditorial;

    @FXML
    private void initialize() {
        cargarEditoriales();
        comboBoxEditoriales.setOnAction(event -> cargarEditorialSeleccionada());
    }

    private void cargarEditoriales() {
        try {
            List<Editorial> editoriales = EditorialDAO.build().findAll();
            if (editoriales.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Lista Vacía", "No hay editoriales disponibles", "No se encontraron editoriales en la base de datos.");
            } else {
                comboBoxEditoriales.getItems().clear();
                comboBoxEditoriales.getItems().addAll(editoriales);
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", "No se pudieron cargar las editoriales.", e.getMessage());
        }
    }

    private void cargarEditorialSeleccionada() {
        selectedEditorial = comboBoxEditoriales.getSelectionModel().getSelectedItem();
        if (selectedEditorial != null) {
            nombre.setText(selectedEditorial.getNombre());
            pais.setText(selectedEditorial.getPais());
            fecha_fundacion.setValue(selectedEditorial.getFecha_fundacion());
        }
    }

    @FXML
    private void close() throws IOException {
        if (selectedEditorial == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se puede modificar la editorial", "No se ha seleccionado ninguna editorial.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            selectedEditorial.setNombre(nombre.getText());
            selectedEditorial.setPais(pais.getText());
            selectedEditorial.setFecha_fundacion(fecha_fundacion.getValue());

            EditorialDAO.build().store(selectedEditorial);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Modificación Correcta", "Modificación realizada con éxito", "La editorial se ha actualizado correctamente.");
            App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);

            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar la modificación.", e.getMessage());
        }
    }

    private boolean validarCampos() {
        if (nombre.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Nombre' no puede estar vacío.");
            return false;
        }
        if (pais.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'País' no puede estar vacío.");
            return false;
        }
        if (fecha_fundacion.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Fecha de Fundación' no puede estar vacío.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
