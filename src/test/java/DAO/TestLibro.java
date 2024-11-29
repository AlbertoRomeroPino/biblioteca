package DAO;

import model.dao.LibroDAO;
import model.entity.Autor;
import model.entity.Categoria;
import model.entity.Editorial;
import model.entity.Enum.Tipo_Enum;
import model.entity.Libro;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestLibro {

    public static void main(String[] args) { // Crear Categorías
        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());
        Categoria categoriaDelete = new Categoria(3, "Tecnologia", new ArrayList<>());

        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());
        Editorial editorialDelete = new Editorial(3, "Editorial", "Africa", LocalDate.of(1949, 12, 23), new ArrayList<>());

        // Crear Autores
        Autor autor1 = new Autor(1, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), new ArrayList<>());
        Autor autor2 = new Autor(2, "Isaac Asimov", "Rusa-Estadounidense", LocalDate.of(1920, 1, 2), new ArrayList<>());
        Autor autorDelete = new Autor(3, "Pepe", "Español", LocalDate.of(1999, 1, 3), new ArrayList<>());

        // Crear Libros
        Libro libro1 = new Libro(1, "Cien Años de Soledad", LocalDate.of(1967, 5, 30), Tipo_Enum.Libro, categoria1, editorial1, new ArrayList<>(), "123-456-789", autor1);
        Libro libro2 = new Libro(2, "Fundación", LocalDate.of(1951, 6, 1), Tipo_Enum.Libro, categoria2, editorial2, new ArrayList<>(), "987-654-321", autor2);
        Libro libroDelete = new Libro(3, "Harry potter", LocalDate.of(200, 4, 5), Tipo_Enum.Libro, categoria1, editorial2, new ArrayList<>(), "123-333-222", autor1);

        System.out.println("Comienzan las pruebas de los AutorDAO");

        LibroDAO.build().store(libro1);
        LibroDAO.build().store(libro2);
        LibroDAO.build().store(libroDelete);

        libro1 = LibroDAO.build().findId(1);
        libro2 = LibroDAO.build().findId(2);
        libroDelete = LibroDAO.build().findId(3);

        System.out.println(libro1);
        System.out.println(libro2);
        System.out.println(libroDelete);

        libro1.setISBN("1234 4321");
        LibroDAO.build().store(libro1);

        LibroDAO.build().deleteEntity(libroDelete);


    }
}
