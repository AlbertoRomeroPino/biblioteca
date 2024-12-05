package org.chatta.controllers_and_view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.PublicacionDAO;
import model.entity.Publicacion;
import org.chatta.App;

import java.io.IOException;

public class PantalladelaBasededatosPublicaciones extends PantalladelaBasededatos{

    @FXML
    private TableView<Publicacion> tablaPublicaciones;

    @FXML
    private TableColumn<Publicacion, Integer> colId;

    @FXML
    private TableColumn<Publicacion, String> colTitulo;

    @FXML
    private TableColumn<Publicacion, String> colFechaPublicacion;

    @FXML
    private TableColumn<Publicacion, String> colTipo;

    @FXML
    private TableColumn<Publicacion, String > colCategoriaId;

    @FXML
    private TableColumn<Publicacion, String> colEditorialId;

    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fecha_publicacion"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // Configuración para mostrar el nombre de la categoría
        colCategoriaId.setCellValueFactory(cellData -> {
            // Extraer el nombre de la categoría desde el objeto Publicacion
            return new SimpleStringProperty(
                    cellData.getValue().getCategoria().getNombre()
            );
        });

        // Configuración para mostrar el nombre de la editorial
        colEditorialId.setCellValueFactory(cellData -> {
            // Extraer el nombre de la editorial desde el objeto Publicacion
            return new SimpleStringProperty(
                    cellData.getValue().getEditorial().getNombre()
            );
        });

        // Cargar los datos en la tabla
        cargarPublicaciones();
    }

    private void cargarPublicaciones() {
        // Obtener datos usando el método que realiza el JOIN
        ObservableList<Publicacion> publicaciones = FXCollections.observableArrayList(
                PublicacionDAO.build().findJoinPublicacion()  // Asegúrate de que este método devuelve una lista con los datos
        );
        tablaPublicaciones.setItems(publicaciones);  // Establecer los elementos de la tabla
    }
}
