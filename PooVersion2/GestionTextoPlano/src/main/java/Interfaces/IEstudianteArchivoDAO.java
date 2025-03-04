package Interfaces;

import Modelos.Estudiante;
import java.util.List;

public interface IEstudianteArchivoDAO {
    void addEstudiante(Estudiante estudiante);
    Estudiante getEstudianteById(Integer id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante);
    void deleteEstudiante(Integer id);
}