package DAO;

import Factory.InscripcionFactory;
import Interfaces.IInscripcionDAO;
import Modelos.Inscripcion;
import Modelos.Curso;
import Modelos.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO implements IInscripcionDAO {
    private static final String INSERT_INSCRIPCION_SQL = "INSERT INTO inscripcion (curso_id, año, semestre, estudiante_id) VALUES (?, ?, ?, ?)";
    private static final String SELECT_INSCRIPCION_BY_ID = "SELECT * FROM inscripcion WHERE id = ?";
    private static final String SELECT_ALL_INSCRIPCIONES = "SELECT * FROM inscripcion";
    private static final String UPDATE_INSCRIPCION_SQL = "UPDATE inscripcion SET curso_id = ?, año = ?, semestre = ?, estudiante_id = ? WHERE id = ?";
    private static final String DELETE_INSCRIPCION_SQL = "DELETE FROM inscripcion WHERE id = ?";

    @Override
    public void addInscripcion(Inscripcion inscripcion) throws ValidationException {
        validateInscripcion(inscripcion);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSCRIPCION_SQL)) {
            preparedStatement.setInt(1, inscripcion.getCurso().getId());
            preparedStatement.setInt(2, inscripcion.getAño());
            preparedStatement.setInt(3, inscripcion.getSemestre());
            preparedStatement.setInt(4, inscripcion.getEstudiante().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Inscripcion getInscripcionById(int id) {
        Inscripcion inscripcion = null;
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSCRIPCION_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cursoId = rs.getInt("curso_id");
                int año = rs.getInt("año");
                int semestre = rs.getInt("semestre");
                int estudianteId = rs.getInt("estudiante_id");
                CursoDAO cursoDAO = new CursoDAO();
                Curso curso = cursoDAO.getCursoById(cursoId);

                EstudianteDAO estudianteDAO = new EstudianteDAO();
                Estudiante estudiante = estudianteDAO.getEstudianteById(estudianteId);

                // ✅ Usamos la Factory para crear la inscripción
                inscripcion = InscripcionFactory.crearInscripcion(id, curso, año, semestre, estudiante);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return inscripcion;
    }

    @Override
    public List<Inscripcion> getAllInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSCRIPCIONES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int cursoId = rs.getInt("curso_id");
                int año = rs.getInt("año");
                int semestre = rs.getInt("semestre");
                int estudianteId = rs.getInt("estudiante_id");
                Curso curso = new CursoDAO().getCursoById(cursoId);
                Estudiante estudiante = new EstudianteDAO().getEstudianteById(estudianteId);
                inscripciones.add(new Inscripcion(id, curso, año, semestre, estudiante));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return inscripciones;
    }

    @Override
    public void updateInscripcion(Inscripcion inscripcion) throws ValidationException {
        validateInscripcion(inscripcion);
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSCRIPCION_SQL)) {
            preparedStatement.setInt(1, inscripcion.getCurso().getId());
            preparedStatement.setInt(2, inscripcion.getAño());
            preparedStatement.setInt(3, inscripcion.getSemestre());
            preparedStatement.setInt(4, inscripcion.getEstudiante().getId());
            preparedStatement.setInt(5, inscripcion.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteInscripcion(int id) {
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSCRIPCION_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void validateInscripcion(Inscripcion inscripcion) throws ValidationException {
        if (inscripcion.getCurso() == null || inscripcion.getCurso().getId() == null) {
            throw new ValidationException("El curso no puede estar vacío");
        }
        if (inscripcion.getAño() <= 0) {
            throw new ValidationException("El año debe ser un número positivo");
        }
        if (inscripcion.getSemestre() <= 0) {
            throw new ValidationException("El semestre debe ser un número positivo");
        }
        if (inscripcion.getEstudiante() == null || inscripcion.getEstudiante().getId() == null) {
            throw new ValidationException("El estudiante no puede estar vacío");
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