module org.chatta {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;  // Asegúrate de que este módulo esté incluido
    requires javafx.graphics;
    requires java.sql;
    requires java.xml.bind;  // Si estás usando JAXB
    opens model.connection to java.xml.bind; // Permite acceso a JAXB

    exports org.chatta;  // Ajusta esto según el paquete principal

    opens org.chatta.controllers_and_view to javafx.fxml;
    opens org.chatta.controllers_and_view.create to javafx.fxml;  // Abre el paquete donde se encuentran tus controladores
    opens org.chatta.controllers_and_view.modify to javafx.fxml;
    opens org.chatta.controllers_and_view.delete to javafx.fxml;
}


