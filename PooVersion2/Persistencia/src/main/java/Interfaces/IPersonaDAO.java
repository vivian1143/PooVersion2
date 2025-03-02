package Interfaces;

import Modelos.Persona;
import java.util.List;

public interface IPersonaDAO {
    void addPersona(Persona persona);
    Persona getPersonaById(int id);
    List<Persona> getAllPersonas();
    void updatePersona(Persona persona);
    void deletePersona(int id);
}
