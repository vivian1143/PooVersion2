package Interfaces;

import Modelos.Persona;
import java.util.List;

public interface IPersonaArchivoDAO {
    void addPersona(Persona persona);
    Persona getPersonaById(double id);
    List<Persona> getAllPersonas();
    void updatePersona(Persona persona);
    void deletePersona(double id);
}
