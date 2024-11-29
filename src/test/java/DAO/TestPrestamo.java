package DAO;

import model.dao.PrestamoDAO;
import model.entity.*;
import model.entity.Enum.Estado_Enum;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Enum.Tipo_Enum;
import utils.Validacion;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestPrestamo {
    public static void main(String[] args) {
        // Crear Usuarios
        String clave1 = Validacion.encryptClave("clave123");
        String clave2 = Validacion.encryptClave("clave456");


        Usuario usuario1 = new Usuario(1, "Juan Pérez", clave1, "juan.perez@example.com", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "María López", clave2, "maria.lopez@example.com", new ArrayList<>());


        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());

        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());

        // Crear Autores
        Autor autor1 = new Autor(1, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), new ArrayList<>());

        //Libro-Revista
        Libro libro1 = new Libro(1, "Cien Años de Soledad", LocalDate.of(1967, 5, 30), Tipo_Enum.Libro, categoria1, editorial1, new ArrayList<>(), "123-456-789", autor1);
        Revista revista1 = new Revista(4, "National Geographic", LocalDate.of(2023, 1, 1), Tipo_Enum.Revista, categoria1, editorial1, new ArrayList<>(), "1234-5678", Periodicidad_Enum.Anual);
        Revista revista2 = new Revista(5, "Scientific American", LocalDate.of(2023, 2, 1), Tipo_Enum.Revista, categoria2, editorial2, new ArrayList<>(), "8765-4321", Periodicidad_Enum.Diaria);


        // Crear Préstamos
        Prestamo prestamo1 = new Prestamo(usuario1, libro1, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15), Estado_Enum.Prestado);
        Prestamo prestamo2 = new Prestamo(usuario2, revista1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 20), Estado_Enum.Devuelto);
        Prestamo prestamoDelete = new Prestamo(usuario2, revista2, LocalDate.of(2050, 11, 1), LocalDate.of(2033, 11, 20), Estado_Enum.Devuelto);

        PrestamoDAO.build().store(prestamo1);
        PrestamoDAO.build().store(prestamo2);
        PrestamoDAO.build().store(prestamoDelete);

        // Buscar en la base de datos
        System.out.println(PrestamoDAO.build().findId(prestamo1));
        System.out.println(PrestamoDAO.build().findId(prestamo2));
        System.out.println(PrestamoDAO.build().findId(prestamoDelete));

        PrestamoDAO.build().deleteEntity(prestamoDelete);
    }
}
