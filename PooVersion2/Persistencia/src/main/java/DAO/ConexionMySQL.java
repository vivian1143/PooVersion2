package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ConexionMySQL {

    // Ajusta la URL, usuario y contraseña a tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/academia?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    // Ruta absoluta del script de la BD
    private static final String RUTA_SCRIPT = "/home/daikyri/Documentos/Avanzadas/PooVersion2/PooVersion2/DBScript.sql";

    // Método para obtener una conexión
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Registrar el driver (opcional en versiones recientes)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Obtener la conexión
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL no encontrado");
            e.printStackTrace();
        }
        return conn;
    }

    // Método para cerrar la conexión
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión");
                e.printStackTrace();
            }
        }
    }

    // Método para ejecutar el script de la BD
    public static void ejecutarScriptBD() {
        try (Connection connection = getConnection()) {
            // Leer todo el contenido del script
            String content = new String(Files.readAllBytes(Paths.get(RUTA_SCRIPT)));
            // Dividir el contenido por el delimitador de sentencias (usualmente ';')
            String[] comandos = content.split(";");
            Statement statement = connection.createStatement();
            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    System.out.println("Ejecutando: " + comando);
                    statement.execute(comando);
                }
            }
            statement.close();
            System.out.println("Script ejecutado correctamente.");
        } catch (IOException | SQLException e) {
            System.err.println("Error al ejecutar el script de la BD:");
            e.printStackTrace();
        }
    }
}
