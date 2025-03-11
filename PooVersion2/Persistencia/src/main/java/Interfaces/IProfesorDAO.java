package Interfaces;

import Modelos.Profesor;
import DAO.ValidationException;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface IProfesorDAO {
    void addProfesor(Profesor profesor) throws ValidationException, InvalidAttributeValueException;
    Profesor getProfesorById(int id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor) throws ValidationException;
    void deleteProfesor(int id);
}