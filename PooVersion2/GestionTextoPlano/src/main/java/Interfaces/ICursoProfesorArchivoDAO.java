package Interfaces;

import Modelos.CursoProfesor;
import java.util.List;

public interface ICursoProfesorArchivoDAO {
    void addCursoProfesor(CursoProfesor cursoProfesor);
    CursoProfesor getCursoProfesorById(Integer id);
    List<CursoProfesor> getAllCursoProfesores();
    void updateCursoProfesor(CursoProfesor cursoProfesor);
    void deleteCursoProfesor(Integer id);
}