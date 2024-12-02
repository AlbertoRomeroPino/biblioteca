package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Enum.Periodicidad_Enum;
import model.entity.Libro;
import model.entity.Publicacion;
import model.entity.Revista;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevistaDAO implements IDAO<Revista, Integer> {

    private Connection connection;

    public RevistaDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Revista(Publicacion_ID, ISSN, Periodicidad) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Revista WHERE Publicacion_ID = ?";
    private static final String UPDATE = "UPDATE Revista SET ISSN = ?, Periodicidad = ? WHERE Publicacion_ID = ?";
    private static final String FINDID = "SELECT Publicacion_Id, ISSN, Periodicidad FROM revista WHERE Publicacion_Id = ?";
    private static final String FINDALL = "SELECT Publicacion_Id, ISSN, Periodicidad FROM revista";


    /**
     * Almacena un objeto Revista en la base de datos.
     * Si la revista no existe (según su ID), la inserta como nueva.
     * Si ya existe, actualiza los datos de la revista.
     *
     * @param entity El objeto Revista que se desea almacenar.
     * @return El mismo objeto Revista que fue almacenado o actualizado.
     */
    @Override
    public Revista store(Revista entity) {
        if (entity != null) {
            int idRevistaTmp = entity.getId();
            if (idRevistaTmp >= 0) {
                Revista revistaTmp = findId(idRevistaTmp);
                if (revistaTmp == null) {
                    // Insertar una nueva revista
                    // Insertar la publicación asociada
                    Publicacion publicacionTmp = new Publicacion(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getFecha_publicacion(),
                            entity.getTipo(),
                            entity.getCategoria(),
                            entity.getEditorial(),
                            entity.getPrestamos()
                    );
                    PublicacionDAO.build().store(publicacionTmp);

                    List<Publicacion> publicaciones = PublicacionDAO.build().findAll();
                    for (Publicacion publicacion : publicaciones){
                        if (publicacionTmp.equals(publicacion)){
                            publicacionTmp=publicacion;
                        }
                    }

                    // Insertar la revista
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setInt(1, publicacionTmp.getId());
                        preparedStatement.setString(2, entity.getISSN());
                        preparedStatement.setString(3, entity.getPeriodicidad().toString());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Actualizar una revista existente
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getISSN());
                        preparedStatement.setString(2, entity.getPeriodicidad().toString());
                        preparedStatement.setInt(3, entity.getId());

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
     * Busca una revista en la base de datos según su ID.
     * Si la revista existe, construye un objeto Revista con los datos obtenidos.
     *
     * @param entityId El ID de la revista que se desea buscar.
     * @return Un objeto Revista con los datos encontrados, o null si no existe.
     */
    @Override
    public Revista findId(Integer entityId) {
        Revista revista = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    revista = new Revista();

                    // Atributos heredados de Publicación
                    Publicacion publicacion = PublicacionDAO.build().findId(resultSet.getInt("Publicacion_Id"));
                    revista.setId(publicacion.getId());
                    revista.setTitulo(publicacion.getTitulo());
                    revista.setFecha_publicacion(publicacion.getFecha_publicacion());
                    revista.setTipo(publicacion.getTipo());
                    revista.setCategoria(publicacion.getCategoria());
                    revista.setEditorial(publicacion.getEditorial());

                    // Atributos específicos de Revista
                    revista.setISSN(resultSet.getString("ISSN"));
                    revista.setPeriodicidad(Periodicidad_Enum.valueOf(resultSet.getString("Periodicidad")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revista;
    }

    public List<Revista> findAll() {
        List<Revista> revistas = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Revista revista = new Revista();

                    // Atributos heredados de Publicación
                    Publicacion publicacion = PublicacionDAO.build().findId(resultSet.getInt("Publicacion_Id"));
                    revista.setId(publicacion.getId());
                    revista.setTitulo(publicacion.getTitulo());
                    revista.setFecha_publicacion(publicacion.getFecha_publicacion());
                    revista.setTipo(publicacion.getTipo());
                    revista.setCategoria(publicacion.getCategoria());
                    revista.setEditorial(publicacion.getEditorial());

                    // Atributos específicos de Revista
                    revista.setISSN(resultSet.getString("ISSN"));
                    revista.setPeriodicidad(Periodicidad_Enum.valueOf(resultSet.getString("Periodicidad")));

                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

    /**
     * Elimina un objeto Revista de la base de datos.
     * Dado que Revista hereda de Publicacion, este método delega
     * la eliminación al DAO de Publicación, lo que garantiza que
     * el registro de revista y sus datos relacionados se eliminen correctamente.
     *
     * @param entityDelete El objeto Revista que se desea eliminar.
     * @return El mismo objeto Revista que fue eliminado.
     */
    @Override
    public Revista deleteEntity(Revista entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Publicacion publicacionTmp = new Publicacion(
                    entityDelete.getId(),
                    entityDelete.getTitulo(),
                    entityDelete.getFecha_publicacion(),
                    entityDelete.getTipo(),
                    entityDelete.getCategoria(),
                    entityDelete.getEditorial(),
                    entityDelete.getPrestamos()
            );

            PublicacionDAO.build().deleteEntity(publicacionTmp);
        }
        return entityDelete;
    }


    @Override
    public void close() throws IOException {

    }

    public static RevistaDAO build() {
        return new RevistaDAO();
    }
}
