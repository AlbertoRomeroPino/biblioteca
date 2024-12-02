package org.chatta.controllers_and_view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.chatta.App;

import java.io.IOException;
import java.util.Objects;

public class PantalladeInicio {

    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerAudio;

    @FXML
    private MediaView mediaView;  // MediaView para mostrar el video

    @FXML
    private Pane rootPane; // Pane para acceder al tamaño de la ventana

    @FXML
    private void initialize() {
        // Ruta del archivo de video y audio
        String videoFile = "/org/chatta/assets/fondo.mp4";  // Asegúrate de tener el archivo de video en la ruta correcta
        String musicFile = "/org/chatta/assets/sonido.wav"; // Ruta del archivo de música

        // Crear el Media para el video
        Media videoMedia = new Media(Objects.requireNonNull(getClass().getResource(videoFile)).toExternalForm());

        // Crear el reproductor de video
        mediaPlayer = new MediaPlayer(videoMedia);
        mediaView.setMediaPlayer(mediaPlayer);

        // Crear el Media para el audio
        Media audioMedia = new Media(Objects.requireNonNull(getClass().getResource(musicFile)).toExternalForm());
        mediaPlayerAudio = new MediaPlayer(audioMedia);
        mediaPlayerAudio.setCycleCount(MediaPlayer.INDEFINITE);  // Música en loop
        mediaPlayerAudio.play();

        // Reproducir el video
        mediaPlayer.play();

        // Desactivar la preservación de la relación de aspecto para que el video se ajuste al tamaño de la ventana
        mediaView.setPreserveRatio(false);  // No mantener el ratio de aspecto del video
        mediaView.setSmooth(true); // Para suavizar la visualización

        // Listener para ajustar el tamaño del video cuando cambia el tamaño del Pane
        rootPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaView.setFitWidth(newValue.doubleValue());  // Ajusta el ancho del video
            }
        });

        rootPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaView.setFitHeight(newValue.doubleValue());  // Ajusta la altura del video
            }
        });
    }

    @FXML
    private void SwitchToPantalladeRegistro() throws IOException {
        // Detener el video y la música antes de cambiar de pantalla
        stopMediaPlayers();

        // Cargar el FXML de PantalladeRegistro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/registro.fxml"));
        Parent root = loader.load();

        // Crear un nuevo Stage (ventana emergente)
        Stage popupStage = new Stage();
        popupStage.setTitle("Pantalla de Registro");
        popupStage.initModality(Modality.APPLICATION_MODAL); // Hace que el popup sea modal
        popupStage.setScene(new Scene(root));

        // Configurar dimensiones predeterminadas
        popupStage.setWidth(600);  // Ancho de la ventana
        popupStage.setHeight(400); // Alto de la ventana

        // Opcional: Configurar tamaño mínimo y máximo
        popupStage.setMinWidth(300);
        popupStage.setMinHeight(200);
        popupStage.setMaxWidth(800);
        popupStage.setMaxHeight(600);

        // Mostrar el popup
        popupStage.show();

    }

    @FXML
    private void SwitchToPantalladeIdentificacion() throws IOException {
        // Detener el video y la música antes de cambiar de pantalla
        stopMediaPlayers();

        // Cargar el FXML de PantalladeIdentificacion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/identificacion.fxml"));
        Parent root = loader.load();

        // Crear un nuevo Stage (ventana emergente)
        Stage popupStage = new Stage();
        popupStage.setTitle("Pantalla de Identificación");
        popupStage.initModality(Modality.APPLICATION_MODAL); // Hace que el popup sea modal
        popupStage.setScene(new Scene(root));

        // Configurar dimensiones predeterminadas
        popupStage.setWidth(600);  // Ancho de la ventana
        popupStage.setHeight(400); // Alto de la ventana

        // Opcional: Configurar tamaño mínimo y máximo
        popupStage.setMinWidth(300);
        popupStage.setMinHeight(200);
        popupStage.setMaxWidth(800);
        popupStage.setMaxHeight(600);

        // Mostrar el popup
        popupStage.show();

    }

    @FXML
    private void SwitchToPantalladeCreditos() throws IOException {

        // Detener el video y la música antes de cambiar de pantalla
        stopMediaPlayers();

        // Cargar el FXML de PantalladeCreditos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/chatta/controllers_and_view/creditos.fxml"));
        Parent root = loader.load();

        // Crear un nuevo Stage (ventana emergente)
        Stage popupStage = new Stage();
        popupStage.setTitle("Pantalla de Créditos");
        popupStage.initModality(Modality.APPLICATION_MODAL); // Hace que el popup sea modal
        popupStage.setScene(new Scene(root));

        // Configurar dimensiones predeterminadas
        popupStage.setWidth(600);  // Ancho de la ventana
        popupStage.setHeight(400); // Alto de la ventana

        // Opcional: Configurar tamaño mínimo y máximo
        popupStage.setMinWidth(300);
        popupStage.setMinHeight(200);
        popupStage.setMaxWidth(800);
        popupStage.setMaxHeight(600);

        // Mostrar el popup
        popupStage.show();

    }

    @FXML
    private void toggleMute() {
        if (mediaPlayerAudio != null) {
            if (mediaPlayerAudio.getVolume() > 0) {
                // Si el volumen es mayor que 0, silenciarlo
                mediaPlayerAudio.setVolume(0);
            } else {
                // Si ya está silenciado, restaurar el volumen al nivel original
                mediaPlayerAudio.setVolume(1);
            }
        }
    }

    private void stopMediaPlayers() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();  // Liberar recursos
        }

        if (mediaPlayerAudio != null) {
            mediaPlayerAudio.stop();
            mediaPlayerAudio.dispose();  // Liberar recursos
        }
    }

    @FXML
    private void SwitchToPantalladelabasededatos() throws IOException {
        stopMediaPlayers();
        App.setRoot(scenes.PANTALLADEBASADEDATOS);
    }
}





