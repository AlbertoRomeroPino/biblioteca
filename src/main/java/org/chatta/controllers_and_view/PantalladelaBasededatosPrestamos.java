package org.chatta.controllers_and_view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.PrestamoDAO;
import model.entity.Prestamo;
import org.chatta.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;

public class PantalladelaBasededatosPrestamos extends PantalladelaBasededatos{

    @FXML
    private TableView<Prestamo> tablaPrestamos;

    @FXML
    private TableColumn<Prestamo, String> colUsuarioId;

    @FXML
    private TableColumn<Prestamo, String> colPublicacionId;

    @FXML
    private TableColumn<Prestamo, String> colFechaPrestamo;

    @FXML
    private TableColumn<Prestamo, String> colFechaDevolucion;

    @FXML
    private TableColumn<Prestamo, String> colEstado;


    public void initialize() {
        // Configurar las columnas para mostrar datos derivados
        colUsuarioId.setCellValueFactory(cellData -> {
            // Extraer el nombre del usuario, asegurándose de que no sea null
            return new SimpleStringProperty(
                    cellData.getValue().getUsuario() != null ?
                            cellData.getValue().getUsuario().getNombre() : "Desconocido"
            );
        });

        colPublicacionId.setCellValueFactory(cellData -> {
            // Extraer el título de la publicación, asegurándose de que no sea null
            return new SimpleStringProperty(
                    cellData.getValue().getPublicacion() != null ?
                            cellData.getValue().getPublicacion().getTitulo() : "Desconocido"
            );
        });

        // Configuración para la columna de la fecha de préstamo
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));

        // Configuración personalizada para la columna de la fecha de devolución
        colFechaDevolucion.setCellValueFactory(cellData -> {
            // Comprobar si la fecha de devolución es null y asignar un valor vacío en caso afirmativo
            java.sql.Date fechaDevolucion = Date.valueOf(cellData.getValue().getFechaDevolucion()); // Asegúrate de tener este método en tu clase Prestamo
            String fechaDevolucionStr = (fechaDevolucion != null) ? fechaDevolucion.toLocalDate().toString() : "";
            return new SimpleStringProperty(fechaDevolucionStr);
        });

        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Cargar los datos en la tabla
        cargarPrestamos();
    }

    private void cargarPrestamos() {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList(
                PrestamoDAO.build().findJoin()
        );
        tablaPrestamos.setItems(prestamos);
    }

}
