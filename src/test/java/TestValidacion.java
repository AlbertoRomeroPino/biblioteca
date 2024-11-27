
import model.entity.Usuario;
import utils.Validacion;

import java.util.ArrayList;

public class TestValidacion {
    public static void main(String[] args) {
        // Creación de un nuevo usuario
        Usuario user = new Usuario("nombreUsuario", "miContraseñaSegura", "email@example.com", new ArrayList<>());

        // Mostrar detalles del usuario creado
        System.out.println("Usuario creado: " + user);

        // Verificar la contraseña utilizando la clase Autenticador
        boolean isMatch = Validacion.verifyClave(user, "miContraseñaSegura");
        System.out.println("¿La contraseña coincide? " + isMatch);

        // Probar con una contraseña incorrecta
        boolean isMatchIncorrect = Validacion.verifyClave(user, "contraseñaIncorrecta");
        System.out.println("¿La contraseña coincide? " + isMatchIncorrect);
    }
}


