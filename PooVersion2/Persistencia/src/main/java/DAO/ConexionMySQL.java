package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    // Ajusta la URL, usuario y contraseña a tu entorno
    private static final String URL = "jdbc:mysql://localhost:3306/academia?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    // Método para obtener una conexión
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Registrar el driver (opcional en versiones más recientes de JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Obtener la conexión
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL no encontrado");
            e.printStackTrace();
        }
        return conn;
    }

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
}
