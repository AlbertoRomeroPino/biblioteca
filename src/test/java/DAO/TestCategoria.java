package DAO;

import model.dao.CategoriaDAO;
import model.entity.Categoria;
import java.util.ArrayList;

public class TestCategoria {
    public static void main(String[] args) {
        // Crear Categorías
        Categoria categoria1 = new Categoria(1, "Literatura", new ArrayList<>());
        Categoria categoria2 = new Categoria(2, "Ciencia Ficción", new ArrayList<>());
        Categoria categoria3 = new Categoria(3, "Tecnología", new ArrayList<>());
        Categoria categoria4 = new Categoria(4, "Historia", new ArrayList<>());
        Categoria categoria5 = new Categoria(5, "Filosofía", new ArrayList<>());
        Categoria categoria6 = new Categoria(6, "Arte", new ArrayList<>());
        Categoria categoria7 = new Categoria(7, "Biografía", new ArrayList<>());
        Categoria categoria8 = new Categoria(8, "Economía", new ArrayList<>());
        Categoria categoria9 = new Categoria(9, "Psicología", new ArrayList<>());
        Categoria categoriaDelete = new Categoria(10, "Matemáticas", new ArrayList<>());

        System.out.println("Comienzan las pruebas de los CategoriaDAO");

// Almacenar
        CategoriaDAO.build().store(categoria1);
        CategoriaDAO.build().store(categoria2);
        CategoriaDAO.build().store(categoria3);
        CategoriaDAO.build().store(categoria4);
        CategoriaDAO.build().store(categoria5);
        CategoriaDAO.build().store(categoria6);
        CategoriaDAO.build().store(categoria7);
        CategoriaDAO.build().store(categoria8);
        CategoriaDAO.build().store(categoria9);
        CategoriaDAO.build().store(categoriaDelete);

// Buscar
        categoria1 = CategoriaDAO.build().findId(1);
        categoria2 = CategoriaDAO.build().findId(2);
        categoria3 = CategoriaDAO.build().findId(3);
        categoria4 = CategoriaDAO.build().findId(4);
        categoria5 = CategoriaDAO.build().findId(5);
        categoria6 = CategoriaDAO.build().findId(6);
        categoria7 = CategoriaDAO.build().findId(7);
        categoria8 = CategoriaDAO.build().findId(8);
        categoria9 = CategoriaDAO.build().findId(9);
        categoriaDelete = CategoriaDAO.build().findId(10);

        System.out.println(categoria1);
        System.out.println(categoria2);
        System.out.println(categoria3);
        System.out.println(categoria4);
        System.out.println(categoria5);
        System.out.println(categoria6);
        System.out.println(categoria7);
        System.out.println(categoria8);
        System.out.println(categoria9);
        System.out.println(categoriaDelete);


        //Actualizar
        categoria1.setNombre("Artes Marciales");
        CategoriaDAO.build().store(categoria1);

        //Delete
        String nombreCateogira = CategoriaDAO.build().deleteEntity(categoriaDelete).getNombre();
        System.out.println(nombreCateogira + ". Se a borrado");

    }
}