package DAO;

import model.dao.*;
import model.entity.*;

import java.util.List;

/**
 * Esto es para comprobar todos los FindAll que se an creado despues de comprobar los DAO clasicos del CRUD
 */
public class Test2DAO {
    public static void main(String[] args) {

        System.out.println("-------------------");
        System.out.println("|  All Prestamos  |");
        System.out.println("-------------------");

        FindALLPrestamo();

        System.out.println("-------------------");
        System.out.println("|All Publicaciones|");
        System.out.println("-------------------");

        FindAllPublicacion();

        System.out.println("-------------------");
        System.out.println("|  All Editorial  |");
        System.out.println("-------------------");

        FindAllEditorial();

        System.out.println("-------------------");
        System.out.println("|    All Autor    |");
        System.out.println("-------------------");

        FindAllAutor();

        System.out.println("-------------------");
        System.out.println("|    All Libro    |");
        System.out.println("-------------------");

        FindAllLibro();

        System.out.println("-------------------");
        System.out.println("|   All Revista   |");
        System.out.println("-------------------");

        FindAllRevista();
    }

    public static void FindALLPrestamo() {
        List<Prestamo> prestamos = PrestamoDAO.build().findAll();
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    public static void FindAllPublicacion() {
        List<Publicacion> publicaciones = PublicacionDAO.build().findAll();
        for (Publicacion publicacion : publicaciones) {
            System.out.println(publicacion);
        }
    }

    public static void FindAllEditorial() {
        List<Editorial> editoriales = EditorialDAO.build().findAll();
        for (Editorial editorial : editoriales) {
            System.out.println(editorial);
        }
    }

    public static void FindAllAutor() {
        List<Autor> autores = AutorDAO.build().findAll();
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public static void FindAllLibro() {
        List<Libro> libros = LibroDAO.build().findAll();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public static void FindAllRevista(){
        List<Revista> revistas = RevistaDAO.build().findAll();
        for (Revista revista : revistas){
            System.out.println(revista);
        }
    }
}
