package model.entity;


import model.entity.Enum.Tipo_Enum;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Publicacion {
    private int id;
    private String titulo;
    private LocalDate fecha_publicacion;
    private Tipo_Enum tipo;
    private Categoria categoria;
    private Editorial editorial;
    private List<Prestamo> prestamos;

    public Publicacion() {
    }

    public Publicacion(int id, String titulo, LocalDate fecha_publicacion, Tipo_Enum tipo, Categoria categoria, Editorial editorial, List<Prestamo> prestamos) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_publicacion = fecha_publicacion;
        this.tipo = tipo;
        this.categoria = categoria;
        this.editorial = editorial;
        this.prestamos = prestamos;
    }

    public Publicacion(String titulo, LocalDate fecha_publicacion, Tipo_Enum tipo, Categoria categoria, Editorial editorial, List<Prestamo> prestamos) {
        this.titulo = titulo;
        this.fecha_publicacion = fecha_publicacion;
        this.tipo = tipo;
        this.categoria = categoria;
        this.editorial = editorial;
        this.prestamos = prestamos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Tipo_Enum getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Enum tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
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
        Publicacion that = (Publicacion) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return titulo;
    }
}