import model.dao.PublicacionDAO;
import model.dao.RevistaDAO;
import model.dao.UsuarioDAO;
import model.entity.*;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Enum.Tipo_Enum;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestDAO {
        public static void main(String[] args) {
            // Inicia la conexión a la base de datos
            System.out.println("Iniciando el programa...");

            // Crear un nuevo usuario
            System.out.println("\n-- Creando un nuevo Usuario --");
            Usuario usuario = new Usuario();
            usuario.setNombre("Juan Pérez");
            usuario.setClave("1234");
            usuario.setEMAIL("juanperez@gmail.com");

            UsuarioDAO usuarioDAO = UsuarioDAO.build();
            usuarioDAO.store(usuario);  // Insertamos el usuario
            System.out.println("Usuario creado: " + usuario.getNombre());

            // Buscar usuario por ID
            System.out.println("\n-- Buscando el Usuario con ID 1 --");
            Usuario usuarioEncontrado = usuarioDAO.findId(1);
            if (usuarioEncontrado != null) {
                System.out.println("Usuario encontrado: " + usuarioEncontrado.getNombre());
            } else {
                System.out.println("Usuario no encontrado.");
            }

            // Actualizar usuario
            System.out.println("\n-- Actualizando el Usuario con ID 1 --");
            if (usuarioEncontrado != null) {
                usuarioEncontrado.setNombre("Juan Pérez Actualizado");
                usuarioDAO.store(usuarioEncontrado);  // Actualizamos el usuario
                System.out.println("Usuario actualizado: " + usuarioEncontrado.getNombre());
            }

            // Eliminar usuario
            System.out.println("\n-- Eliminando el Usuario con ID 1 --");
            if (usuarioEncontrado != null) {
                usuarioDAO.deleteEntity(usuarioEncontrado);  // Eliminamos el usuario
                System.out.println("Usuario eliminado.");
            }

            // Crear una nueva publicación
            System.out.println("\n-- Creando una nueva Publicación --");
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo("Revista de Tecnología");
            publicacion.setFecha_publicacion(LocalDate.of(2023, 12, 1));
            publicacion.setTipo(Tipo_Enum.Libro);

            // Establecer la categoría y la editorial
            publicacion.setCategoria(new Categoria(1, "Tecnología", new ArrayList<Publicacion>()));
            publicacion.setEditorial(new Editorial(1, "Editorial XYZ", "España", LocalDate.of(2000, 1, 1), new ArrayList<Publicacion>()));

            PublicacionDAO publicacionDAO = PublicacionDAO.build();
            publicacionDAO.store(publicacion);  // Insertamos la publicación
            System.out.println("Publicación creada: " + publicacion.getTitulo());


            // Buscar publicación por ID
            System.out.println("\n-- Buscando la Publicación con ID 1 --");
            Publicacion publicacionEncontrada = publicacionDAO.findId(1);
            if (publicacionEncontrada != null) {
                System.out.println("Publicación encontrada: " + publicacionEncontrada.getTitulo());
            } else {
                System.out.println("Publicación no encontrada.");
            }

            // Actualizar publicación
            System.out.println("\n-- Actualizando la Publicación con ID 1 --");
            if (publicacionEncontrada != null) {
                publicacionEncontrada.setTitulo("Revista de Innovación");
                publicacionDAO.store(publicacionEncontrada);  // Actualizamos la publicación
                System.out.println("Publicación actualizada: " + publicacionEncontrada.getTitulo());
            }

            // Eliminar publicación
            System.out.println("\n-- Eliminando la Publicación con ID 1 --");
            if (publicacionEncontrada != null) {
                publicacionDAO.deleteEntity(publicacionEncontrada);  // Eliminamos la publicación
                System.out.println("Publicación eliminada.");
            }

            // Crear una nueva revista
            System.out.println("\n-- Creando una nueva Revista --");
            Revista revista = new Revista();
            revista.setId(1);  // Usamos el ID de la publicación recién creada
            revista.setISSN("1234-5678");
            revista.setPeriodicidad(Periodicidad_Enum.Anual);

            RevistaDAO revistaDAO = RevistaDAO.build();
            revistaDAO.store(revista);  // Insertamos la revista
            System.out.println("Revista creada: " + revista.getISSN());

            // Buscar revista por ID
            System.out.println("\n-- Buscando la Revista con ID 1 --");
            Revista revistaEncontrada = revistaDAO.findId(1);
            if (revistaEncontrada != null) {
                System.out.println("Revista encontrada: " + revistaEncontrada.getISSN());
            } else {
                System.out.println("Revista no encontrada.");
            }

            // Actualizar revista
            System.out.println("\n-- Actualizando la Revista con ID 1 --");
            if (revistaEncontrada != null) {
                revistaEncontrada.setISSN("9876-5432");
                revistaDAO.store(revistaEncontrada);  // Actualizamos la revista
                System.out.println("Revista actualizada: " + revistaEncontrada.getISSN());
            }

            // Eliminar revista
            System.out.println("\n-- Eliminando la Revista con ID 1 --");
            if (revistaEncontrada != null) {
                revistaDAO.deleteEntity(revistaEncontrada);  // Eliminamos la revista
                System.out.println("Revista eliminada.");
            }

            // Cerrar conexión (si fuera necesario)
            try {
                usuarioDAO.close();
                publicacionDAO.close();
                revistaDAO.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }

            System.out.println("\nPrograma terminado.");
        }
}
