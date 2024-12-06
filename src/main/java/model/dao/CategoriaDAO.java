package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Categoria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO implements IDAO<Categoria, Integer> {

    private Connection connection;

    public CategoriaDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Categoria(Nombre) VALUES (?)";
    private static final String DELETE = "DELETE FROM Categoria WHERE Id = ?";
    private static final String UPDATE = "UPDATE Categoria SET Nombre = ? WHERE Id = ?";
    private static final String FINDID = "SELECT Id, Nombre FROM Categoria WHERE Id = ?";
    private static final String FINDALL = "SELECT Id, Nombre FROM Categoria";

    /**
     * Almacena una categoría en la base de datos.
     * Si la categoría no existe (por su ID), la inserta como un nuevo registro.
     * Si ya existe, se actualizan sus datos.
     *
     * @param entity La categoría que se desea almacenar.
     * @return La categoría almacenada o actualizada en la base de datos.
     */
    @Override
    public Categoria store(Categoria entity) {
        if (entity != null) {
            int idCategoriatmp = entity.getId();
            if (idCategoriatmp >= 0) {
                Categoria categoriaTmp = findId(idCategoriatmp);
                if (categoriaTmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        // Inserta una nueva categoría
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        // Actualiza una categoría existente
                        preparedStatement.setInt(2, entity.getId());
                        preparedStatement.setString(1, entity.getNombre());
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
     * Busca una categoría en la base de datos por su identificador (ID).
     *
     * @param entityId El ID de la categoría que se desea buscar.
     * @return La categoría encontrada con todos sus datos, o una categoría vacía si no se encuentra.
     */
    @Override
    public Categoria findId(Integer entityId) {
        Categoria categoria = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Categoria categoriaTmp = new Categoria();
                    categoriaTmp.setId(resultSet.getInt("Id"));
                    categoriaTmp.setNombre(resultSet.getString("Nombre"));
                    categoria = categoriaTmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public List<Categoria> findAll() {
        List<Categoria> categorias = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Categoria categoriaTmp = new Categoria();
                    categoriaTmp.setId(resultSet.getInt("Id"));
                    categoriaTmp.setNombre(resultSet.getString("Nombre"));
                    categorias.add(categoriaTmp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    /**
     * Elimina una categoría específica de la base de datos.
     *
     * @param entityDelete La categoría que se desea eliminar.
     * @return La categoría eliminada.
     */
    @Override
    public Categoria deleteEntity(Categoria entityDelete) {
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

    public static CategoriaDAO build() {
        return new CategoriaDAO();
    }
}
