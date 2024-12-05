package DAO;

import model.dao.RevistaDAO;
import model.entity.Autor;
import model.entity.Categoria;
import model.entity.Editorial;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Enum.Tipo_Enum;
import model.entity.Revista;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestRevista {
    public static void main(String[] args) {

        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());


        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());


        Revista revista1 = new Revista(4, "National Geographic", LocalDate.of(2023, 1, 1), Tipo_Enum.Revista, categoria1, editorial1, new ArrayList<>(), "1234-5678", Periodicidad_Enum.Anual);
        Revista revista2 = new Revista(5, "Scientific American", LocalDate.of(2023, 2, 1), Tipo_Enum.Revista, categoria2, editorial2, new ArrayList<>(), "8765-4321", Periodicidad_Enum.Diaria);
        Revista revistaDelete = new Revista(6,"Vandal",LocalDate.of(2022, 3, 4),Tipo_Enum.Revista, categoria2,editorial2, new ArrayList<>(),"787-998-755",Periodicidad_Enum.Anual);

        System.out.println("Comienzan las pruebas de los RevistaDAO");

        RevistaDAO.build().store(revista1);
        RevistaDAO.build().store(revista2);
        RevistaDAO.build().store(revistaDelete);

        revista1 = RevistaDAO.build().findId(4);
        revista2 = RevistaDAO.build().findId(5);
        revistaDelete = RevistaDAO.build().findId(6);

        System.out.println("Publicacion{Publicacion_Id="+revista1.getId()+", Titulo=" + revista1.getTitulo() + ", FechaPublicacion= " + revista1.getFecha_publicacion() + ", Tipo=" + revista1.getTipo() + ", Categoria_ID=" + revista1.getCategoria().getId() + ", Editorial_ID=" + revista1.getEditorial().getId());
        System.out.println(revista1);
        System.out.println("Publicacion{Publicacion_Id="+revista2.getId()+", Titulo=" + revista2.getTitulo() + ", FechaPublicacion= " + revista2.getFecha_publicacion() + ", Tipo=" + revista2.getTipo() + ", Categoria_ID=" + revista2.getCategoria().getId() + ", Editorial_ID=" + revista2.getEditorial().getId());
        System.out.println(revista2);
        System.out.println("Publicacion{Publicacion_Id="+revistaDelete.getId()+", Titulo=" + revistaDelete.getTitulo() + ", FechaPublicacion= " + revistaDelete.getFecha_publicacion() + ", Tipo=" + revistaDelete.getTipo() + ", Categoria_ID=" + revistaDelete.getCategoria().getId() + ", Editorial_ID=" + revistaDelete.getEditorial().getId());
        System.out.println(revistaDelete);

        revista1.setISSN("1234-4321");
        RevistaDAO.build().store(revista1);

        RevistaDAO.build().deleteEntity(revistaDelete);
    }
}