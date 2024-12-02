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


        System.out.println("Comienzan las pruebas de los AutorDAO");

        // Almacenar
        AutorDAO.build().store(autor1);
        AutorDAO.build().store(autor2);
        AutorDAO.build().store(autorDelete);

        // Buscar
        autor1 = AutorDAO.build().findId(autor1.getId());
        autor2 = AutorDAO.build().findId(autor2.getId());
        autorDelete = AutorDAO.build().findId(autorDelete.getId());

        System.out.println(autor1);
        System.out.println(autor2);
        System.out.println(autorDelete);

        // Actualizar
        autor1.setNacionalidad("Portugal");
        AutorDAO.build().store(autor1);

        // Delete
        AutorDAO.build().deleteEntity(autorDelete);

    }
}
