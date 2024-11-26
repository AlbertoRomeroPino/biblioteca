package model.entity;


import model.entity.Enum.Tipo_Enum;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Libro extends Publicacion {
    private String ISBN;
    private Publicacion publicacion;
    private Autor autor;

    public Libro() {
    }


    public Libro(int id, String titulo, LocalDate fecha_publicacion, Tipo_Enum tipo, Categoria categoria, Editorial editorial, List<Prestamo> prestamos, String ISBN, Publicacion publicacion, Autor autor) {
        super(id, titulo, fecha_publicacion, tipo, categoria, editorial, prestamos);
        this.ISBN = ISBN;
        this.publicacion = publicacion;
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Libro libro = (Libro) object;
        return Objects.equals(ISBN, libro.ISBN) && Objects.equals(autor, libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ISBN, autor);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", publicacion=" + publicacion +
                ", autor=" + autor +
                '}';
    }
}
