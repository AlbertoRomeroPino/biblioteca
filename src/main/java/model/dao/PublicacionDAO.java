package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.*;
import model.entity.Enum.Tipo_Enum;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicacionDAO implements IDAO<Publicacion, Integer> {
    private Connection connection;

    public PublicacionDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Publicacion(Titulo, FechaPublicacion, Tipo, Categoria_ID, Editorial_ID) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM Publicacion WHERE Id = ?";
    private static final String UPDATE = "UPDATE Publicacion SET Titulo = ?, FechaPublicacion = ?, Tipo = ?, Categoria_ID = ?, Editorial_ID = ? WHERE Id = ?";
    //Select
    private static final String FINDID = "SELECT Id, Titulo, FechaPublicacion, Tipo, Categoria_ID, Editorial_ID FROM publicacion WHERE Id = ?";
    private static final String FINDALL = "SELECT Id, Titulo, FechaPublicacion, Tipo, Categoria_ID, Editorial_ID FROM publicacion";
    private static final String FINDJOIN = "SELECT p.ID, p.Titulo, p.FechaPublicacion, p.Tipo, c.Nombre AS CategoriaNombre, e.Nombre AS EditorialNombre " +
            " FROM Publicacion p " +
            " JOIN Categoria c ON p.Categoria_ID = c.ID JOIN Editorial e ON p.Editorial_ID = e.ID";

    /**
     * Almacena un objeto Publicacion en la base de datos.
     * Si la publicación no existe (según su ID), la inserta como nueva.
     * Si ya existe, actualiza los datos de la publicación.
     *
     * @param entity El objeto Publicacion que se desea almacenar.
     * @return El mismo objeto Publicacion que fue almacenado o actualizado.
     */
    @Override
    public Publicacion store(Publicacion entity) {
        if (entity != null) {
            int idPublicacionTmp = entity.getId();
            if (idPublicacionTmp >= 0) {
                Publicacion publicacionTmp = findId(idPublicacionTmp);
                if (publicacionTmp == null) {
                    // Insertar una nueva publicación
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getTitulo());
                        preparedStatement.setDate(2, Date.valueOf(entity.getFecha_publicacion()));
                        preparedStatement.setString(3, entity.getTipo().toString());
                        preparedStatement.setInt(4, entity.getCategoria().getId());
                        preparedStatement.setInt(5, entity.getEditorial().getId());

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Actualizar una publicación existente
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getTitulo());
                        preparedStatement.setDate(2, Date.valueOf(entity.getFecha_publicacion()));
                        preparedStatement.setString(3, entity.getTipo().toString());
                        preparedStatement.setInt(4, entity.getCategoria().getId());
                        preparedStatement.setInt(5, entity.getEditorial().getId());
                        preparedStatement.setInt(6, entity.getId());

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
     * Busca una publicación en la base de datos según su ID.
     * Si la publicación existe, construye un objeto Publicacion con los datos obtenidos.
     *
     * @param entityId El ID de la publicación que se desea buscar.
     * @return Un objeto Publicacion con los datos encontrados, o null si no existe.
     */
    @Override
    public Publicacion findId(Integer entityId) {
        Publicacion publicacion = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    publicacion = new Publicacion();
                    publicacion.setId(resultSet.getInt("Id"));
                    publicacion.setTitulo(resultSet.getString("Titulo"));
                    publicacion.setFecha_publicacion(resultSet.getDate("FechaPublicacion").toLocalDate());
                    publicacion.setTipo(Tipo_Enum.valueOf(resultSet.getString("Tipo")));
                    publicacion.setCategoria(CategoriaDAO.build().findId(resultSet.getInt("Categoria_ID")));
                    publicacion.setEditorial(EditorialDAO.build().findId(resultSet.getInt("Editorial_ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacion;
    }

    /**
     * Obtiene una lista con todas las publicaciones almacenadas en la base de datos.
     * Este método recupera las asociaciones con las categorías y editoriales correspondientes,
     * así como información sobre el tipo, título y fecha de publicación.
     *
     * @return Una lista de objetos {@link Publicacion}, cada uno representando una publicación completa
     *         con su categoría, editorial, tipo, título y fecha. Si no hay publicaciones registradas,
     *         se retorna una lista vacía.
     */
    public List<Publicacion> findAll() {
        List<Publicacion> publicacions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Publicacion publicacion = new Publicacion();
                    publicacion.setId(resultSet.getInt("Id"));
                    publicacion.setTitulo(resultSet.getString("Titulo"));
                    publicacion.setFecha_publicacion(resultSet.getDate("FechaPublicacion").toLocalDate());
                    publicacion.setTipo(Tipo_Enum.valueOf(resultSet.getString("Tipo")));
                    publicacion.setCategoria(CategoriaDAO.build().findId(resultSet.getInt("Categoria_ID")));
                    publicacion.setEditorial(EditorialDAO.build().findId(resultSet.getInt("Editorial_ID")));

                    publicacions.add(publicacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacions;
    }

    /**
     * Obtiene una lista de publicaciones con información adicional utilizando una consulta que incluye uniones (joins).
     * Este método incluye datos como el nombre de la categoría y de la editorial asociados a la publicación,
     * además del título, tipo y fecha de publicación.
     *
     * @return Una lista de objetos {@link Publicacion}, cada uno con datos relacionados sobre la categoría,
     *         editorial, tipo, título y fecha. Si no hay publicaciones registradas, se retorna una lista vacía.
     */
    public List<Publicacion> findJoinPublicacion() {
        List<Publicacion> publicacions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDJOIN)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Categoria categoria = new Categoria();
                    Editorial editorial = new Editorial();
                    categoria.setNombre(resultSet.getString("CategoriaNombre"));
                    editorial.setNombre(resultSet.getString("EditorialNombre"));

                    Publicacion publicacion = new Publicacion();
                    publicacion.setId(resultSet.getInt("Id"));
                    publicacion.setTitulo(resultSet.getString("Titulo"));
                    publicacion.setFecha_publicacion(resultSet.getDate("FechaPublicacion").toLocalDate());
                    publicacion.setTipo(Tipo_Enum.valueOf(resultSet.getString("Tipo")));
                    publicacion.setCategoria(categoria);
                    publicacion.setEditorial(editorial);

                    publicacions.add(publicacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacions;
    }


    /**
     * Elimina un objeto Publicacion de la base de datos.
     * El registro se identifica únicamente por su ID.
     *
     * @param entityDelete El objeto Publicacion que se desea eliminar.
     * @return El mismo objeto Publicacion que fue eliminado.
     */
    @Override
    public Publicacion deleteEntity(Publicacion entityDelete) {
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

    public static PublicacionDAO build() {
        return new PublicacionDAO();
    }
}
