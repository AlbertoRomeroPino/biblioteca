package org.chatta.controllers_and_view;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.AutorDAO;
import model.entity.Autor;
import org.chatta.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;


public class PantalladelaBasededatosAutores extends PantalladelaBasededatos{


    @FXML
    private TableView<Autor> tablaAutores;
    @FXML
    private TableColumn<Autor, Integer> colId;
    @FXML
    private TableColumn<Autor, String> colNombre;
    @FXML
    private TableColumn<Autor, String> colNacionalidad;
    @FXML
    private TableColumn<Autor, LocalDate> colNacimiento;

    public void initialize() {
        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        // Cargar los autores
        cargarAutores();
    }

    private void cargarAutores() {
        // Obtén la lista de autores desde la base de datos o el DAO
        ObservableList<Autor> autores = FXCollections.observableArrayList(AutorDAO.build().findAll());

        // Asignar la lista de autores a la tabla
        tablaAutores.setItems(autores);
    }


}
