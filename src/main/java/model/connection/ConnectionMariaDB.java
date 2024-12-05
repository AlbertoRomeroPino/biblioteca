package model.connection;

import utils.ManagerXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMariaDB {
    private final static String FILE = "connection.xml";
    private static ConnectionMariaDB _instance;
    private Connection connection;

    /**
     * Constructor privado de la clase {@code ConnectionMariaDB}.
     * Establece una conexión con la base de datos utilizando las propiedades definidas en un archivo XML.
     * Si ocurre un error al intentar establecer la conexión, la conexión se inicializa como {@code null}.
     */
    private ConnectionMariaDB() {
        ConnectionProperties properties = (ConnectionProperties) ManagerXML.readXML(new ConnectionProperties(), FILE);

        try {
            connection = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    /**
     * Proporciona una instancia única de la conexión a la base de datos.
     * Si la conexión no ha sido creada, está cerrada o es nula, se crea una nueva instancia.
     *
     * @return Un objeto {@link Connection} que representa la conexión activa a la base de datos.
     */
    public static Connection getConnection() {
        if (_instance == null || _instance.connection == null || isConnectionClosed()) {
            _instance = new ConnectionMariaDB();
        }
        return _instance.connection;
    }

    /**
     * Cierra la conexión activa con la base de datos si existe.
     * Maneja posibles excepciones al cerrar la conexión.
     */
    public static void closeConnection() {
        if (_instance != null && _instance.connection != null) {
            try {
                _instance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifica si la conexión a la base de datos está cerrada o es nula.
     *
     * @return {@code true} si la conexión está cerrada o es nula; de lo contrario, {@code false}.
     */
    private static boolean isConnectionClosed() {
        try {
            return _instance.connection == null || _instance.connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}

