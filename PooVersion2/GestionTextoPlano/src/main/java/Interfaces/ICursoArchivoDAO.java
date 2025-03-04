// ICursoArchivoDAO.java
package Interfaces;

import Modelos.Curso;
import java.util.List;

public interface ICursoArchivoDAO {
    void addCurso(Curso curso);
    Curso getCursoById(Integer id);
    List<Curso> getAllCursos();
    void updateCurso(Curso curso);
    void deleteCurso(Integer id);
}