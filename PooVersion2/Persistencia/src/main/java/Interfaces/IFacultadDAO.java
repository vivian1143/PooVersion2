package Interfaces;

import Modelos.Facultad;
import DAO.ValidationException;
import java.util.List;

public interface IFacultadDAO {
    void addFacultad(Facultad facultad) throws ValidationException;
    Facultad getFacultadById(int id);
    List<Facultad> getAllFacultades();
    void updateFacultad(Facultad facultad) throws ValidationException;
    void deleteFacultad(int id);
}