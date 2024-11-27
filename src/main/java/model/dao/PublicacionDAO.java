package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Editorial;
import model.entity.Enum.Tipo_Enum;
import model.entity.Publicacion;
import model.entity.Usuario;

import java.io.IOException;
import java.sql.*;

public class PublicacionDAO implements IDAO<Publicacion, Integer> {
    private Connection connection;

    public PublicacionDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Publicacion(Titulo, FechaPublicacion, Tipo, Categoria_ID, Editorial_ID) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM Publicacion WHERE Id = ?";
    private static final String UPDATE = "UPDATE Publicacion SET Titulo = ?, FechaPublicacion = ?, Tipo = ?, Categoria_ID = ?, Editorial_ID = ? WHERE Id = ?";
    private static final String FINDID = "SELECT Id, Titulo, FechaPublicacion, Tipo FROM publicacion WHERE Id = ?";

    @Override
    public Publicacion store(Publicacion entity) {
        if (entity != null) {
            int idPublicacionTmp = entity.getId();
            if (idPublicacionTmp > 0) {
                Publicacion publicacionTmp = findId(idPublicacionTmp);
                if (publicacionTmp == null) {
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

    @Override
    public Publicacion findId(Integer entityId) {
        Publicacion publicacion = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    publicacion = new Publicacion();
                    publicacion.setId(resultSet.getInt("Id"));
                    publicacion.setTitulo(resultSet.getString("Nombre"));
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

    @Override
    public Publicacion deleteEntity(Publicacion entityDelete) {
        if (entityDelete != null){
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
                preparedStatement.setInt(1,entityDelete.getId());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
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
