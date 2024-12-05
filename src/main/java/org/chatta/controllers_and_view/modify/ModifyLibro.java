package org.chatta.controllers_and_view.modify;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.dao.LibroDAO;
import model.dao.PublicacionDAO;
import model.entity.Autor;
import model.entity.Libro;
import model.entity.Publicacion;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class ModifyLibro {

    @FXML
    private ComboBox<Autor> comboBoxAutores;
    @FXML
    private ComboBox<Publicacion> comboboxPublicacion;
    @FXML
    public TextField ISBN;
    @FXML
    private Button closeButton;

    private AutorDAO autorDAO;

    private PublicacionDAO publicacionDAO;

    public ModifyLibro() {
        // Inicializar el DAO
        autorDAO = AutorDAO.build();
        publicacionDAO=PublicacionDAO.build();
    }

    @FXML
    public void initialize() {
        // Cargar los autores en el ComboBox al inicio
        ObservableList<Autor> autores = FXCollections.observableArrayList(autorDAO.findAll());
        comboBoxAutores.setItems(autores);

        if (comboBoxAutores.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay autores disponibles",
                    "No se encontraron autores en la base de datos.");
            return;
        }

        ObservableList<Publicacion> publicaciones = FXCollections.observableArrayList(publicacionDAO.findAll());
        comboboxPublicacion.setItems(publicaciones);
        if (comboboxPublicacion.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Lista Vacía", "No hay publicaciones disponibles",
                    "No se publicaciones autores en la base de datos.");
            return;
        }
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

        Autor autorSeleccionado = comboBoxAutores.getSelectionModel().getSelectedItem();
        Publicacion publicacionSeleccionado = comboboxPublicacion.getSelectionModel().getSelectedItem();



        if (ISBN.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'ISBN' está vacío. Por favor, ingréselo.");
            return;
        }

        // Si todos los campos están correctamente completados

        Libro libro = new Libro();
        libro.setId(publicacionSeleccionado.getId());
        libro.setTitulo(publicacionSeleccionado.getTitulo());
        libro.setFecha_publicacion(publicacionSeleccionado.getFecha_publicacion());
        libro.setTipo(publicacionSeleccionado.getTipo());
        libro.setCategoria(publicacionSeleccionado.getCategoria());
        libro.setEditorial(publicacionSeleccionado.getEditorial());
        libro.setPrestamos(publicacionSeleccionado.getPrestamos());
        libro.setISBN(ISBN.getText());
        libro.setAutor(autorSeleccionado);

        LibroDAO.build().store(libro);

        showAlert(Alert.AlertType.INFORMATION, "Inserción Correcta", "La inserción se ha completado con éxito",
                "El libro ha sido registrado correctamente.");

        // Cambiar a la pantalla de autores después de insertar el autor
        App.setRoot(scenes.PANTALLADEBASADEDATOSLIBRO);

        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
