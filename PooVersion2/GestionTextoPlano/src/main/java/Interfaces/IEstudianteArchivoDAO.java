package Interfaces;

import Modelos.Estudiante;
import java.util.List;

public interface IEstudianteArchivoDAO {
    void addEstudiante(Estudiante estudiante);
    Estudiante getEstudianteById(double id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante);
    void deleteEstudiante(double id);
}
