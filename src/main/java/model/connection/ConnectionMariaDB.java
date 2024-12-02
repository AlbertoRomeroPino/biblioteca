package model.connection;

import utils.ManagerXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMariaDB {
    private final static String FILE = "connection.xml";
    private static ConnectionMariaDB _instance;
    private Connection connection;

    private ConnectionMariaDB() {
        ConnectionProperties properties = (ConnectionProperties) ManagerXML.readXML(new ConnectionProperties(), FILE);

        try {
            connection = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    public static Connection getConnection() {
        if (_instance == null || _instance.connection == null || isConnectionClosed()) {
            _instance = new ConnectionMariaDB();
        }
        return _instance.connection;
    }

    public static void closeConnection() {
        if (_instance != null && _instance.connection != null) {
            try {
                _instance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isConnectionClosed() {
        try {
            return _instance.connection == null || _instance.connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}

