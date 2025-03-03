package Interfaces;

import Modelos.Persona;
import DAO.ValidationException;
import java.util.List;

public interface IPersonaDAO {
    void addPersona(Persona persona) throws ValidationException;
    Persona getPersonaById(int id);
    List<Persona> getAllPersonas();
    void updatePersona(Persona persona) throws ValidationException;
    void deletePersona(int id);
}