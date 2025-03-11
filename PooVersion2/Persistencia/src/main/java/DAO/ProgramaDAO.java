package DAO;

import Interfaces.IProgramaDAO;
import Modelos.Programa;
import Modelos.Facultad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAO implements IProgramaDAO {
    private static final String INSERT_PROGRAMA_SQL = "INSERT INTO programa (nombre, duracion, registro, facultad_id) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PROGRAMA_BY_ID = "SELECT * FROM programa WHERE id = ?";
    private static final String SELECT_ALL_PROGRAMAS = "SELECT * FROM programa";
    private static final String UPDATE_PROGRAMA_SQL = "UPDATE programa SET nombre = ?, duracion = ?, registro = ?, facultad_id = ? WHERE id = ?";
    private static final String DELETE_PROGRAMA_SQL = "DELETE FROM programa WHERE id = ?";

    @Override
    public void addPrograma(Programa programa) throws ValidationException {
        validatePrograma(programa);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROGRAMA_SQL)) {
            preparedStatement.setString(1, programa.getNombre());
            preparedStatement.setDouble(2, programa.getDuracion());
            preparedStatement.setDate(3, new java.sql.Date(programa.getRegistro().getTime()));
            preparedStatement.setInt(4, programa.getFacultad().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Programa getProgramaById(int id) {
        Programa programa = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROGRAMA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                double duracion = rs.getDouble("duracion");
                Date registro = rs.getDate("registro");
                int facultadId = rs.getInt("facultad_id");
                Facultad facultad = new FacultadDAO().getFacultadById(facultadId);
                programa = new Programa(id, nombre, duracion, registro, facultad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return programa;
    }

    @Override
    public List<Programa> getAllProgramas() {
        List<Programa> programas = new ArrayList<>();
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROGRAMAS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double duracion = rs.getDouble("duracion");
                Date registro = rs.getDate("registro");
                int facultadId = rs.getInt("facultad_id");
                Facultad facultad = new FacultadDAO().getFacultadById(facultadId);
                programas.add(new Programa(id, nombre, duracion, registro, facultad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return programas;
    }

    @Override
    public void updatePrograma(Programa programa) throws ValidationException {
        validatePrograma(programa);
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROGRAMA_SQL)) {
            preparedStatement.setString(1, programa.getNombre());
            preparedStatement.setDouble(2, programa.getDuracion());
            preparedStatement.setDate(3, new java.sql.Date(programa.getRegistro().getTime()));
            preparedStatement.setInt(4, programa.getFacultad().getId());
            preparedStatement.setInt(5, programa.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deletePrograma(int id) {
        try (Connection connection = Factory.ConexionFactory.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROGRAMA_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void validatePrograma(Programa programa) throws ValidationException {
        if (programa.getNombre() == null || programa.getNombre().isEmpty()) {
            throw new ValidationException("El nombre no puede estar vacío");
        }
        if (programa.getDuracion() <= 0) {
            throw new ValidationException("La duración debe ser un número positivo");
        }
        if (programa.getFacultad() == null || programa.getFacultad().getId() == null) {
            throw new ValidationException("La facultad no puede estar vacía");
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