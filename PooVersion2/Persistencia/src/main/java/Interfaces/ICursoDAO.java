package Interfaces;

import Modelos.Curso;
import DAO.ValidationException;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface ICursoDAO {
    void addCurso(Curso curso) throws ValidationException, InvalidAttributeValueException;
    Curso getCursoById(int id);
    List<Curso> getAllCursos();
    void updateCurso(Curso curso) throws ValidationException;
    void deleteCurso(int id);
}