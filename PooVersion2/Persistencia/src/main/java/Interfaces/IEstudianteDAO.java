package Interfaces;

import Modelos.Estudiante;
import DAO.ValidationException;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface IEstudianteDAO {
    void addEstudiante(Estudiante estudiante) throws ValidationException, InvalidAttributeValueException;
    Estudiante getEstudianteById(int id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante) throws ValidationException;
    void deleteEstudiante(int id);
}