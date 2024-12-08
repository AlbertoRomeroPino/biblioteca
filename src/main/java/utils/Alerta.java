package utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

    /**
     * Muestra una alerta emergente al usuario con un título, un mensaje y un tipo específico.
     *
     * @param titulo  El título de la ventana de la alerta.
     * @param mensaje El mensaje que se mostrará en el contenido de la alerta.
     * @param tipo    El tipo de alerta (por ejemplo, {@code AlertType.ERROR}, {@code AlertType.INFORMATION}).
     */
    public static void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
