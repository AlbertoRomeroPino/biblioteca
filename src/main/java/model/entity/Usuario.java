package model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nombre;
    private String hashedClave;
    private String salt;
    private String EMAIL;
    private List<Prestamo> prestamos;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave, String EMAIL, List<Prestamo> prestamos) {
        this.id = id;
        this.nombre = nombre;
        this.salt = generateSalt();
        this.hashedClave = hashClave(clave, this.salt);
        this.EMAIL = EMAIL;
        this.prestamos = prestamos;
    }

    public Usuario(String nombre, String clave, String EMAIL, List<Prestamo> prestamos) {
        this.nombre = nombre;
        this.salt = generateSalt();
        this.hashedClave = hashClave(clave, this.salt);
        this.EMAIL = EMAIL;
        this.prestamos = prestamos;
    }

    // Generar una sal aleatoria
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hashear la clave con la sal usando SHA-256
    private String hashClave(String clave, String salt) {
        String saltedClave = clave + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedClave.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHashedClave() {
        return hashedClave;
    }

    public void setHashedClave(String hashedClave) {
        this.hashedClave = hashedClave;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return id == usuario.id && Objects.equals(EMAIL, usuario.EMAIL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, EMAIL);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", hashedClave='" + hashedClave + '\'' +
                ", salt='" + salt + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", prestamos=" + prestamos +
                '}';
    }
}
