package Interfaces;

import Modelos.Profesor;
import java.util.List;

public interface IProfesorDAO {
    void addProfesor(Profesor profesor);
    Profesor getProfesorById(int id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor);
    void deleteProfesor(int id);
}