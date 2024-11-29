package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Autor;
import model.entity.Libro;
import model.entity.Publicacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroDAO implements IDAO<Libro, Integer> {

    private Connection connection;

    public LibroDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Revista(Publicacion_ID, ISSN, Periodicidad) VALUES (?,?,?)";
//    No hace falta porque se borra en cascada si borras la publicación
//    private static final String DELETE = "DELETE FROM Revista WHERE Publicacion_ID = ?";
    private static final String UPDATE = "UPDATE Revista SET ISSN = ?, Periodicidad = ? WHERE Publicacion_ID = ?";
    private static final String FINDID = "SELECT Publicacion_Id, ISBN, Autor_Id FROM libro WHERE Publicacion_Id = ?";


    @Override
    public Libro store(Libro entity) {
        if (entity != null){
            int idLibroTmp = entity.getId();
            if (idLibroTmp > 0) {
                Libro libroTmp = findId(idLibroTmp);
                if (libroTmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
                        preparedStatement.setInt(1, entity.getId());
                        preparedStatement.setString(2, entity.getISBN());
                        preparedStatement.setInt(3, entity.getAutor().getId());

                        preparedStatement.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else{
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
                        preparedStatement.setString(1, entity.getISBN());
                        preparedStatement.setInt(2,entity.getAutor().getId());
                        preparedStatement.setInt(3, entity.getId());

                        preparedStatement.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public Libro findId(Integer entityId) {
        Libro libro = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    libro = new Libro();
                    //Atributos heredados de publicacion
                    Publicacion publicacion = new Publicacion();
                    publicacion = PublicacionDAO.build().findId(resultSet.getInt("Publicacion_Id"));
                    libro.setId(publicacion.getId());
                    libro.setTitulo(publicacion.getTitulo());
                    libro.setFecha_publicacion(publicacion.getFecha_publicacion());
                    libro.setTipo(publicacion.getTipo());
                    libro.setCategoria(publicacion.getCategoria());
                    libro.setEditorial(publicacion.getEditorial());

                    // Atributos de libro
                    libro.setTitulo(resultSet.getString("ISBN"));
                    libro.setAutor(AutorDAO.build().findId(resultSet.getInt("Autor_Id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public Libro deleteEntity(Libro entityDelete) {
        if (entityDelete != null) {
                PublicacionDAO.build().deleteEntity(findId(entityDelete.getId()));
        }
        return entityDelete;
    }

    @Override
    public void close() throws IOException {

    }

    public static LibroDAO build() {
        return new LibroDAO();
    }
}
