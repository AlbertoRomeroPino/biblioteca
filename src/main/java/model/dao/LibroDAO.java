package model.dao;

import interfaces.IDAO;
import model.connection.ConnectionMariaDB;
import model.entity.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements IDAO<Libro, Integer> {

    private Connection connection;

    public LibroDAO() {
        connection = ConnectionMariaDB.getConnection();
    }


    private static final String INSERT = "INSERT INTO Libro(Publicacion_ID, ISBN, Autor_ID) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Libro WHERE Publicacion_ID = ?";
    private static final String UPDATE = "UPDATE Libro SET ISBN = ?, Autor_ID = ? WHERE Publicacion_ID = ?";
    private static final String FINDID = "SELECT Publicacion_Id, ISBN, Autor_Id FROM libro WHERE Publicacion_Id = ?";
    private static final String FINDALL = "SELECT Publicacion_Id, ISBN, Autor_Id FROM libro";
    private static final String FINDJOIN = "SELECT p.ID AS Id, p.Titulo AS Titulo, p.FechaPublicacion AS Publicacion, " +
            " c.Nombre AS Categoria, e.Nombre AS Editorial, l.ISBN AS ISBN, " +
            " a.Nombre AS Autor " +
            " FROM Publicacion p " +
            " JOIN Categoria c ON p.Categoria_ID = c.ID " +
            " JOIN Editorial e ON p.Editorial_ID = e.ID " +
            " JOIN Libro l ON p.ID = l.Publicacion_ID " +
            " JOIN Autor a ON l.Autor_ID = a.ID;";


    /**
     * Almacena un objeto Libro en la base de datos.
     * Si el libro no existe (según su ID), lo inserta como un nuevo registro.
     * Si ya existe, actualiza sus datos.
     *
     * @param entity El objeto Libro que se desea almacenar.
     * @return El mismo objeto Libro que fue almacenado o actualizado.
     */
    @Override
    public Libro store(Libro entity) {

        if (entity != null) {
            int idLibroTmp = entity.getId();
            if (idLibroTmp >= 0) {
                // Buscar el libro por ID
                Libro libroTmp = findId(idLibroTmp);
                if (libroTmp == null) {
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
                    for (Publicacion publicacion : publicaciones) {

                        if (publicacionTmp.getTitulo().equals(publicacion.getTitulo()) &&
                                publicacionTmp.getFecha_publicacion().equals(publicacion.getFecha_publicacion()) &&
                                publicacionTmp.getTipo().equals(publicacion.getTipo()) &&
                                publicacionTmp.getCategoria().equals(publicacion.getCategoria()) &&
                                publicacionTmp.getEditorial().equals(publicacion.getEditorial())){
                            publicacionTmp = publicacion;
                        }
                    }


                    // Si no existe, insertar un nuevo libro
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                        preparedStatement.setInt(1, publicacionTmp.getId());
                        preparedStatement.setString(2, entity.getISBN());
                        preparedStatement.setInt(3, entity.getAutor().getId());

                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Si ya existe, actualizar los datos
                    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                        preparedStatement.setString(1, entity.getISBN());
                        preparedStatement.setInt(2, entity.getAutor().getId());
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
     * Busca un libro en la base de datos según su ID.
     * Si el libro existe, construye y retorna un objeto Libro con los datos encontrados.
     *
     * @param entityId El ID del libro que se desea buscar.
     * @return Un objeto Libro con los datos encontrados, o null si no existe.
     */
    @Override
    public Libro findId(Integer entityId) {
        Libro libro = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDID)) {
            preparedStatement.setInt(1, entityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    libro = new Libro();


                    // Atributos heredados de Publicación
                    Publicacion publicacion = PublicacionDAO.build().findId(resultSet.getInt("Publicacion_Id"));
                    libro.setId(publicacion.getId());
                    libro.setTitulo(publicacion.getTitulo());
                    libro.setFecha_publicacion(publicacion.getFecha_publicacion());
                    libro.setTipo(publicacion.getTipo());
                    libro.setCategoria(publicacion.getCategoria());
                    libro.setEditorial(publicacion.getEditorial());


                    // Atributos específicos de Libro
                    libro.setISBN(resultSet.getString("ISBN"));
                    libro.setAutor(AutorDAO.build().findId(resultSet.getInt("Autor_Id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    /**
     * Obtiene una lista con todos los libros almacenados en la base de datos.
     * Este método utiliza la relación con la tabla de publicaciones para obtener los datos comunes
     * y luego añade la información específica de los libros, como el ISBN y el autor.
     *
     * @return Una lista de objetos {@link Libro}, cada uno representando un libro con su información completa.
     * Si no hay libros registrados, se retorna una lista vacía.
     */
    public List<Libro> findAll() {
        List<Libro> libros = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Libro libro = new Libro();

                    // Atributos heredados de Publicación
                    Publicacion publicacion = PublicacionDAO.build().findId(resultSet.getInt("Publicacion_Id"));
                    libro.setId(publicacion.getId());
                    libro.setTitulo(publicacion.getTitulo());
                    libro.setFecha_publicacion(publicacion.getFecha_publicacion());
                    libro.setTipo(publicacion.getTipo());
                    libro.setCategoria(publicacion.getCategoria());
                    libro.setEditorial(publicacion.getEditorial());

                    // Atributos específicos de Libro
                    libro.setISBN(resultSet.getString("ISBN"));
                    libro.setAutor(AutorDAO.build().findId(resultSet.getInt("Autor_Id")));

                    libros.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    /**
     * Obtiene una lista de libros utilizando una consulta que incluye uniones (joins)
     * para obtener información adicional como el nombre de la categoría, editorial y autor.
     *
     * @return Una lista de objetos {@link Libro}, cada uno con sus datos completos, incluyendo
     * relaciones con categorías, editoriales y autores. Si no hay libros registrados, se retorna una lista vacía.
     */
    public List<Libro> findJoinLibro() {
        List<Libro> libros = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDJOIN)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Crear objetos relacionados con la publicación
                    Categoria categoria = new Categoria();
                    Editorial editorial = new Editorial();
                    categoria.setNombre(resultSet.getString("Categoria"));
                    editorial.setNombre(resultSet.getString("Editorial"));

                    // Crear y llenar el objeto Libro
                    Libro libro = new Libro();
                    libro.setId(resultSet.getInt("Id"));
                    libro.setTitulo(resultSet.getString("Titulo"));
                    libro.setFecha_publicacion(resultSet.getDate("Publicacion").toLocalDate());
                    libro.setCategoria(categoria);
                    libro.setEditorial(editorial);
                    libro.setISBN(resultSet.getString("ISBN"));

                    // Asociar el autor al libro
                    Autor autor = new Autor();
                    autor.setNombre(resultSet.getString("Autor"));
                    libro.setAutor(autor);

                    // Agregar el libro a la lista
                    libros.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    /**
     * Elimina un objeto Libro de la base de datos.
     * Este método delega la eliminación al DAO de Publicación, dado que Libro hereda de Publicacion.
     * Esto garantiza que el registro y los datos relacionados del libro se eliminen correctamente.
     *
     * @param entityDelete El objeto Libro que se desea eliminar.
     * @return El mismo objeto Libro que fue eliminado.
     */
    @Override
    public Libro deleteEntity(Libro entityDelete) {
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

    public static LibroDAO build() {
        return new LibroDAO();
    }
}
