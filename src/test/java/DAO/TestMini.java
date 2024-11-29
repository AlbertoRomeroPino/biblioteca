package DAO;

import model.dao.PrestamoDAO;
import model.entity.Prestamo;

import java.util.List;

import static model.dao.PrestamoDAO.build;

public class TestMini {
    public static void main(String[] args) {
        List<Prestamo> prestamos = PrestamoDAO.build().findAll();
        for (Prestamo prestamo : prestamos){
            System.out.println(prestamo);
        }
    }
}
