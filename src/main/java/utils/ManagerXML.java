package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ManagerXML {
    public static <T> boolean writeXML(T connection, String filename) {
        boolean write = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(connection.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(connection, new File(filename));

            write = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return write;
    }

    public static <T> T readXML(T connection, String filename) {
        T result = connection;
        JAXBContext context;

        try {
            context = JAXBContext.newInstance(connection.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

}
