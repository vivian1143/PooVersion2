package Interfaces;

import Modelos.Curso;
import java.util.List;

public interface ICursoDAO {
    void addCurso(Curso curso);
    Curso getCursoById(int id);
    List<Curso> getAllCursos();
    void updateCurso(Curso curso);
    void deleteCurso(int id);
}
