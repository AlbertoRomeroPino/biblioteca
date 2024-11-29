package DAO;

public class Test1DAO {

    /*
    Tiene que tener este orden los 4 primero puedes ponerlos en el orden que quieras:
    1. Usuario
    2. Autor
    3. Categoria
    4. Editorial

    5. Libro o revista
    6. Libro o revista

    Aclaracion Publicaciones solo va a ser mas que un puente para tocar en los test. Porque ya se tocan y se prueban en revista y libro

    7. Prestamo


    Tambien para probarlo bien tienes que tener en cuenta que la base de datos este recien creado. Porque si tienes autoincrement
    la 2 vez que lo pruebes se actualizaran las 2 primeras y la que sea ObjetoDelete se pasara al id 4 en vez del 3

    La base de datos se encuentra en ./src/main/java/model/connection/Biblioteca
    */
    public static void main(String[] args) {
        String[] strings = new String[1];
        TestAutor.main(strings);
        TestUsuario.main(strings);
        TestCategoria.main(strings);
        TestEditorial.main(strings);

        TestLibro.main(strings);
        TestRevista.main(strings);

        TestPrestamo.main(strings);

    }
}
