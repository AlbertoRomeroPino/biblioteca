package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ManagerXML {

    /**
     * Escribe un objeto en formato XML en un archivo.
     *
     * @param <T>        El tipo del objeto que se escribirá en XML.
     * @param connection El objeto a serializar en formato XML.
     * @param filename   El nombre del archivo donde se almacenará el XML.
     * @return {@code true} si la escritura fue exitosa, {@code false} en caso de error.
     * @throws JAXBException Si ocurre un error durante la creación del contexto o el proceso de serialización.
     */
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

    /**
     * Lee un archivo XML y lo convierte en un objeto de tipo específico.
     *
     * @param <T>        El tipo del objeto que se espera como resultado.
     * @param connection Una instancia del objeto que define el tipo de clase esperada.
     * @param filename   El nombre del archivo XML que se leerá.
     * @return El objeto deserializado desde el archivo XML, o el objeto original si ocurre un error.
     * @throws JAXBException Si ocurre un error durante la creación del contexto o el proceso de deserialización.
     */
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
