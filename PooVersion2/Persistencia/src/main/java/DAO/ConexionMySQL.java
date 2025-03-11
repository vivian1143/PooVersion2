package DAO;

import Factory.ConexionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionMySQL {

    public static Connection getConnection() throws SQLException {
        return ConexionFactory.getConnection();
    }
}