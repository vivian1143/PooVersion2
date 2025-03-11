package DAO;

import Validaciones.Validaciones;
import Factory.ProfesorFactory;
import Interfaces.IProfesorDAO;
import Modelos.Profesor;

import javax.management.InvalidAttributeValueException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements IProfesorDAO {
    private static final String INSERT_PROFESOR_SQL = "INSERT INTO persona (nombre, apellidos, email, tipoContrato) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PROFESOR_BY_ID = "SELECT * FROM persona WHERE id = ?";
    private static final String SELECT_ALL_PROFESORES = "SELECT * FROM persona WHERE tipoContrato IS NOT NULL";
    private static final String UPDATE_PROFESOR_SQL = "UPDATE persona SET nombre = ?, apellidos = ?, email = ?, tipoContrato = ? WHERE id = ?";
    private static final String DELETE_PROFESOR_SQL = "DELETE FROM persona WHERE id = ?";

    @Override
    public void addProfesor(Profesor profesor) throws ValidationException, InvalidAttributeValueException {
        Validaciones.validarProfesor(profesor);
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFESOR_SQL)) {
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getApellidos());
            preparedStatement.setString(3, profesor.getEmail());
            preparedStatement.setString(4, profesor.getTipoContrato());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Profesor getProfesorById(int id) {
        Profesor profesor = null;
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFESOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String tipoContrato = rs.getString("tipoContrato");
                profesor = ProfesorFactory.crearProfesor(id, nombre, apellidos, email, tipoContrato);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return profesor;
    }

    @Override
    public List<Profesor> getAllProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROFESORES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String tipoContrato = rs.getString("tipoContrato");
                Profesor profesor = ProfesorFactory.crearProfesor(id, nombre, apellidos, email, tipoContrato);
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return profesores;
    }

    @Override
    public void updateProfesor(Profesor profesor) throws ValidationException {
        validateProfesor(profesor);
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFESOR_SQL)) {
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getApellidos());
            preparedStatement.setString(3, profesor.getEmail());
            preparedStatement.setString(4, profesor.getTipoContrato());
            preparedStatement.setInt(5, profesor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteProfesor(int id) {
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROFESOR_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void validateProfesor(Profesor profesor) throws ValidationException {
        if (profesor.getNombre() == null || profesor.getNombre().isEmpty()) {
            throw new ValidationException("El nombre no puede estar vacío");
        }
        if (profesor.getApellidos() == null || profesor.getApellidos().isEmpty()) {
            throw new ValidationException("Los apellidos no pueden estar vacíos");
        }
        if (profesor.getEmail() == null || profesor.getEmail().isEmpty()) {
            throw new ValidationException("El email no puede estar vacío");
        }
        if (profesor.getTipoContrato() == null || profesor.getTipoContrato().isEmpty()) {
            throw new ValidationException("El tipo de contrato no puede estar vacío");
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