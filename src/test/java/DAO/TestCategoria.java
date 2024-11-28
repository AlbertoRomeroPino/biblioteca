package DAO;

import model.dao.CategoriaDAO;
import model.entity.Categoria;
import java.util.ArrayList;

public class TestCategoria {
    public static void main(String[] args) {
        // Crear Categorías
        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());
        Categoria categoriaDelete = new Categoria(3, "Tecnologia", new ArrayList<>());

        System.out.println("Comienzan las pruebas de los CategoriaDAO");


        //Almacenar
        CategoriaDAO.build().store(categoria1);
        CategoriaDAO.build().store(categoria2);
        CategoriaDAO.build().store(categoriaDelete);

        //Buscar
        categoria1 = (CategoriaDAO.build().findId(1));
        categoria2 = (CategoriaDAO.build().findId(2));
        // Este es el que no tiene ID
        categoriaDelete = (CategoriaDAO.build().findId(3));

        System.out.println(categoria1);
        System.out.println(categoria2);
        System.out.println(categoriaDelete);

        //Actualizar
        categoria1.setNombre("Artes Marciales");
        CategoriaDAO.build().store(categoria1);

        //Delete
        String nombreCateogira = CategoriaDAO.build().deleteEntity(categoriaDelete).getNombre();
        System.out.println(nombreCateogira + ". Se a borrado");

    }
}