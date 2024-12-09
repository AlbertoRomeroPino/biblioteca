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
        System.out.println("|  All Categoria  |");
        System.out.println("-------------------");

        FindALLCategoria();


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

        System.out.println("-------------------");
        System.out.println("|   All Usuario   |");
        System.out.println("-------------------");

        FindAllUsuario();

        System.out.println("-------------------");
        System.out.println("|All Identificador |");
        System.out.println("-------------------");

        FindIdentificador();

        System.out.println("-------------------");
        System.out.println("|  All Prestamos  |");
        System.out.println("-------------------");

        FindALLPrestamo();

        System.out.println("-------------------");
        System.out.println("|     All Join    |");
        System.out.println("-------------------");

        FindJoinPrestamo();



    }

    public static void FindALLCategoria() {
        List<Categoria> categorias = CategoriaDAO.build().findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
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

    public static void FindAllUsuario(){
        List<Usuario> usuarios = UsuarioDAO.build().findAll();
        for (Usuario usuario : usuarios){
            System.out.println(usuario);
        }
    }

    public static void FindIdentificador(){
        String email = "algo@gmail.com";
        String clave = "1a02c611aeb8a65c9bf6fe741a1f4e0119334737abaa97f20e42f2e68c4d851c";
        Usuario usuario = UsuarioDAO.build().findByIdentificator(email, clave);

        System.out.println(usuario);
    }

    public static void FindJoinPrestamo(){
        List<Prestamo> prestamos = PrestamoDAO.build().findJoin();
        for (Prestamo prestamo : prestamos){
            System.out.println(prestamo.getUsuario().getNombre() + ", Titulo: " + prestamo.getPublicacion().getTitulo());
            System.out.println(prestamo.toString());
        }
    }
}
