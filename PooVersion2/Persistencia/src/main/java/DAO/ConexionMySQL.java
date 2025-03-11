package DAO;

import Factory.ConexionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.sql.Statement;

public class ConexionMySQL {

    // Ruta absoluta del script de la BD
    private static final String RUTA_SCRIPT = "/home/daikyri/Documentos/Avanzadas/PooVersion2/PooVersion2/DBScript.sql";

    // Método para obtener una conexión usando `ConexionFactory`
    public static Connection getConnection() throws SQLException {
        return ConexionFactory.getConnection();
    }

    // Método para ejecutar el script de la BD
    public static void ejecutarScriptBD() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Leer todo el contenido del script
            String content = new String(Files.readAllBytes(Paths.get(RUTA_SCRIPT)));

            // Dividir comandos evitando problemas con `;` dentro de strings
            String[] comandos = content.split(";\n"); // Agregar salto de línea para evitar errores

            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    System.out.println("Ejecutando: " + comando);
                    statement.execute(comando);
                }
            }
            System.out.println("✅ Script ejecutado correctamente.");

        } catch (IOException | SQLException e) {
            System.err.println("❌ Error al ejecutar el script de la BD:");
            e.printStackTrace();
        }
    }
}
