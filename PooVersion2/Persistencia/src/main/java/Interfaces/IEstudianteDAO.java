package Interfaces;

import Modelos.Estudiante;
import DAO.ValidationException;
import java.util.List;

public interface IEstudianteDAO {
    void addEstudiante(Estudiante estudiante) throws ValidationException;
    Estudiante getEstudianteById(int id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante) throws ValidationException;
    void deleteEstudiante(int id);
}