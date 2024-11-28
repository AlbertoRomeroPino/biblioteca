package utils;

import interfaces.IValidacion;
import model.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.SecureRandom;

public class Validacion implements IValidacion {

    @Override
    public String encryptClave(String clave) {
        String hexString = null;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA3-256");

            byte[] hash = digest.digest(clave.getBytes());

            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex);
            }

            hexString = hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString;
    }

    @Override
    public boolean validacionEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

}




