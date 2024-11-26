package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Categoria {
    private int id;
    private String nombre;
    private List<Publicacion> publicacions;

    public Categoria() {
    }

    public Categoria(int id, String nombre, List<Publicacion> publicacions) {
        this.id = id;
        this.nombre = nombre;
        this.publicacions = new ArrayList<>();
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

    public List<Publicacion> getPublicacions() {
        return publicacions;
    }

    public void setPublicacions(List<Publicacion> publicacions) {
        this.publicacions = publicacions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Categoria categoria = (Categoria) object;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", publicacions=" + publicacions +
                '}';
    }
}
