module org.chatta {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;  // Asegúrate de que este módulo esté incluido
    requires javafx.graphics;
    requires java.sql;
    requires java.xml.bind;  // Si estás usando JAXB

    exports org.chatta;  // Ajusta esto según el paquete principal
    opens org.chatta.controllers_and_view to javafx.fxml;  // Abre el paquete donde se encuentran tus controladores
}

