package utils;

import interfaces.IValidacion;
import model.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.SecureRandom;

public class Validacion implements IValidacion {

    // Verificar si la clave ingresada coincide con el hash almacenado en Usuario
    public static boolean verifyClave(Usuario usuario, String clave) {
        String hashedClaveInput = hashClave(clave, usuario.getSalt());
        return hashedClaveInput.equals(usuario.getClave());
    }

    // Hashear la clave con la sal usando SHA-256
    private static String hashClave(String clave, String salt) {
        String saltedClave = clave + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedClave.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encryptClave(String clave) {
        String salt = generateSalt();
        return hashClave(clave, salt);
    }

    @Override
    public boolean validacionEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}




