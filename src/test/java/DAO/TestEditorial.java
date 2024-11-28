package DAO;

import model.dao.EditorialDAO;
import model.entity.Editorial;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestEditorial {
    public static void main(String[] args) {
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());
        Editorial editorialDelete = new Editorial(3, "Editorial", "Africa", LocalDate.of(1949,12,23), new ArrayList<>());

        // Almacenar
        EditorialDAO.build().store(editorial1);
        EditorialDAO.build().store(editorial2);
        EditorialDAO.build().store(editorialDelete);

        // Buscar
        editorial1 = EditorialDAO.build().findId(1);
        editorial2 = EditorialDAO.build().findId(2);
        editorialDelete = EditorialDAO.build().findId(3);

        System.out.println(editorial1);
        System.out.println(editorial2);
        System.out.println(editorialDelete);

        // Actualizar
        editorial1.setPais("Alemania");
        EditorialDAO.build().store(editorial1);

        // Borrar
        EditorialDAO.build().deleteEntity(editorialDelete);
    }
}
