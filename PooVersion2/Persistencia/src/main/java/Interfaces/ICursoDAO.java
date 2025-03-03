package Interfaces;

import Modelos.Curso;
import DAO.ValidationException;
import java.util.List;

public interface ICursoDAO {
    void addCurso(Curso curso) throws ValidationException;
    Curso getCursoById(int id);
    List<Curso> getAllCursos();
    void updateCurso(Curso curso) throws ValidationException;
    void deleteCurso(int id);
}