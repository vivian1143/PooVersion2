package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.sql.Statement;

public class ConexionFactory {
    private static final String URL_BASE = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "academia";
    private static final String URL = URL_BASE + DATABASE_NAME + "?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "avanzadas";
    private static final String PASSWORD = "15243";
    private static final String RUTA_SCRIPT = "DBScript.sql";

    public static Connection getConnection() throws SQLException {
        System.out.println("Intentando conectar a la BD con URL: " + URL);
        Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        System.out.println("Conexión exitosa.");
        return connection;
    }

    private static void verificarOCrearBaseDeDatos() {
        System.out.println("🔍 Verificando si la base de datos existe...");
        try (Connection connection = DriverManager.getConnection(URL_BASE + "?useSSL=false&serverTimezone=UTC", USUARIO, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            System.out.println("🛠️ Ejecutando: " + sqlCreateDB);
            statement.executeUpdate(sqlCreateDB);
            System.out.println("✅ Base de datos verificada/creada correctamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error al crear/verificar la base de datos:");
            e.printStackTrace();
        }
    }

    public static void ejecutarScriptBD() {
        verificarOCrearBaseDeDatos(); // 🔹 Asegurar que la BD existe antes de conectarse

        System.out.println("🚀 Iniciando la ejecución del script de la BD...");
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("📂 Leyendo el script desde: " + RUTA_SCRIPT);
            String content = new String(Files.readAllBytes(Paths.get(RUTA_SCRIPT)));
            System.out.println("📜 Script leído correctamente.");

            String[] comandos = content.split(";\n");
            System.out.println("📌 Cantidad de comandos encontrados: " + comandos.length);

            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    System.out.println("⏳ Ejecutando: " + comando);
                    try {
                        statement.execute(comando);
                        System.out.println("✅ Comando ejecutado correctamente.");
                    } catch (SQLException ex) {
                        System.err.println("⚠️ Error ejecutando el comando: " + comando);
                        ex.printStackTrace();
                    }
                }
            }
            System.out.println("✅ Script ejecutado completamente.");

        } catch (IOException e) {
            System.err.println("❌ Error al leer el script de la BD:");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar o ejecutar el script de la BD:");
            e.printStackTrace();
        }
    }
}
