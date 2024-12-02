package org.chatta.controllers_and_view;

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

public class PantalladelaBasededatosLibros {

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
        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("publicacion"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        // Cargar los libros
        cargarLibros();
    }

    private void cargarLibros() {
        // Obtén la lista de libros desde la base de datos o el DAO
        ObservableList<Libro> libros = FXCollections.observableArrayList(LibroDAO.build().findAll());

        // Asignar la lista de libros a la tabla
        tablaLibros.setItems(libros);
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
