package org.chatta.controllers_and_view;

import javafx.beans.property.SimpleIntegerProperty;
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
import model.dao.EditorialDAO;
import model.entity.Editorial;
import org.chatta.App;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PantalladelaBasededatosEditorial extends PantalladelaBasededatos{

    private final EditorialDAO editorialDAO = EditorialDAO.build();


    @FXML
    private TableView<Editorial> tableView;

    @FXML
    private TableColumn<Editorial, Integer> idColumn;

    @FXML
    private TableColumn<Editorial, String> nombreColumn;

    @FXML
    private TableColumn<Editorial, String> paisColumn;

    @FXML
    private TableColumn<Editorial, LocalDate> fechaFundacionColumn;

    private final ObservableList<Editorial> editorialList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        paisColumn.setCellValueFactory(new PropertyValueFactory<>("pais"));
        fechaFundacionColumn.setCellValueFactory(new PropertyValueFactory<>("fecha_fundacion"));

        // Cargar los datos
        loadEditoriales();
    }

    private void loadEditoriales() {
        List<Editorial> editoriales = editorialDAO.findAll();

        // Cargar los datos en el TableView
        tableView.getItems().clear();
        tableView.getItems().addAll(editoriales);
    }

}
