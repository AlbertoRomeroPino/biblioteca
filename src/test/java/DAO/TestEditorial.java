package DAO;

import model.dao.EditorialDAO;
import model.entity.Editorial;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestEditorial {
    public static void main(String[] args) {
        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());
        Editorial editorial3 = new Editorial(3, "HarperCollins", "Estados Unidos", LocalDate.of(1989, 8, 12), new ArrayList<>());
        Editorial editorial4 = new Editorial(4, "Simon & Schuster", "Estados Unidos", LocalDate.of(1924, 1, 2), new ArrayList<>());
        Editorial editorial5 = new Editorial(5, "Hachette Livre", "Francia", LocalDate.of(1826, 5, 25), new ArrayList<>());
        Editorial editorial6 = new Editorial(6, "Macmillan Publishers", "Reino Unido", LocalDate.of(1843, 1, 10), new ArrayList<>());
        Editorial editorial7 = new Editorial(7, "Bloomsbury", "Reino Unido", LocalDate.of(1986, 6, 27), new ArrayList<>());
        Editorial editorial8 = new Editorial(8, "Scholastic", "Estados Unidos", LocalDate.of(1920, 10, 22), new ArrayList<>());
        Editorial editorial9 = new Editorial(9, "Pearson Education", "Reino Unido", LocalDate.of(1844, 6, 15), new ArrayList<>());
        Editorial editorialDelete = new Editorial(10, "Editorial Africa", "África", LocalDate.of(1949, 12, 23), new ArrayList<>());

// Almacenar
        EditorialDAO.build().store(editorial1);
        EditorialDAO.build().store(editorial2);
        EditorialDAO.build().store(editorial3);
        EditorialDAO.build().store(editorial4);
        EditorialDAO.build().store(editorial5);
        EditorialDAO.build().store(editorial6);
        EditorialDAO.build().store(editorial7);
        EditorialDAO.build().store(editorial8);
        EditorialDAO.build().store(editorial9);
        EditorialDAO.build().store(editorialDelete);

// Buscar
        editorial1 = EditorialDAO.build().findId(1);
        editorial2 = EditorialDAO.build().findId(2);
        editorial3 = EditorialDAO.build().findId(3);
        editorial4 = EditorialDAO.build().findId(4);
        editorial5 = EditorialDAO.build().findId(5);
        editorial6 = EditorialDAO.build().findId(6);
        editorial7 = EditorialDAO.build().findId(7);
        editorial8 = EditorialDAO.build().findId(8);
        editorial9 = EditorialDAO.build().findId(9);
        editorialDelete = EditorialDAO.build().findId(10);

        System.out.println(editorial1);
        System.out.println(editorial2);
        System.out.println(editorial3);
        System.out.println(editorial4);
        System.out.println(editorial5);
        System.out.println(editorial6);
        System.out.println(editorial7);
        System.out.println(editorial8);
        System.out.println(editorial9);
        System.out.println(editorialDelete);


        // Actualizar
        editorial1.setPais("Alemania");
        EditorialDAO.build().store(editorial1);

        // Borrar
        EditorialDAO.build().deleteEntity(editorialDelete);
    }
}
