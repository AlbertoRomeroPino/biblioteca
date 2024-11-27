package model.entity;



import model.entity.Enum.Periodicidad_Enum;
import model.entity.Enum.Tipo_Enum;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Revista extends Publicacion {
    private String ISSN;
    private Periodicidad_Enum periodicidad;


    public Revista() {
    }

    public Revista(int id, String titulo, LocalDate fecha_publicacion, Tipo_Enum tipo, Categoria categoria, Editorial editorial, List<Prestamo> prestamos, String ISSN, Periodicidad_Enum periodicidad) {
        super(id, titulo, fecha_publicacion, tipo, categoria, editorial, prestamos);
        this.ISSN = ISSN;
        this.periodicidad = periodicidad;
    }

    public Revista(String titulo, LocalDate fecha_publicacion, Tipo_Enum tipo, Categoria categoria, Editorial editorial, List<Prestamo> prestamos, String ISSN, Periodicidad_Enum periodicidad) {
        super(titulo, fecha_publicacion, tipo, categoria, editorial, prestamos);
        this.ISSN = ISSN;
        this.periodicidad = periodicidad;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public Periodicidad_Enum getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad_Enum periodicidad) {
        this.periodicidad = periodicidad;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Revista revista = (Revista) object;
        return Objects.equals(ISSN, revista.ISSN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ISSN);
    }

    @Override
    public String toString() {
        return "Revista{" +
                "ISSN='" + ISSN + '\'' +
                ", periodicidad=" + periodicidad +
                '}';
    }
}