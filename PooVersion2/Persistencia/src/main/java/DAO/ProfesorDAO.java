package DAO;

import Interfaces.IProfesorDAO;
import Modelos.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements IProfesorDAO {
    private static final String INSERT_PROFESOR_SQL = "INSERT INTO profesor (tipoContrato) VALUES (?)";
    private static final String SELECT_PROFESOR_BY_ID = "SELECT * FROM profesor WHERE idProfesor = ?";
    private static final String SELECT_ALL_PROFESORES = "SELECT * FROM profesor";
    private static final String UPDATE_PROFESOR_SQL = "UPDATE profesor SET tipoContrato = ? WHERE idProfesor = ?";
    private static final String DELETE_PROFESOR_SQL = "DELETE FROM profesor WHERE idProfesor = ?";

    @Override
    public void addProfesor(Profesor profesor) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFESOR_SQL)) {
            preparedStatement.setString(1, profesor.getTipoContrato());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Profesor getProfesorById(int id) {
        Profesor profesor = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFESOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String tipoContrato = rs.getString("tipoContrato");
                profesor = new Profesor(tipoContrato);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return profesor;
    }

    @Override
    public List<Profesor> getAllProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROFESORES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tipoContrato = rs.getString("tipoContrato");
                profesores.add(new Profesor(tipoContrato));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return profesores;
    }

    @Override
    public void updateProfesor(Profesor profesor) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFESOR_SQL)) {
            preparedStatement.setString(1, profesor.getTipoContrato());
            preparedStatement.setInt(2, profesor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteProfesor(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROFESOR_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}