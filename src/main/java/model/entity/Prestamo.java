package model.entity;


import model.entity.Enum.Estado;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private Usuario usuario;
    private Publicacion publicacion;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Estado estado;

    public Prestamo(Usuario usuario, Publicacion publicacion, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Estado estado) {
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;


    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Prestamo prestamo = (Prestamo) object;
        return Objects.equals(usuario, prestamo.usuario) && Objects.equals(publicacion, prestamo.publicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, publicacion);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "usuario=" + usuario +
                ", publicacion=" + publicacion +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado=" + estado +
                '}';
    }
}
