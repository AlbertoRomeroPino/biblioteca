package org.chatta.controllers_and_view;

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
import model.entity.Revista;
import org.chatta.App;

import java.io.IOException;
import java.util.List;

public class PantalladelaBasededatosRevistas {

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
        // Configuración de columnas
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        fechaPublicacionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        issnColumn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        periodicidadColumn.setCellValueFactory(new PropertyValueFactory<>("periodicidad"));

        // Cargar datos desde RevistaDAO
        List<Revista> revistas = RevistaDAO.build().findAll();
        ObservableList<Revista> data = FXCollections.observableArrayList(revistas);
        tableView.setItems(data);
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
