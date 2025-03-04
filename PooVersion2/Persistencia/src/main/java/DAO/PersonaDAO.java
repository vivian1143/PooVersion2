package DAO;

import Interfaces.IPersonaDAO;
import Modelos.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PersonaDAO implements IPersonaDAO {
    private static final String INSERT_PERSONA_SQL = "INSERT INTO persona (nombre, apellidos, email) VALUES (?, ?, ?)";
    private static final String SELECT_PERSONA_BY_ID = "SELECT * FROM persona WHERE id = ?";
    private static final String SELECT_ALL_PERSONAS = "SELECT * FROM persona";
    private static final String UPDATE_PERSONA_SQL = "UPDATE persona SET nombre = ?, apellidos = ?, email = ? WHERE id = ?";
    private static final String DELETE_PERSONA_SQL = "DELETE FROM persona WHERE id = ?";

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public void addPersona(Persona persona) throws ValidationException {
        validatePersona(persona);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONA_SQL)) {
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellidos());
            preparedStatement.setString(3, persona.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Persona getPersonaById(int id) {
        Persona persona = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                persona = new Persona(id, nombre, apellidos, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return persona;
    }

    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONAS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                personas.add(new Persona(id, nombre, apellidos, email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return personas;
    }

    @Override
    public void updatePersona(Persona persona) throws ValidationException {
        validatePersona(persona);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSONA_SQL)) {
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellidos());
            preparedStatement.setString(3, persona.getEmail());
            preparedStatement.setInt(4, persona.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deletePersona(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSONA_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void validatePersona(Persona persona) throws ValidationException {
        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            throw new ValidationException("El nombre no puede estar vacío");
        }
        if (persona.getApellidos() == null || persona.getApellidos().isEmpty()) {
            throw new ValidationException("Los apellidos no pueden estar vacíos");
        }
        if (persona.getEmail() == null || !EMAIL_PATTERN.matcher(persona.getEmail()).matches()) {
            throw new ValidationException("El correo electrónico no es válido");
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