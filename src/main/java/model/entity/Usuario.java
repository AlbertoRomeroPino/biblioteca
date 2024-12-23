package model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nombre;
    private String Clave;
    private String EMAIL;
    private List<Prestamo> prestamos;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave, String EMAIL, List<Prestamo> prestamos) {
        this.id = id;
        this.nombre = nombre;
        this.Clave = clave;
        this.EMAIL = EMAIL;
        this.prestamos = prestamos;
    }

    public Usuario(String nombre, String clave, String EMAIL, List<Prestamo> prestamos) {
        this.nombre = nombre;
        this.Clave = clave;
        this.EMAIL = EMAIL;
        this.prestamos = prestamos;
    }

    public Usuario(String nombre, String email, String password, LocalDate fechaNacimiento) {

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

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
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
        return nombre;
    }
}
