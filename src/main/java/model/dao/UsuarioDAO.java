package model.dao;

import model.interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IDAO<Usuario, Integer> {

    private Connection connection;

    public UsuarioDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    // Esto es porsi necesitamos insertar con id
    //private static final String INSERT = "INSERT INTO Usuario(Id, Nombre, Clave, Email) VALUES (?,?,?,?)";

    private static final String INSERT = "INSERT INTO Usuario(Nombre, Clave, Email) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Usuario WHERE Id = ?";
    private static final String UPDATE = "UPDATE Usuario SET Nombre = ?, Clave = ?, Email = ? WHERE Id = ?";

    //Select
    private static final String FINDID = "SELECT ID, Nombre, Clave, Email FROM usuario WHERE Id = ?";

    /**
     * Tiene dos cosas que puede hacer actualizar un usuario o insertarlo en la base de datos
     * @param entity el usuario que quiere almacenar
     * @return el usuario que a insertado en la base de datos
     */
    @Override
    public Usuario store(Usuario entity) {
        if (entity != null) {
            int idUsuariotmp = entity.getId();
            if (idUsuariotmp > 0) {
                Usuario usuarioTmp = findId(idUsuariotmp);
                if (usuarioTmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        // id se crea porque esta en autoincrement
                        // preparedStatement.setInt(1, entity.getId());

                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getClave());
                        preparedStatement.setString(3, entity.getEMAIL());

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        // Con lo que se va a comparar
                        preparedStatement.setInt(4, entity.getId());
                        //Lo que se va a editar
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getClave());
                        preparedStatement.setString(3, entity.getEMAIL());

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
     * Busca un usuario por su identificador
     * @param entityId El identificador de usuario
     * @return El usuario rellenado
     */
    @Override
    public Usuario findId(Integer entityId) {

        Usuario usuario = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuarioTmp = new Usuario();
                    usuarioTmp.setId(resultSet.getInt("Id"));
                    usuarioTmp.setNombre(resultSet.getString("Nombre"));
                    usuarioTmp.setClave(resultSet.getString("Clave"));
                    usuarioTmp.setEMAIL(resultSet.getString("Email"));
                    usuario = usuarioTmp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return usuario;
    }

    /**
     * Se le pasa un usuario y borra ese usuario de la base de datos
     *
     * @param entityDelete el usuario que se desea borrar
     * @return el usuario que as enviado que deseabas borrar
     */
    @Override
    public Usuario deleteEntity(Usuario entityDelete) {
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
}
