package model.dao;

import model.connection.ConnectionMariaDB;
import model.entity.Autor;
import model.entity.Usuario;
import interfaces.IDAO;

import java.io.IOException;
import java.sql.*;

/**
 * Clase DAO para manejar las operaciones de la tabla "Autor" en la base de datos.
 */
public class AutorDAO implements IDAO<Autor, Integer> {
    private Connection connection;

    public AutorDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Autor(Nombre, Nacionalidad, Fecha_Nacimiento) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Autor WHERE Id = ?";
    private static final String UPDATE = "UPDATE Autor SET Nombre = ?, Nacionalidad= ?, Fecha_Nacimiento= ? WHERE Id = ?";
    private static final String FINDID = "SELECT Id, Nombre, Nacionalidad, Fecha_Nacimiento FROM Autor WHERE Id = ?";

    /**
     * Almacena un autor en la base de datos.
     * Si el autor ya existe (por su ID), lo actualiza; si no, lo inserta como nuevo.
     *
     * @param entity El autor que se desea almacenar.
     * @return El autor almacenado en la base de datos.
     */
    @Override
    public Autor store(Autor entity) {
        if (entity != null) {
            int idAutorTmp = entity.getId();
            if (idAutorTmp > 0) {
                Autor autorTmp = findId(idAutorTmp);
                if (autorTmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        // Inserta un nuevo autor
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getNacionalidad());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaNacimiento()));

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        // Actualiza un autor existente
                        preparedStatement.setInt(4, entity.getId());
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getNacionalidad());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaNacimiento()));

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Busca un autor en la base de datos utilizando su identificador (ID).
     *
     * @param entityId El ID del autor que se desea buscar.
     * @return El autor encontrado con todos sus datos; devuelve null si no existe.
     */
    @Override
    public Autor findId(Integer entityId) {
        Autor autor = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Autor autorTmp = new Autor();
                    autorTmp.setId(resultSet.getInt("Id"));
                    autorTmp.setNombre(resultSet.getString("Nombre"));
                    autorTmp.setNacionalidad(resultSet.getString("Nacionalidad"));
                    autorTmp.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento").toLocalDate());
                    autor = autorTmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    }

    /**
     * Elimina un autor de la base de datos.
     *
     * @param entityDelete El autor que se desea eliminar.
     * @return El autor eliminado.
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
