package DAO;

import model.dao.UsuarioDAO;
import model.entity.Usuario;
import utils.Validacion;

import java.util.ArrayList;

public class TestUsuario {
    public static void main(String[] args) {
        // Crear Usuarios
        String clave1 = Validacion.encryptClave("clave123");
        String clave2 = Validacion.encryptClave("clave456");
        String clave3 = Validacion.encryptClave("delete");

        Usuario usuario1 = new Usuario(1, "Juan Pérez", clave1, "juan.perez@gmail.com", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "María López", clave2, "maria.lopez@hotmail.com", new ArrayList<>());
        Usuario usuarioDelete = new Usuario(3, "Alberto", clave3, "alberto@gamil.com", new ArrayList<>());

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


    }
}
