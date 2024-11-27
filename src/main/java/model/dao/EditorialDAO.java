package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Autor;
import model.entity.Editorial;
import model.entity.Usuario;

import java.io.IOException;
import java.sql.*;

public class EditorialDAO implements IDAO<Editorial, Integer> {
    private Connection connection;

    public EditorialDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Editorial (Nombre, Pais, Fecha_Fundacion) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Editorial WHERE Id = ?";
    private static final String UPDATE = "UPDATE Editorial SET Nombre = ?, Pais = ?, Fecha_Fundacion = ? WHERE Id = ?";
    private static final String FINDID =  "SELECT Id, Nombre, Pais, Fecha_Fundacion FROM Editorial WHERE Id = ?";

    /**
     * Almacena una editorial en la base de datos.
     * Si la editorial no existe (por su ID), la inserta como un nuevo registro.
     * Si ya existe, se actualizan sus datos.
     *
     * @param entity La editorial que se desea almacenar.
     * @return La editorial almacenada o actualizada en la base de datos.
     */
    @Override
    public Editorial store(Editorial entity) {
        if (entity != null) {
            int idEditorialTmp = entity.getId();
            if (idEditorialTmp > 0) {
                Editorial editorialTmp = findId(idEditorialTmp);
                if (editorialTmp == null) {
                    // Inserta una nueva editorial
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getPais());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFecha_fundacion()));

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Actualiza una editorial existente
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getPais());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFecha_fundacion()));
                        preparedStatement.setInt(4, entity.getId());

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
     * Busca una editorial en la base de datos utilizando su identificador (ID).
     *
     * @param entityId El ID de la editorial que se desea buscar.
     * @return La editorial encontrada con todos sus datos, o null si no existe.
     */
    @Override
    public Editorial findId(Integer entityId) {
        Editorial editorial = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    editorial = new Editorial();
                    editorial.setId(resultSet.getInt("Id"));
                    editorial.setNombre(resultSet.getString("Nombre"));
                    editorial.setPais(resultSet.getString("Pais"));
                    editorial.setFecha_fundacion(resultSet.getDate("Fecha_Fundacion").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editorial;
    }

    /**
     * Elimina una editorial específica de la base de datos.
     *
     * @param entityDelete La editorial que se desea eliminar.
     * @return La editorial que se intentó eliminar.
     */
    @Override
    public Editorial deleteEntity(Editorial entityDelete) {
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

    public static EditorialDAO build() {
        return new EditorialDAO();
    }
}
