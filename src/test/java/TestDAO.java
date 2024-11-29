import model.dao.*;
import model.entity.*;
import model.entity.Enum.Estado_Enum;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Enum.Tipo_Enum;
import utils.Validacion;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestDAO {
    public static void main(String[] args) {
        // Crear Usuarios
        String clave1 = Validacion.encryptClave("clave123");
        String clave2 = Validacion.encryptClave("clave456");
        String clave3 = Validacion.encryptClave("delete");

        Usuario usuario1 = new Usuario(1, "Juan Pérez", clave1, "juan.perez@example.com", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "María López", clave2, "maria.lopez@example.com", new ArrayList<>());
        Usuario usuarioDelete = new Usuario(3, "Alberto", clave3, "alberto@gamil.com", new ArrayList<>());

        // Crear Categorías
        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());
        Categoria categoriaDelete = new Categoria(3, "Tecnologia", new ArrayList<>());

        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());
        Editorial editorialDelete = new Editorial(3, "Editorial", "Africa", LocalDate.of(1949,12,23), new ArrayList<>());

        // Crear Autores
        Autor autor1 = new Autor(1, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), new ArrayList<>());
        Autor autor2 = new Autor(2, "Isaac Asimov", "Rusa-Estadounidense", LocalDate.of(1920, 1, 2), new ArrayList<>());
        Autor autorDelete = new Autor(3, "Pepe","Español",LocalDate.of(1999, 1,3), new ArrayList<>());

        // Crear Libros
        Libro libro1 = new Libro(1, "Cien Años de Soledad", LocalDate.of(1967, 5, 30), Tipo_Enum.Libro, categoria1, editorial1, new ArrayList<>(), "123-456-789", autor1);
        Libro libro2 = new Libro(2, "Fundación", LocalDate.of(1951, 6, 1), Tipo_Enum.Libro, categoria2, editorial2, new ArrayList<>(), "987-654-321", autor2);
        Libro libroDelete = new Libro(3,"Harry potter", LocalDate.of(200, 4,5), Tipo_Enum.Libro, categoria1,editorial2, new ArrayList<>(), "123-333-222",autor1);

        // Crear Revistas
        Revista revista1 = new Revista(1, "National Geographic", LocalDate.of(2023, 1, 1), Tipo_Enum.Revista, categoria2, editorial1, new ArrayList<>(), "1234-5678", Periodicidad_Enum.Anual);
        Revista revista2 = new Revista(2, "Scientific American", LocalDate.of(2023, 2, 1), Tipo_Enum.Revista, categoria2, editorial2, new ArrayList<>(), "8765-4321", Periodicidad_Enum.Diaria);
        Revista revistaDelete = new Revista(3,"Vandal",LocalDate.of(2022, 3, 4),Tipo_Enum.Revista, categoria2,editorial2, new ArrayList<>(),"787-998-755",Periodicidad_Enum.Anual);

        // Crear Préstamos
        Prestamo prestamo1 = new Prestamo(usuario1, libro1, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 15), Estado_Enum.prestado);
        Prestamo prestamo2 = new Prestamo(usuario2, revista1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 20), Estado_Enum.devuelto);
        Prestamo prestamoDelete = new Prestamo(usuario2, revista1, LocalDate.of(2050, 11, 1), LocalDate.of(2033, 11, 20), Estado_Enum.devuelto);

        // Mostrar las entidades creadas
        System.out.println("Categorías:");
        System.out.println(categoria1);
        System.out.println(categoria2);

        System.out.println("\nEditoriales:");
        System.out.println(editorial1);
        System.out.println(editorial2);

        System.out.println("\nAutores:");
        System.out.println(autor1);
        System.out.println(autor2);

        System.out.println("\nUsuarios:");
        System.out.println(usuario1);
        System.out.println(usuario2);

        System.out.println("\nLibros:");
        System.out.println(libro1);
        System.out.println(libro2);

        System.out.println("\nRevistas:");
        System.out.println(revista1);
        System.out.println(revista2);

        System.out.println("\nPréstamos:");
        System.out.println(prestamo1);
        System.out.println(prestamo2);

        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
/*
        System.out.println("Comienzan las pruebas de los usuarioDAO");

        //Almacenar
        UsuarioDAO.build().store(usuario1);
        UsuarioDAO.build().store(usuario2);
        UsuarioDAO.build().store(usuarioDelete);

        //Buscar
        usuario1 = (UsuarioDAO.build().findId(1));
        usuario2 = (UsuarioDAO.build().findId(2));
        usuarioDelete = (UsuarioDAO.build().findId(3));

        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println(usuarioDelete);

        //Actualizar
        usuario1.setEMAIL("algo@gmail.com");
        UsuarioDAO.build().store(usuario1);

        //Delete
        String nombreUsuario = UsuarioDAO.build().deleteEntity(usuarioDelete).getNombre();
        System.out.println(nombreUsuario + ". Se a borrado");


        System.out.println("-----------------");

        System.out.println("Comienzan las pruebas de los CategoriaDAO");


        //Almacenar
        CategoriaDAO.build().store(categoria1);
        CategoriaDAO.build().store(categoria2);
        CategoriaDAO.build().store(categoriaDelete);

        //Buscar
        categoria1 = (CategoriaDAO.build().findId(1));
        categoria2 = (CategoriaDAO.build().findId(2));
        // Este es el que no tiene ID
        categoriaDelete = (CategoriaDAO.build().findId(3));

        System.out.println(categoria1);
        System.out.println(categoria2);
        System.out.println(categoriaDelete);

        //Actualizar
        categoria1.setNombre("Artes Marciales");
        CategoriaDAO.build().store(categoria1);

        //Delete
        String nombreCateogira = CategoriaDAO.build().deleteEntity(categoriaDelete).getNombre();
        System.out.println(nombreCateogira + ". Se a borrado");

        System.out.println("-----------------");

        System.out.println("Comienzan las pruebas de los EditorialDAO");

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

        System.out.println("-----------------");

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


        System.out.println("-----------------");
*/
        System.out.println("Comienzan las pruebas de los LibroDAO");

       // Almacenar
        LibroDAO.build().store(libro1);
        LibroDAO.build().store(libro2);
        LibroDAO.build().store(libroDelete);
//
//        // Buscar
//        libro1 = LibroDAO.build().findId(libro1.getId());
//        libro2 = LibroDAO.build().findId(libro2.getId());
//        libroDelete = LibroDAO.build().findId(libroDelete.getId());
//
//        System.out.println(libro1);
//        System.out.println(libro2);
//        System.out.println(libroDelete);
//
//        //Actualizar
//
//        libro1.setTitulo("One piece");
//        LibroDAO.build().store(libro1);
//
//        // Delete
//        LibroDAO.build().deleteEntity(libroDelete);
    }
}



