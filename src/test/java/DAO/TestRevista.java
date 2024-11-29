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
        Categoria categoriaDelete = new Categoria(3, "Tecnologia", new ArrayList<>());

        // Crear Editoriales
        Editorial editorial1 = new Editorial(1, "Penguin Random House", "Reino Unido", LocalDate.of(1935, 7, 1), new ArrayList<>());
        Editorial editorial2 = new Editorial(2, "Planeta", "España", LocalDate.of(1949, 12, 23), new ArrayList<>());
        Editorial editorialDelete = new Editorial(3, "Editorial", "Africa", LocalDate.of(1949, 12, 23), new ArrayList<>());

        // Crear Autores
        Autor autor1 = new Autor(1, "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), new ArrayList<>());
        Autor autor2 = new Autor(2, "Isaac Asimov", "Rusa-Estadounidense", LocalDate.of(1920, 1, 2), new ArrayList<>());
        Autor autorDelete = new Autor(3, "Pepe", "Español", LocalDate.of(1999, 1, 3), new ArrayList<>());


        Revista revista1 = new Revista(4, "National Geographic", LocalDate.of(2023, 1, 1), Tipo_Enum.Revista, categoria1, editorial1, new ArrayList<>(), "1234-5678", Periodicidad_Enum.Anual);
        Revista revista2 = new Revista(5, "Scientific American", LocalDate.of(2023, 2, 1), Tipo_Enum.Revista, categoria2, editorial2, new ArrayList<>(), "8765-4321", Periodicidad_Enum.Diaria);
        Revista revistaDelete = new Revista(6,"Vandal",LocalDate.of(2022, 3, 4),Tipo_Enum.Revista, categoria2,editorial2, new ArrayList<>(),"787-998-755",Periodicidad_Enum.Anual);

        System.out.println("Comienzan las pruebas de los RevistaDAO");

        RevistaDAO.build().store(revista1);
        RevistaDAO.build().store(revista2);
        RevistaDAO.build().store(revistaDelete);
    }
}
