package DAO;

import model.dao.UsuarioDAO;
import model.entity.Usuario;
import utils.Validacion;

import java.util.ArrayList;

public class TestUsuario {
    public static void main(String[] args) {
        // Crear Usuarios con contraseñas cifradas
        String clave1 = Validacion.encryptClave("clave123");
        String clave2 = Validacion.encryptClave("clave456");
        String clave3 = Validacion.encryptClave("delete");
        String clave4 = Validacion.encryptClave("secreta123");
        String clave5 = Validacion.encryptClave("12345678");
        String clave6 = Validacion.encryptClave("qwerty123");
        String clave7 = Validacion.encryptClave("admin123");
        String clave8 = Validacion.encryptClave("superclave");

        Usuario usuario1 = new Usuario(1, "Juan Pérez", clave1, "juan.perez@gmail.com", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "María López", clave2, "maria.lopez@hotmail.com", new ArrayList<>());
        Usuario usuario3 = new Usuario(4, "Carlos García", clave3, "carlos.garcia@yahoo.com", new ArrayList<>());
        Usuario usuario4 = new Usuario(5, "Ana Torres", clave4, "ana.torres@hotmail.com", new ArrayList<>());
        Usuario usuario5 = new Usuario(6, "Luis Fernández", clave5, "luis.fernandez@gmail.com", new ArrayList<>());
        Usuario usuario6 = new Usuario(7, "Pedro Ruiz", clave6, "pedro.ruiz@yahoo.com", new ArrayList<>());
        Usuario usuario7 = new Usuario(8, "Lucía Martínez", clave7, "lucia.martinez@gmail.com", new ArrayList<>());
        Usuario usuario8 = new Usuario(9, "Javier Sánchez", clave8, "javier.sanchez@hotmail.com", new ArrayList<>());
        Usuario usuarioDelete = new Usuario(3, "Alberto", Validacion.encryptClave("delete"), "alberto@gamil.com", new ArrayList<>());

        System.out.println("Comienzan las pruebas de los UsuarioDAO");

// Almacenar los usuarios en la base de datos
        UsuarioDAO.build().store(usuario1);
        UsuarioDAO.build().store(usuario2);
        UsuarioDAO.build().store(usuario3);
        UsuarioDAO.build().store(usuario4);
        UsuarioDAO.build().store(usuario5);
        UsuarioDAO.build().store(usuario6);
        UsuarioDAO.build().store(usuario7);
        UsuarioDAO.build().store(usuario8);
        UsuarioDAO.build().store(usuarioDelete);

// Buscar los usuarios por su ID
        usuario1 = UsuarioDAO.build().findId(1);
        usuario2 = UsuarioDAO.build().findId(2);
        usuario3 = UsuarioDAO.build().findId(4);
        usuario4 = UsuarioDAO.build().findId(5);
        usuario5 = UsuarioDAO.build().findId(6);
        usuario6 = UsuarioDAO.build().findId(7);
        usuario7 = UsuarioDAO.build().findId(8);
        usuario8 = UsuarioDAO.build().findId(9);
        usuarioDelete = UsuarioDAO.build().findId(3);

// Imprimir los detalles de los usuarios encontrados
        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println(usuario3);
        System.out.println(usuario4);
        System.out.println(usuario5);
        System.out.println(usuario6);
        System.out.println(usuario7);
        System.out.println(usuario8);
        System.out.println(usuarioDelete);


        //Actualizar
        usuario1.setEMAIL("algo@gmail.com");
        UsuarioDAO.build().store(usuario1);

        //Delete
        String nombreUsuario = UsuarioDAO.build().deleteEntity(usuarioDelete).getNombre();
        System.out.println(nombreUsuario + ". Se a borrado");


    }
}
