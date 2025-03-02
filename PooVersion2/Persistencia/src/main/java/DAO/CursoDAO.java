package DAO;

import Interfaces.ICursoDAO;
import Modelos.Curso;
import Modelos.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements ICursoDAO {
    private static final String INSERT_CURSO_SQL = "INSERT INTO curso (nombre, programa_id, activo) VALUES (?, ?, ?)";
    private static final String SELECT_CURSO_BY_ID = "SELECT * FROM curso WHERE idCurso = ?";
    private static final String SELECT_ALL_CURSOS = "SELECT * FROM curso";
    private static final String UPDATE_CURSO_SQL = "UPDATE curso SET nombre = ?, programa_id = ?, activo = ? WHERE idCurso = ?";
    private static final String DELETE_CURSO_SQL = "DELETE FROM curso WHERE idCurso = ?";

    @Override
    public void addCurso(Curso curso) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CURSO_SQL)) {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setInt(2, curso.getPrograma().getId().intValue());
            preparedStatement.setBoolean(3, curso.isActivo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Curso getCursoById(int id) {
        Curso curso = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURSO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                // Assuming ProgramaDAO and Programa class are implemented
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                curso = new Curso(id, nombre, programa, activo);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return curso;
    }

    @Override
    public List<Curso> getAllCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURSOS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idCurso");
                String nombre = rs.getString("nombre");
                int programaId = rs.getInt("programa_id");
                boolean activo = rs.getBoolean("activo");
                // Assuming ProgramaDAO and Programa class are implemented
                Programa programa = new ProgramaDAO().getProgramaById(programaId);
                cursos.add(new Curso(id, nombre, programa, activo));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cursos;
    }

    @Override
    public void updateCurso(Curso curso) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CURSO_SQL)) {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setInt(2, curso.getPrograma().getId().intValue());
            preparedStatement.setBoolean(3, curso.isActivo());
            preparedStatement.setInt(4, curso.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteCurso(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CURSO_SQL)) {
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