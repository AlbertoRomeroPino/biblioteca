package Controller;

import model.dao.UsuarioDAO;
import model.entity.Sesion;
import utils.View;
import view.viewController;

import java.io.IOException;
import java.util.Scanner;

import static view.viewController.*;

public class Main {
    public static void main(String[] args) {
        boolean opcion = true;
        while (opcion) {


            switch (Texto()) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    mostrarUsuarioSesion();
                    break;
                case 4:
                    View.readText("Saliendo del sistema...");
                    opcion = false;
                    break;
                default:
                    View.readText("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
