package org.chatta.controllers_and_view.modify;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.entity.Autor;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;
import java.util.List;

public class ModifyAutor {

    @FXML
    private DatePicker fecha_nacimiento;
    @FXML
    private TextField nombre;
    @FXML
    private TextField nacionalidad;
    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<Autor> comboBoxAutores;

    private Autor selectedAutor;

    @FXML
    private void initialize() {
        cargarAutores();
        comboBoxAutores.setOnAction(event -> cargarAutorSeleccionado());
    }

    private void cargarAutores() {
        try {
            List<Autor> autores = AutorDAO.build().findAll();
            if (autores.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Lista Vacía", "No hay autores disponibles", "No se encontraron autores en la base de datos.");
            } else {
                comboBoxAutores.getItems().clear();
                comboBoxAutores.getItems().addAll(autores);
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", "No se pudieron cargar los autores.", e.getMessage());
        }
    }

    private void cargarAutorSeleccionado() {
        selectedAutor = comboBoxAutores.getSelectionModel().getSelectedItem();
        if (selectedAutor != null) {
            nombre.setText(selectedAutor.getNombre());
            nacionalidad.setText(selectedAutor.getNacionalidad());
            fecha_nacimiento.setValue(selectedAutor.getFechaNacimiento());
        }
    }

    @FXML
    private void close() throws IOException {
        if (selectedAutor == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se puede modificar el autor", "No se ha seleccionado ningún autor.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            selectedAutor.setId(selectedAutor.getId());
            selectedAutor.setNombre(nombre.getText());
            selectedAutor.setNacionalidad(nacionalidad.getText());
            selectedAutor.setFechaNacimiento(fecha_nacimiento.getValue());

            AutorDAO.build().store(selectedAutor); //store funciona tambien como update

            mostrarAlerta(Alert.AlertType.INFORMATION, "Modificación Correcta", "Modificación realizada con éxito", "El autor se ha actualizado correctamente.");
            App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

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
        if (nacionalidad.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Nacionalidad' no puede estar vacío.");
            return false;
        }
        if (fecha_nacimiento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Fecha de Nacimiento' no puede estar vacío.");
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
