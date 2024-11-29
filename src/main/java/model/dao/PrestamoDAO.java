package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Enum.Estado_Enum;
import model.entity.Prestamo;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO implements IDAO<Prestamo, Prestamo> {
    private Connection connection;

    private PrestamoDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Prestamo(Usuario_ID, Publicacion_ID, FechaPrestamo, FechaDevolucion, Estado) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM Prestamo WHERE Usuario_ID = ? and Publicacion_ID = ? and FechaPrestamo = ?";
    private static final String UPDATE = "UPDATE Prestamo SET FechaDevolucion = ?, Estado = ? WHERE Usuario_ID = ? AND Publicacion_ID = ? AND FechaPrestamo = ?";

    private static final String FINDID = "select Usuario_ID, Publicacion_ID, FechaPrestamo, FechaDevolucion, Estado FROM prestamo WHERE Usuario_ID = ? and Publicacion_ID = ? and FechaPrestamo = ?";
    private static final String FINDALL = "select Usuario_ID, Publicacion_ID, FechaPrestamo, FechaDevolucion, Estado FROM prestamo";

    /**
     * Almacena un objeto Prestamo en la base de datos.
     * Si el préstamo no existe (según el ID del usuario y la publicación), lo inserta como nuevo.
     * Si ya existe, actualiza los datos del préstamo.
     *
     * @param entity El objeto Prestamo que se desea almacenar.
     * @return El mismo objeto Prestamo que fue almacenado o actualizado.
     */
    @Override
    public Prestamo store(Prestamo entity) {
        if (entity != null) {
            int idprestamoTmp = entity.getUsuario().getId();
            if (idprestamoTmp > 0) {
                Prestamo prestamoTmp = findId(entity);
                if (prestamoTmp == null) {
                    // Insertar un nuevo préstamo
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setInt(1, entity.getUsuario().getId());
                        preparedStatement.setInt(2, entity.getPublicacion().getId());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaPrestamo()));
                        preparedStatement.setDate(4, Date.valueOf(entity.getFechaDevolucion()));
                        preparedStatement.setString(5, entity.getEstado().toString());

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Actualizar un préstamo existente
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setDate(1, Date.valueOf(entity.getFechaDevolucion()));
                        preparedStatement.setString(2, entity.getEstado().toString());

                        // Condiciones para identificar el préstamo
                        preparedStatement.setInt(3, entity.getUsuario().getId());
                        preparedStatement.setInt(4, entity.getPublicacion().getId());
                        preparedStatement.setDate(5, Date.valueOf(entity.getFechaPrestamo()));

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
     * Busca un préstamo en la base de datos según el ID del usuario.
     * Si el préstamo existe, construye un objeto Prestamo con los datos obtenidos.
     *
     * @param entityId El ID del usuario relacionado al préstamo que se desea buscar.
     * @return Un objeto Prestamo con los datos encontrados, o null si no existe.
     */
    @Override
    public Prestamo findId(Prestamo entityId) {
        Prestamo prestamo = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId.getUsuario().getId());
            preparedStatement.setInt(2, entityId.getPublicacion().getId());
            preparedStatement.setDate(3, Date.valueOf(entityId.getFechaPrestamo()));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    prestamo = new Prestamo();
                    prestamo.setUsuario(UsuarioDAO.build().findId(resultSet.getInt("Usuario_ID")));
                    prestamo.setPublicacion(PublicacionDAO.build().findId(resultSet.getInt("Publicacion_ID")));
                    prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
                    prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion").toLocalDate());
                    prestamo.setEstado(Estado_Enum.valueOf(resultSet.getString("Estado")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    public List<Prestamo> findAll(){
        List<Prestamo> prestamos = new ArrayList<>();


        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setUsuario(UsuarioDAO.build().findId(resultSet.getInt("Usuario_ID")));
                    prestamo.setPublicacion(PublicacionDAO.build().findId(resultSet.getInt("Publicacion_ID")));
                    prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo").toLocalDate());
                    prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion").toLocalDate());
                    prestamo.setEstado(Estado_Enum.valueOf(resultSet.getString("Estado")));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    /**
     * Elimina un objeto Prestamo de la base de datos.
     * El préstamo se identifica por el usuario, la publicación y la fecha del préstamo.
     *
     * @param entityDelete El objeto Prestamo que se desea eliminar.
     * @return El mismo objeto Prestamo que fue eliminado.
     */
    @Override
    public Prestamo deleteEntity(Prestamo entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getUsuario().getId());
                preparedStatement.setInt(2, entityDelete.getPublicacion().getId());
                preparedStatement.setDate(3, Date.valueOf(entityDelete.getFechaPrestamo()));

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

    public static PrestamoDAO build() {
        return new PrestamoDAO();
    }

}
