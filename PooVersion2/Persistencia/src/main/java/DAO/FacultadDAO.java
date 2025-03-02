package DAO;

import Interfaces.IFacultadDAO;
import Modelos.Facultad;
import Modelos.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDAO implements IFacultadDAO {
    private static final String INSERT_FACULTAD_SQL = "INSERT INTO facultad (nombre, decano_id) VALUES (?, ?)";
    private static final String SELECT_FACULTAD_BY_ID = "SELECT * FROM facultad WHERE idFacultad = ?";
    private static final String SELECT_ALL_FACULTADES = "SELECT * FROM facultad";
    private static final String UPDATE_FACULTAD_SQL = "UPDATE facultad SET nombre = ?, decano_id = ? WHERE idFacultad = ?";
    private static final String DELETE_FACULTAD_SQL = "DELETE FROM facultad WHERE idFacultad = ?";

    @Override
    public void addFacultad(Facultad facultad) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACULTAD_SQL)) {
            preparedStatement.setString(1, facultad.getNombre());
            preparedStatement.setInt(2, (int) facultad.getDecano().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Facultad getFacultadById(int id) {
        Facultad facultad = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACULTAD_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int decanoId = rs.getInt("decano_id");
                // Assuming PersonaDAO and Persona class are implemented
                Persona decano = new PersonaDAO().getPersonaById(decanoId);
                facultad = new Facultad(id, nombre, decano);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facultad;
    }

    @Override
    public List<Facultad> getAllFacultades() {
        List<Facultad> facultades = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FACULTADES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idFacultad");
                String nombre = rs.getString("nombre");
                int decanoId = rs.getInt("decano_id");
                // Assuming PersonaDAO and Persona class are implemented
                Persona decano = new PersonaDAO().getPersonaById(decanoId);
                facultades.add(new Facultad(id, nombre, decano));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facultades;
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FACULTAD_SQL)) {
            preparedStatement.setString(1, facultad.getNombre());
            preparedStatement.setInt(2, (int) facultad.getDecano().getId());
            preparedStatement.setInt(3, (int) facultad.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteFacultad(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FACULTAD_SQL)) {
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