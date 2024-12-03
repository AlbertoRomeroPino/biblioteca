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

public class PantalladelaBasededatosPrestamos {

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

        // Configuración para los otros campos
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
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


    @FXML
    private void SwitchToPantalladeInicio() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEINICIO);
    }
    @FXML
    private void SwitchToPantalladePrestamos() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSPRESTAMOS);
    }
    @FXML
    private void SwitchToPantalladePublicaciones() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSPUBLICACION);
    }
    @FXML
    private void SwitchToPantalladeLibros() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSLIBRO);
    }
    @FXML
    private void SwitchToPantalladeRevistas() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSREVISTA);
    }
    @FXML
    private void SwitchToPantalladeAutores() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSAUTORES);
    }
    @FXML
    private void SwitchToPantalladeEditorial() throws IOException {
        // Cambiar la vista a la pantalla de Inicio
        App.setRoot(scenes.PANTALLADEBASADEDATOSEDITORIAL);
    }

    @FXML
    private void CreateNewUsuario() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createusuario.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Creación de Usuario");
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
    @FXML
    private void CreateNewPrestamo() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createprestamo.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Creación de Prestamo");
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
    @FXML
    private void CreateNewLibro() throws IOException {
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
    }
    @FXML
    private void CreateNewRevista() throws IOException {
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
    @FXML
    private void CreateNewAutor() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createautor.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Creación de Autor");
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
    @FXML
    private void CreateNewEditorial() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/createeditorial.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Creación de Editorial");
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

    @FXML
    private void ModifyUsuario() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifyusuario.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Usuario");
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
    @FXML
    private void ModifyPrestamo() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifyprestamo.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Prestamo");
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
    @FXML
    private void ModifyLibro() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifylibro.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Libro");
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
    @FXML
    private void ModifyRevista() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifyrevista.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Revista");
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
    @FXML
    private void ModifyAutor() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifyautor.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Autor");
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
    @FXML
    private void ModifyEditorial() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/modifyeditorial.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Modificación de Editorial");
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

    @FXML
    private void DeleteUsuario() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deleteusuario.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de  Eliminación de Usuario");
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
    @FXML
    private void DeletePrestamo() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deleteprestamo.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de  Eliminación de Prestamo");
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
    @FXML
    private void DeleteLibro() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deletelibro.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de  Eliminación de Libro");
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
    @FXML
    private void DeleteRevista() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deleterevista.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de  Eliminación de Revista");
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
    @FXML
    private void DeleteAutor() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deleteautor.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de  Eliminación de Autor");
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
    @FXML
    private void DeleteEditorial() throws IOException {
        try {
            // Cargar el FXML de PantalladeIdentificacion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/deleteeditorial.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana emergente)
            Stage popupStage = new Stage();
            popupStage.setTitle("Pantalla de Eliminación de Editorial");
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

}
