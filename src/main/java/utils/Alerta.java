package utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {


    public static void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
