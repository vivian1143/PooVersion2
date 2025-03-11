package DAO;

import Factory.EstudianteFactory;
import Interfaces.IEstudianteDAO;
import Modelos.Estudiante;
import Modelos.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements IEstudianteDAO {
    private static final String INSERT_ESTUDIANTE_SQL = "INSERT INTO estudiante (nombre, apellidos, email, codigo, programa_id, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ESTUDIANTE_BY_ID = "SELECT * FROM estudiante WHERE id = ?";
    private static final String SELECT_ALL_ESTUDIANTES = "SELECT * FROM estudiante";
    private static final String UPDATE_ESTUDIANTE_SQL = "UPDATE estudiante SET nombre = ?, apellidos = ?, email = ?, codigo = ?, programa_id = ?, activo = ?, promedio = ? WHERE id = ?";
    private static final String DELETE_ESTUDIANTE_SQL = "DELETE FROM estudiante WHERE id = ?";

    @Override
    public void addEstudiante(Estudiante estudiante) throws ValidationException {
        validateEstudiante(estudiante);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTUDIANTE_SQL)) {
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellidos());
            preparedStatement.setString(3, estudiante.getEmail());
            preparedStatement.setInt(4, estudiante.getCodigo());
            preparedStatement.setInt(5, estudiante.getPrograma().getId());
            preparedStatement.setBoolean(6, estudiante.isActivo());
            preparedStatement.setDouble(7, estudiante.getPromedio());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Estudiante getEstudianteById(int id) {
        Estudiante estudiante = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ESTUDIANTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                int codigo = rs.getInt("codigo");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                double promedio = rs.getDouble("promedio");
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                estudiante = EstudianteFactory.crearEstudiante(id, nombre, apellidos, email, codigo, programa, activo, promedio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return estudiante;
    }

    @Override
    public List<Estudiante> getAllEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ESTUDIANTES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                int codigo = rs.getInt("codigo");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                double promedio = rs.getDouble("promedio");
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                Estudiante estudiante = EstudianteFactory.crearEstudiante(id, nombre, apellidos, email, codigo, programa, activo, promedio);

                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return estudiantes;
    }

    @Override
    public void updateEstudiante(Estudiante estudiante) throws ValidationException {
        validateEstudiante(estudiante);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ESTUDIANTE_SQL)) {
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellidos());
            preparedStatement.setString(3, estudiante.getEmail());
            preparedStatement.setInt(4, estudiante.getCodigo());
            preparedStatement.setInt(5, estudiante.getPrograma().getId());
            preparedStatement.setBoolean(6, estudiante.isActivo());
            preparedStatement.setDouble(7, estudiante.getPromedio());
            preparedStatement.setInt(8, estudiante.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteEstudiante(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ESTUDIANTE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void validateEstudiante(Estudiante estudiante) throws ValidationException {
        if (estudiante.getCodigo() <= 0) {
            throw new ValidationException("El código debe ser un número positivo");
        }
        if (estudiante.getPrograma() == null || estudiante.getPrograma().getId() == null) {
            throw new ValidationException("El programa no puede estar vacío");
        }
        if (estudiante.getPromedio() < 0.0 || estudiante.getPromedio() > 5.0) {
            throw new ValidationException("El promedio debe estar entre 0.0 y 5.0");
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