package org.chatta.controllers_and_view;


public enum scenes {
    PANTALLADEINICIO("controllers_and_view/inicio.fxml"),
    PANTALLADECREDITOS("controllers_and_view/creditos.fxml"),
    PANTALLADEBASADEDATOS("controllers_and_view/basededatos.fxml"),
    PANTALLADEBASADEDATOSPRESTAMOS("controllers_and_view/prestamos.fxml"),
    PANTALLADEBASADEDATOSPUBLICACION("controllers_and_view/publicación.fxml"),
    PANTALLADEBASADEDATOSLIBRO("controllers_and_view/libro.fxml"),
    PANTALLADEBASADEDATOSREVISTA("controllers_and_view/revista.fxml"),
    PANTALLADEBASADEDATOSAUTORES("controllers_and_view/autores.fxml"),
    PANTALLADEBASADEDATOSEDITORIAL("controllers_and_view/editorial.fxml"),
    PANTALLADEIDENTIFICACION("controllers_and_view/identificacion.fxml"),
    PANTALLADEREGISTRO("controllers_and_view/registro.fxml");


    private String url;

    scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

}
