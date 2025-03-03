package DAO;

import Interfaces.IEstudianteDAO;
import Modelos.Estudiante;
import Modelos.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements IEstudianteDAO {
    private static final String INSERT_ESTUDIANTE_SQL = "INSERT INTO estudiante (codigo, programa_id, activo, promedio) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ESTUDIANTE_BY_ID = "SELECT * FROM estudiante WHERE idEstudiante = ?";
    private static final String SELECT_ALL_ESTUDIANTES = "SELECT * FROM estudiante";
    private static final String UPDATE_ESTUDIANTE_SQL = "UPDATE estudiante SET codigo = ?, programa_id = ?, activo = ?, promedio = ? WHERE idEstudiante = ?";
    private static final String DELETE_ESTUDIANTE_SQL = "DELETE FROM estudiante WHERE idEstudiante = ?";

    @Override
    public void addEstudiante(Estudiante estudiante) throws ValidationException {
        validateEstudiante(estudiante);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTUDIANTE_SQL)) {
            preparedStatement.setDouble(1, estudiante.getCodigo());
            preparedStatement.setInt(2, estudiante.getPrograma().getId().intValue());
            preparedStatement.setBoolean(3, estudiante.isActivo());
            preparedStatement.setDouble(4, estudiante.getPromedio());
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
                double codigo = rs.getDouble("codigo");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                double promedio = rs.getDouble("promedio");
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                estudiante = new Estudiante(codigo, programa, activo, promedio);
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
                int id = rs.getInt("idEstudiante");
                double codigo = rs.getDouble("codigo");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                double promedio = rs.getDouble("promedio");
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                estudiantes.add(new Estudiante(codigo, programa, activo, promedio));
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
            preparedStatement.setDouble(1, estudiante.getCodigo());
            preparedStatement.setInt(2, estudiante.getPrograma().getId().intValue());
            preparedStatement.setBoolean(3, estudiante.isActivo());
            preparedStatement.setDouble(4, estudiante.getPromedio());
            preparedStatement.setInt(5, (int) estudiante.getCodigo());
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