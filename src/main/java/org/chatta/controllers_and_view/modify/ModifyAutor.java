package org.chatta.controllers_and_view.modify;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.entity.Autor;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;
import java.util.List;

public class ModifyAutor {

    @FXML
    public DatePicker fecha_nacimiento;
    @FXML
    public TextField nombre;
    @FXML
    public TextField nacionalidad;
    @FXML
    private Button closeButton;
    @FXML
    private ComboBox<Autor> comboBoxAutores; // ComboBox para seleccionar un autor

    private Autor selectedAutor; // Autor seleccionado en el ComboBox

    @FXML
    private void initialize() {
        loadAutores(); // Cargar los autores en el ComboBox al iniciar

        // Acción cuando se selecciona un autor en el ComboBox
        comboBoxAutores.setOnAction(event -> loadSelectedAutor());
    }

    // Cargar los autores en el ComboBox
    private void loadAutores() {
        List<Autor> autores = AutorDAO.build().findAll();
        comboBoxAutores.getItems().clear();
        comboBoxAutores.getItems().addAll(autores);
    }

    // Cargar los datos del autor seleccionado
    private void loadSelectedAutor() {
        selectedAutor = comboBoxAutores.getSelectionModel().getSelectedItem();
        if (selectedAutor != null) {
            nombre.setText(selectedAutor.getNombre());
            nacionalidad.setText(selectedAutor.getNacionalidad());
            fecha_nacimiento.setValue(selectedAutor.getFechaNacimiento());
        }
    }

    @FXML
    private void close() throws IOException {
        if (selectedAutor != null) {
            // Modificar el autor
            selectedAutor.setNombre(nombre.getText());
            selectedAutor.setNacionalidad(nacionalidad.getText());
            selectedAutor.setFechaNacimiento(fecha_nacimiento.getValue());

            // Llamar al DAO para almacenar la modificación
            AutorDAO.build().store(selectedAutor);

            // Mostrar alerta de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modificación Correcta");
            alert.setHeaderText("La modificación ha sido completada con éxito");
            alert.setContentText("La modificación del autor ha sido realizada con éxito.");
            alert.showAndWait();

            // Cambiar a la vista de la base de datos de autores
            App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);

            // Cerrar la ventana actual
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } else {
            // Si no se ha seleccionado un autor, mostrar un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se puede modificar el autor");
            alert.setContentText("No se ha seleccionado ningún autor.");
            alert.showAndWait();
        }
    }
}

