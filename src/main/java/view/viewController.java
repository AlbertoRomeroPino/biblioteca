package view;

import model.dao.UsuarioDAO;
import model.entity.Usuario;
import model.entity.Sesion;
import utils.Validacion;
import utils.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class viewController {


    public static int Texto() {
        Scanner scanner = new Scanner(System.in);


            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Mostrar usuario en sesión");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea



        return opcion;
    }

    public static void crearUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine();

        if (Validacion.validacionEmail(email)) {
            Usuario usuario = new Usuario(nombre, Validacion.encryptClave(clave), email, new ArrayList<>());
            UsuarioDAO.build().store(usuario);
            System.out.println("Usuario creado exitosamente: " + usuario);
        } else {
            System.out.println("Correo electrónico o contraseña no válidos.");
        }
    }

    public static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine();

        if (Validacion.validacionEmail(email)) {
            Usuario usuario = UsuarioDAO.build().FindByEmail(email);
            if (usuario != null && Validacion.encryptClave(clave).equals(usuario.getClave())) {
                Sesion.getInstance().setUsuario(usuario);
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Correo electrónico o contraseña incorrectos.");
            }
        } else {
            System.out.println("Correo electrónico o contraseña no válidos.");
        }
    }

    public static void mostrarUsuarioSesion() {
        Usuario usuario = Sesion.getInstance().getUsuario();
        if (usuario != null) {
            System.out.println("Usuario en sesión: " + usuario);
        } else {
            System.out.println("No hay ningún usuario en sesión.");
        }
    }


}


