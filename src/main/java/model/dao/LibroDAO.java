package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Libro;

import java.io.IOException;
import java.sql.Connection;

public class LibroDAO implements IDAO<Libro, Integer> {

    private Connection connection;

    public LibroDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Revista(Publicacion_ID, ISSN, Periodicidad) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Revista WHERE Publicacion_ID = ?";
    private static final String UPDATE = "UPDATE Revista SET ISSN = ?, Periodicidad = ? WHERE Publicacion_ID = ?";
    private static final String FINDID = "SELECT Publicacion_Id, ISBN, Autor_Id FROM libro WHERE Publicacion_Id = ?";

    @Override
    public Libro store(Libro entity) {
        return null;
    }

    @Override
    public Libro findId(Integer entityId) {
        return null;
    }

    @Override
    public Libro deleteEntity(Libro entityDelete) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    public static LibroDAO build() {
        return new LibroDAO();
    }
}
