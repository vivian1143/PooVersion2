package Interfaces;

import Modelos.Estudiante;
import java.util.List;

public interface IEstudianteDAO {
    void addEstudiante(Estudiante estudiante);
    Estudiante getEstudianteById(int id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante);
    void deleteEstudiante(int id);
}
