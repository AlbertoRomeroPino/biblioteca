package org.chatta.controllers_and_view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.LibroDAO;
import model.entity.Libro;
import org.chatta.App;
import javafx.collections.FXCollections;

import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class PantalladelaBasededatosLibros extends PantalladelaBasededatos{

    @FXML
    private TableView<Libro> tablaLibros;
    @FXML
    private TableColumn<Libro, Integer> colId;
    @FXML
    private TableColumn<Libro, String> colTitulo;
    @FXML
    private TableColumn<Libro, String> colPublicacion;
    @FXML
    private TableColumn<Libro, String> colCategoria;
    @FXML
    private TableColumn<Libro, String> colEditorial;
    @FXML
    private TableColumn<Libro, String> colIsbn;
    @FXML
    private TableColumn<Libro, String> colAutor;

    public void initialize() {
        // Configurar las columnas para mostrar los datos derivados de la consulta JOIN
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        // Convertir la fecha de la publicación (Date) a String para mostrarla
        colPublicacion.setCellValueFactory(cellData -> {
            // Obtener la fecha de publicación y convertirla a formato String
            LocalDate fechaPublicacion = cellData.getValue().getFecha_publicacion();
            String fechaString = (fechaPublicacion != null) ? fechaPublicacion.toString() : "";
            return new SimpleStringProperty(fechaString);
        });

        colCategoria.setCellValueFactory(cellData -> {
            // Extraer el nombre de la categoría
            return new SimpleStringProperty(cellData.getValue().getCategoria().getNombre());
        });
        colEditorial.setCellValueFactory(cellData -> {
            // Extraer el nombre de la editorial
            return new SimpleStringProperty(cellData.getValue().getEditorial().getNombre());
        });
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colAutor.setCellValueFactory(cellData -> {
            // Extraer el nombre del autor
            return new SimpleStringProperty(cellData.getValue().getAutor().getNombre());
        });

        // Cargar los datos en la tabla
        cargarLibros();
    }

    private void cargarLibros() {
        // Obtener los libros usando el método que devuelve los resultados del JOIN
        ObservableList<Libro> libros = FXCollections.observableArrayList(
                LibroDAO.build().findJoinLibro()
        );
        tablaLibros.setItems(libros);
    }



}
