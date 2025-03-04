package Interfaces;

import Modelos.Persona;
import java.util.List;

public interface IProfesorArchivoDAO {
    void addProfesor(Profesor profesor);
    Profesor getProfesorById(int id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor);
    void deleteProfesor(double id);
}
