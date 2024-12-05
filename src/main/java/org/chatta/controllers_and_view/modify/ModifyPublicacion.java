package org.chatta.controllers_and_view.modify;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.CategoriaDAO;
import model.dao.EditorialDAO;
import model.dao.PublicacionDAO;
import model.entity.Categoria;
import model.entity.Editorial;
import model.entity.Enum.Tipo_Enum;
import model.entity.Publicacion;
import org.chatta.App;
import org.chatta.controllers_and_view.PantalladelaBasededatos;
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class ModifyPublicacion extends PantalladelaBasededatos {

    @FXML
    public TextField nombre;
    @FXML
    public DatePicker fecha_publicacion;
    @FXML
    private ComboBox<Tipo_Enum> ComboBoxtipoEnum;
    @FXML
    private ComboBox<Categoria> comboBoxCategoria;
    @FXML
    private ComboBox<Editorial> comboBoxEditorial;
    @FXML
    private ComboBox<Publicacion> comboboxPublicacion;

    private EditorialDAO editorialDAO;
    private CategoriaDAO categoriaDAO;
    private PublicacionDAO publicacionDAO;
    @FXML
    private Button closeButton;

    public ModifyPublicacion() {
        // Inicializar el DAO
        publicacionDAO = PublicacionDAO.build();
        editorialDAO = EditorialDAO.build();
        categoriaDAO = CategoriaDAO.build();
    }

    public void initialize() {
        ObservableList<Tipo_Enum> tipos = FXCollections.observableArrayList(Tipo_Enum.values());
        ComboBoxtipoEnum.setItems(tipos);
        ObservableList<Categoria> categorias = FXCollections.observableArrayList(categoriaDAO.findAll());
        comboBoxCategoria.setItems(categorias);
        ObservableList<Editorial> editoriales = FXCollections.observableArrayList(editorialDAO.findAll());
        comboBoxEditorial.setItems(editoriales);
        ObservableList<Publicacion> publicaciones = FXCollections.observableArrayList(publicacionDAO.findAll());
        comboboxPublicacion.setItems(publicaciones);
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

        if (nombre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Nombre' está vacío. Por favor, ingréselo.");
            return;
        }
        if (ComboBoxtipoEnum.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Tipo' está vacío. Por favor, ingréselo.");
            return;
        }
        if (comboBoxEditorial.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Editorial' está vacío. Por favor, ingréselo.");
            return;
        }
        if (comboBoxCategoria.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Categoria' está vacío. Por favor, ingréselo.");
            return;
        }
        if (fecha_publicacion.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Fecha de Nacimiento' está vacío. Por favor, ingréselo.");
            return;
        }
        if (comboboxPublicacion.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Inserción Incorrecta", "La inserción no ha sido completada con éxito",
                    "El campo 'Publicacion' está vacío. Por favor, ingréselo.");
            return;
        }

        // Si todos los campos están correctamente completados
        Publicacion publicacion = new Publicacion();
        publicacion.setId(comboboxPublicacion.getValue().getId());
        publicacion.setTitulo(nombre.getText());
        publicacion.setFecha_publicacion(fecha_publicacion.getValue());
        publicacion.setTipo(ComboBoxtipoEnum.getValue());
        publicacion.setEditorial(comboBoxEditorial.getValue());
        publicacion.setCategoria(comboBoxCategoria.getValue());

        PublicacionDAO.build().store(publicacion);

        showAlert(Alert.AlertType.INFORMATION, "Inserción Correcta", "La inserción se ha completado con éxito",
                "El autor ha sido registrado correctamente.");

        // Cambiar a la pantalla de autores después de insertar el autor
        App.setRoot(scenes.PANTALLADEBASADEDATOSPUBLICACION);

        if(ComboBoxtipoEnum.getValue().equals(Tipo_Enum.Libro)) {
            ModifyLibro();
        } else if(ComboBoxtipoEnum.getValue().equals(Tipo_Enum.Revista)){
           ModifyRevista();
        }
        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
