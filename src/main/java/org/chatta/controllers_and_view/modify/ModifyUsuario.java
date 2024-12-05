package org.chatta.controllers_and_view.modify;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import org.chatta.App;
import org.chatta.controllers_and_view.scenes;
import utils.Validacion;

import java.io.IOException;
import java.util.List;

public class ModifyUsuario {

    @FXML
    private ComboBox<Usuario> comboUsuarios;
    @FXML
    private TextField nombre;
    @FXML
    private TextField clave;
    @FXML
    private TextField email;
    @FXML
    private Button closeButton;

    private ObservableList<Usuario> usuariosList;

    @FXML
    private void initialize() {
        cargarUsuarios();
        configurarComboBox();
    }

    /**
     * Carga la lista de usuarios en el ComboBox.
     */
    private void cargarUsuarios() {
        try {
            List<Usuario> usuarios = UsuarioDAO.build().findAll();
            usuariosList = FXCollections.observableArrayList(usuarios);
            comboUsuarios.setItems(usuariosList);
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", "No se pudo cargar la lista de usuarios", e.getMessage());
        }
    }

    /**
     * Configura el ComboBox para detectar cambios en la selección.
     */
    private void configurarComboBox() {
        comboUsuarios.setOnAction(event -> cargarDatosUsuario(comboUsuarios.getSelectionModel().getSelectedItem()));
    }

    /**
     * Carga los datos del usuario seleccionado en los campos de texto.
     *
     * @param usuario Usuario seleccionado.
     */
    private void cargarDatosUsuario(Usuario usuario) {
        if (usuario != null) {
            nombre.setText(usuario.getNombre());
            email.setText(usuario.getEMAIL());
            clave.setText(""); // No mostrar contraseñas por seguridad.
        }
    }

    /**
     * Guarda los cambios realizados en el usuario seleccionado.
     */
    @FXML
    private void guardarCambios() {
        Usuario usuarioSeleccionado = comboUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "No se ha seleccionado un usuario", "Seleccione un usuario del ComboBox para continuar.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            // Actualizar los datos del usuario seleccionado
            usuarioSeleccionado.setNombre(nombre.getText());
            usuarioSeleccionado.setEMAIL(email.getText());

            // Cifrar y establecer la nueva clave si ha sido modificada
            if (!clave.getText().isEmpty()) {
                String hashedClave = Validacion.encryptClave(clave.getText());
                usuarioSeleccionado.setClave(hashedClave);
            }

            // Guardar los cambios en la base de datos
            UsuarioDAO.build().store(usuarioSeleccionado);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Modificación Correcta", "Cambios guardados con éxito", "El usuario ha sido actualizado correctamente.");
            App.setRoot(scenes.PANTALLADEBASADEDATOS);

            cerrarVentana();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Modificación", "No se pudo guardar los cambios", e.getMessage());
        }
    }

    /**
     * Valida los campos de entrada.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    private boolean validarCampos() {
        if (nombre.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Nombre' no puede estar vacío.");
            return false;
        }
        if (email.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Campo vacío", "El campo 'Email' no puede estar vacío.");
            return false;
        }
        if (!Validacion.validacionEmail(email.getText())) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Formato Incorrecto", "El email proporcionado no es válido.");
            return false;
        }
        return true;
    }

    /**
     * Cierra la ventana actual.
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta con los detalles proporcionados.
     *
     * @param tipo      Tipo de alerta.
     * @param titulo    Título de la alerta.
     * @param cabecera  Cabecera de la alerta.
     * @param contenido Contenido del mensaje.
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
