import model.connection.ConnectionProperties;
import utils.ManagerXML;

public class ConnectionSave {
    public static void main(String[] arg) {
        ConnectionProperties connection = new ConnectionProperties
                ("localhost", "3306", "biblioteca", "root", "root");
        ManagerXML.writeXML(connection, "connection.xml");
    }
}
