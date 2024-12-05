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
import model.dao.RevistaDAO;
import model.entity.Categoria;
import model.entity.Editorial;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Libro;
import model.entity.Revista;
import org.chatta.App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PantalladelaBasededatosRevistas extends PantalladelaBasededatos{

    @FXML
    private TableView<Revista> tableView;

    @FXML
    private TableColumn<Revista, Integer> idColumn;

    @FXML
    private TableColumn<Revista, String> tituloColumn;

    @FXML
    private TableColumn<Revista, String> fechaPublicacionColumn;

    @FXML
    private TableColumn<Revista, String> categoriaColumn;

    @FXML
    private TableColumn<Revista, String> editorialColumn;

    @FXML
    private TableColumn<Revista, String> issnColumn;

    @FXML
    private TableColumn<Revista, String> periodicidadColumn;

    @FXML
    public void initialize() {
        // Configurar las columnas
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        fechaPublicacionColumn.setCellValueFactory(cellData -> {
            // Obtener la fecha de publicación y convertirla a formato String
            LocalDate fechaPublicacion = cellData.getValue().getFecha_publicacion();
            String fechaString = (fechaPublicacion != null) ? fechaPublicacion.toString() : "";
            return new SimpleStringProperty(fechaString);
        });        categoriaColumn.setCellValueFactory(data -> {
            Categoria categoria = data.getValue().getCategoria();
            return new javafx.beans.property.SimpleStringProperty(categoria != null ? categoria.getNombre() : "");
        });
        editorialColumn.setCellValueFactory(data -> {
            Editorial editorial = data.getValue().getEditorial();
            return new javafx.beans.property.SimpleStringProperty(editorial != null ? editorial.getNombre() : "");
        });
        issnColumn.setCellValueFactory(new PropertyValueFactory<>("ISSN"));
        periodicidadColumn.setCellValueFactory(new PropertyValueFactory<>("periodicidad"));

        // Obtener datos para la tabla
       cargarRevistas();
    }

    private void cargarRevistas() {
        // Obtener las revistas usando el método que devuelve los resultados del JOIN
        ObservableList<Revista> revistas = FXCollections.observableArrayList(RevistaDAO.build().findJoinRevista());
        tableView.setItems(revistas);
    }

}
