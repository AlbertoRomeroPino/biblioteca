package DAO;

import model.dao.AutorDAO;
import model.entity.Autor;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestAutor {
    public static void main(String[] args) {
        Autor autor1 = new Autor(1, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), new ArrayList<>());
        Autor autor2 = new Autor(2, "Isaac Asimov", "Rusa-Estadounidense", LocalDate.of(1920, 1, 2), new ArrayList<>());
        Autor autorDelete = new Autor(3, "Pepe","Español",LocalDate.of(1999, 1,3), new ArrayList<>());
        Autor autor3 = new Autor(4, "J.K. Rowling", "Británica", LocalDate.of(1965, 7, 31), new ArrayList<>());
        Autor autor4 = new Autor(5, "George Orwell", "Británica", LocalDate.of(1903, 6, 25), new ArrayList<>());
        Autor autor5 = new Autor(6, "Jane Austen", "Británica", LocalDate.of(1775, 12, 16), new ArrayList<>());
        Autor autor6 = new Autor(7, "Haruki Murakami", "Japonesa", LocalDate.of(1949, 1, 12), new ArrayList<>());
        Autor autor7 = new Autor(8, "Toni Morrison", "Estadounidense", LocalDate.of(1931, 2, 18), new ArrayList<>());
        Autor autor8 = new Autor(9, "Franz Kafka", "Austriaca", LocalDate.of(1883, 7, 3), new ArrayList<>());
        Autor autor9 = new Autor(10, "Virginia Woolf", "Británica", LocalDate.of(1882, 1, 25), new ArrayList<>());
        Autor autor10 = new Autor(11, "Pablo Neruda", "Chilena", LocalDate.of(1904, 7, 12), new ArrayList<>());



        System.out.println("Comienzan las pruebas de los AutorDAO");

        // Almacenar
        AutorDAO.build().store(autor1);
        AutorDAO.build().store(autor2);
        AutorDAO.build().store(autor3);
        AutorDAO.build().store(autor4);
        AutorDAO.build().store(autor5);
        AutorDAO.build().store(autor6);
        AutorDAO.build().store(autor7);
        AutorDAO.build().store(autor8);
        AutorDAO.build().store(autor9);
        AutorDAO.build().store(autor10);
        AutorDAO.build().store(autorDelete);

        // Buscar
        autor1 = AutorDAO.build().findId(autor1.getId());
        autor2 = AutorDAO.build().findId(autor2.getId());
        autor3 = AutorDAO.build().findId(autor3.getId());
        autor4 = AutorDAO.build().findId(autor4.getId());
        autor5 = AutorDAO.build().findId(autor5.getId());
        autor6 = AutorDAO.build().findId(autor6.getId());
        autor7 = AutorDAO.build().findId(autor7.getId());
        autor8 = AutorDAO.build().findId(autor8.getId());
        autor9 = AutorDAO.build().findId(autor9.getId());
        autor10 = AutorDAO.build().findId(autor10.getId());
        autorDelete = AutorDAO.build().findId(autorDelete.getId());

        System.out.println(autor1);
        System.out.println(autor2);
        System.out.println(autor3);
        System.out.println(autor4);
        System.out.println(autor5);
        System.out.println(autor6);
        System.out.println(autor7);
        System.out.println(autor8);
        System.out.println(autor9);
        System.out.println(autor10);
        System.out.println(autorDelete);

        // Actualizar
        autor1.setNacionalidad("Portugal");
        AutorDAO.build().store(autor1);

        // Delete
        AutorDAO.build().deleteEntity(autorDelete);

    }
}
