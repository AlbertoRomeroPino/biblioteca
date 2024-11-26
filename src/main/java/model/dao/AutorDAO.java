package model.dao;

import model.connection.ConnectionMariaDB;
import model.entity.Autor;
import model.entity.Usuario;
import interfaces.IDAO;

import java.io.IOException;
import java.sql.*;

public class AutorDAO implements IDAO<Autor, Integer> {
    private Connection connection;

    public AutorDAO() {
        connection = ConnectionMariaDB.getConnection();
    }

    private static final String INSERT = "INSERT INTO Autor(Nombre, Nacionalidad, Fecha_Nacimiento) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Autor WHERE Id = ?";
    private static final String UPDATE = "UPDATE Autor SET Nombre = ?, Nacionalidad= ?, Fecha_Nacimiento= ? WHERE Id = ?";
    private static final String FINDID = "select Id, Nombre, Nacionalidad, Fecha_Nacimiento FROM autor WHERE Id = ?";


    @Override
    public Autor store(Autor entity) {
        if (entity != null) {
            int idAutorTmp = entity.getId();
            if (idAutorTmp > 0) {
                Autor autorTmp = findId(idAutorTmp);
                if (autorTmp == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setString(1, entity.getNombre());
                        preparedStatement.setString(2, entity.getNacionalidad());
                        preparedStatement.setDate(3, Date.valueOf(entity.getFechaNacimiento()));

                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
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

    @Override
    public Autor findId(Integer entityId) {
        Autor autor = new Autor();
        try (PreparedStatement preparedStatement = connection.prepareStatement((FINDID))) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Autor autorTmp = new Autor();
                    autorTmp.setId(resultSet.getInt("Id"));
                    autorTmp.setNombre(resultSet.getString("Nombre"));
                    autorTmp.setNacionalidad(resultSet.getString("Nacionalidad"));
                    autorTmp.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento").toLocalDate());


                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Usuario deleteEntity(Usuario entityDelete) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
