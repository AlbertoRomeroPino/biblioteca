package model.entity;

import java.util.List;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nombre;
    private String clave;
    private String EMAIL;
    private List<Prestamo> prestamos;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave, String EMAIL, List<Prestamo> prestamos) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.EMAIL = EMAIL;
        this.prestamos = prestamos;
    }

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
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
                ", clave='" + clave + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", prestamos=" + prestamos +
                '}';
    }
}