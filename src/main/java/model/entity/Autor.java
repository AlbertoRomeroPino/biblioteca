package model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Autor {
    private int id;
    private String nombre;
    private String Nacionalidad;
    private Date FechaNacimiento;
    private List<Libro> libros;

    public Autor(int id, String nombre, String nacionalidad, Date fechaNacimiento, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        Nacionalidad = nacionalidad;
        FechaNacimiento = fechaNacimiento;
        this.libros = libros;
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

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = new ArrayList<>(){};

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Autor autor = (Autor) object;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Nacionalidad='" + Nacionalidad + '\'' +
                ", FechaNacimiento=" + FechaNacimiento +
                ", libros=" + libros +
                '}';
    }
}
