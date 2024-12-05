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
import org.chatta.controllers_and_view.scenes;

import java.io.IOException;

public class ModifyPublicacion {

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

    private EditorialDAO editorialDAO;
    private CategoriaDAO categoriaDAO;

    @FXML
    private Button closeButton;

    public ModifyPublicacion() {
        // Inicializar el DAO
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

        // Si todos los campos están correctamente completados
        Publicacion publicacion = new Publicacion();
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
            try {
                // Cargar el FXML de PantalladeIdentificacion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createlibro.fxml"));
                Parent root = loader.load();

                // Crear un nuevo Stage (ventana emergente)
                Stage popupStage = new Stage();
                popupStage.setTitle("Pantalla de Creación de Libro");
                popupStage.initModality(Modality.APPLICATION_MODAL); // Hace que el popup sea modal
                popupStage.setScene(new Scene(root));

                // Configurar dimensiones predeterminadas
                popupStage.setWidth(300);  // Ancho de la ventana
                popupStage.setHeight(350); // Alto de la ventana

                // Opcional: Configurar tamaño mínimo y máximo
                popupStage.setMinWidth(300);
                popupStage.setMinHeight(350);
                popupStage.setMaxWidth(300);
                popupStage.setMaxHeight(350);

                // Mostrar el popup
                popupStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(ComboBoxtipoEnum.getValue().equals(Tipo_Enum.Revista)){
            try {
                // Cargar el FXML de PantalladeIdentificacion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createrevista.fxml"));
                Parent root = loader.load();

                // Crear un nuevo Stage (ventana emergente)
                Stage popupStage = new Stage();
                popupStage.setTitle("Pantalla de Creación de Revista");
                popupStage.initModality(Modality.APPLICATION_MODAL); // Hace que el popup sea modal
                popupStage.setScene(new Scene(root));

                // Configurar dimensiones predeterminadas
                popupStage.setWidth(300);  // Ancho de la ventana
                popupStage.setHeight(350); // Alto de la ventana

                // Opcional: Configurar tamaño mínimo y máximo
                popupStage.setMinWidth(300);
                popupStage.setMinHeight(350);
                popupStage.setMaxWidth(300);
                popupStage.setMaxHeight(350);

                // Mostrar el popup
                popupStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Cerrar la ventana
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
