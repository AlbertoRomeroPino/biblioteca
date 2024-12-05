package model.dao;

import model.connection.ConnectionMariaDB;
import model.entity.Autor;
import interfaces.IDAO;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AutorDAO implements IDAO<Autor, Integer> {
    private Connection connection;


    public AutorDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Autor(Nombre, Nacionalidad, Fecha_Nacimiento) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Autor WHERE Id = ?";
    private static final String UPDATE = "UPDATE Autor SET Nombre = ?, Nacionalidad= ?, Fecha_Nacimiento= ? WHERE Id = ?";
    private static final String FINDID = "SELECT Id, Nombre, Nacionalidad, Fecha_Nacimiento FROM Autor WHERE Id = ?";
    private static final String FINDALL = "SELECT Id, Nombre, Nacionalidad, Fecha_Nacimiento FROM Autor";

    /**
     * Guarda o actualiza un autor en la base de datos.
     * Si el ID del autor es menor o igual a 0, se considera un nuevo registro y se inserta.
     * En caso contrario, se actualiza el autor existente.
     *
     * @param entity el autor que se desea almacenar.
     * @return el autor almacenado o actualizado.
     */
    @Override
    public Autor store(Autor entity) {
        if (entity != null) {
            try {
                if (entity.getId() >= 0) { // Se considera que un ID <= 0 es un autor nuevo
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getNacionalidad());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaNacimiento()));
                        preparedStatement.executeUpdate();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setInt(4, entity.getId());
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getNacionalidad());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaNacimiento()));
                        preparedStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    /**
     * Busca un autor en la base de datos por su ID.
     *
     * @param entityId el ID del autor a buscar.
     * @return el autor encontrado, o {@code null} si no se encuentra.
     */
    @Override
    public Autor findId(Integer entityId) {
        Autor autor = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    autor = new Autor();
                    autor.setId(resultSet.getInt("Id"));
                    autor.setNombre(resultSet.getString("Nombre"));
                    autor.setNacionalidad(resultSet.getString("Nacionalidad"));
                    autor.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    }

    /**
     * Obtiene todos los autores de la base de datos.
     *
     * @return una lista de autores encontrados.
     */
    public List<Autor> findAll() {
        List<Autor> autores = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Autor autorTmp = new Autor();
                    autorTmp.setId(resultSet.getInt("Id"));
                    autorTmp.setNombre(resultSet.getString("Nombre"));
                    autorTmp.setNacionalidad(resultSet.getString("Nacionalidad"));
                    autorTmp.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento").toLocalDate());
                    autores.add(autorTmp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    /**
     * Elimina un autor de la base de datos.
     *
     * @param entityDelete el autor que se desea eliminar.
     * @return el autor eliminado.
     */
    @Override
    public Autor deleteEntity(Autor entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entityDelete;
    }



@Override
    public void close() throws IOException {

    }

    public static AutorDAO build() {
        return new AutorDAO();
    }
}
