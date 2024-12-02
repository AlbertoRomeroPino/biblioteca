import model.entity.Sesion;
import model.entity.Usuario;

public class TestSesion {
     public static void main(String[] args) {
            Usuario usuario = new Usuario(1, "Juan", "miContraseña1", "juan@example.com", null);
            // Obtener la instancia única de Sesion y establecer el usuario
             Sesion sesion = Sesion.getInstance(); try { sesion.setUsuario(usuario);
            // Obtener el usuario de la sesión
             Usuario usuarioEnSesion = sesion.getUsuario();
             System.out.println("Usuario en sesión: " + usuarioEnSesion.getNombre());
             System.out.println("Correo electrónico en sesión: " + usuarioEnSesion.getEMAIL());
             System.out.println("Contraseña en sesión (encriptada): " + usuarioEnSesion.getClave());
             } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); } } }


