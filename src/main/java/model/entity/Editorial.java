package model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Editorial {
    private int id;
    private String nombre;
    private String pais;
    private Date fecha_fundacion;
    private List<Publicacion>publicacions;

    public Editorial(int id, String nombre, String pais, Date fecha_fundacion, List<Publicacion> publicacions) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fecha_fundacion = fecha_fundacion;
        this.publicacions = publicacions;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFecha_fundacion() {
        return fecha_fundacion;
    }

    public void setFecha_fundacion(Date fecha_fundacion) {
        this.fecha_fundacion = fecha_fundacion;
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
        Editorial editorial = (Editorial) object;
        return id == editorial.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", fecha_fundacion=" + fecha_fundacion +
                ", publicacions=" + publicacions +
                '}';
    }
}
