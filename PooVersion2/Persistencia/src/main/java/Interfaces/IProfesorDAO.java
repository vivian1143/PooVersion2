package Interfaces;

import Modelos.Profesor;
import DAO.ValidationException;
import java.util.List;

public interface IProfesorDAO {
    void addProfesor(Profesor profesor) throws ValidationException;
    Profesor getProfesorById(int id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor) throws ValidationException;
    void deleteProfesor(int id);
}