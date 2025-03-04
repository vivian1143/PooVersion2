package Interfaces;

import Modelos.Persona;
import java.util.List;

public interface IPersonaArchivoDAO {
    void addPersona(Persona persona);
    Persona getPersonaById(Integer id);
    List<Persona> getAllPersonas();
    void updatePersona(Persona persona);
    void deletePersona(Integer id);
}