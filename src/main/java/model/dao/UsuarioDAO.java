package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.Sesion;
import model.entity.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDAO<Usuario, Integer> {

    private Connection connection;

    public UsuarioDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Usuario(Nombre, Clave, Email) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Usuario WHERE Id = ?";
    private static final String UPDATE = "UPDATE Usuario SET Nombre = ?, Clave = ?, Email = ? WHERE Id = ?";
    //Select
    private static final String FINDID = "SELECT Id, Nombre, Clave, Email FROM Usuario WHERE Id = ?";
    private static final String FINDBYIDENTIFICATOR = "SELECT Id, Nombre, Clave, Email FROM Usuario WHERE Email = ? and Clave = ?";
    private static final String FINDALL = "SELECT Id, Nombre, Email FROM Usuario";

    /**
     * Clase DAO para gestionar las operaciones CRUD de la entidad Usuario en la base de datos.
     */
    @Override
    public Usuario store(Usuario entity) {
        if (entity != null) {
            int idUsuarioTmp = entity.getId();
            if (idUsuarioTmp >= 0) {
                Usuario usuarioTmp = findId(idUsuarioTmp);
                if (usuarioTmp == null) {
                    // Inserta un nuevo usuario
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getClave());
                        preparedStatement.setString(3, entity.getEMAIL());

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error al insertar un nuevo usuario: " + e.getMessage());
                    }
                } else {
                    // Actualiza un usuario existente
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getClave());
                        preparedStatement.setString(3, entity.getEMAIL());
                        preparedStatement.setInt(4, entity.getId());

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error al actualizar el usuario: " + e.getMessage());
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Busca un usuario en la base de datos utilizando su identificador (ID).
     *
     * @param entityId El ID del usuario que se desea buscar.
     * @return El usuario encontrado con todos sus datos; devuelve null si no existe.
     */
    @Override
    public Usuario findId(Integer entityId) {
        Usuario usuario = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("Id"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setClave(resultSet.getString("Clave"));
                    usuario.setEMAIL(resultSet.getString("Email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el usuario con ID " + entityId + ": " + e.getMessage());
        }

        return usuario;
    }

    /**
     * Busca un usuario en la base de datos utilizando su correo electrónico y clave.
     * Si el usuario existe, retorna un objeto {@link Usuario} con la información encontrada.
     *
     * @param email El correo electrónico del usuario que se desea buscar.
     * @param clave La clave asociada al usuario.
     * @return Un objeto {@link Usuario} con los datos del usuario si se encuentra;
     *         de lo contrario, retorna {@code null}.
     */
    public Usuario findByIdentificator(String email, String clave) {
        Usuario usuario = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDBYIDENTIFICATOR)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, clave);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt("Id"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setClave(resultSet.getString("Clave"));
                    usuario.setEMAIL(resultSet.getString("Email"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }
        return usuario;
    }

    /**
     * Recupera una lista de todos los usuarios almacenados en la base de datos.
     * Este método no incluye la clave del usuario en la información retornada.
     *
     * @return Una lista de objetos {@link Usuario}, cada uno representando un usuario.
     *         Si no hay usuarios en la base de datos, retorna una lista vacía.
     */
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getInt("Id"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setEMAIL(resultSet.getString("Email"));

                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de usuarios: " + e.getMessage());
        }
        return usuarios;
    }


    /**
     * Elimina un usuario específico de la base de datos.
     *
     * @param entityDelete El usuario que se desea eliminar.
     * @return El usuario que se intentó eliminar.
     */
    @Override
    public Usuario deleteEntity(Usuario entityDelete) {
        if (entityDelete != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, entityDelete.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al eliminar el usuario con ID " + entityDelete.getId() + ": " + e.getMessage());
            }
        }
        return entityDelete;
    }

    @Override
    public void close() throws IOException {

    }

    public static UsuarioDAO build() {
        return new UsuarioDAO();
    }
}
