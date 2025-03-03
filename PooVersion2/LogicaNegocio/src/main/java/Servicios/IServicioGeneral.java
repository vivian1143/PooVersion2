package Servicios;

import Modelos.*;

import java.util.List;

public interface IServicioGeneral {
    void addEstudiante(Estudiante estudiante);
    Estudiante getEstudianteById(int id);
    List<Estudiante> getAllEstudiantes();
    void updateEstudiante(Estudiante estudiante);
    void deleteEstudiante(int id);
    void saveEstudianteToFile(Estudiante estudiante, String filePath);

    void addProfesor(Profesor profesor);
    Profesor getProfesorById(int id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor);
    void deleteProfesor(int id);
    void saveProfesorToFile(Profesor profesor, String filePath);

    void addFacultad(Facultad facultad);
    Facultad getFacultadById(int id);
    List<Facultad> getAllFacultades();
    void updateFacultad(Facultad facultad);
    void deleteFacultad(int id);
    void saveFacultadToFile(Facultad facultad, String filePath);

    void addPrograma(Programa programa);
    Programa getProgramaById(int id);
    List<Programa> getAllProgramas();
    void updatePrograma(Programa programa);
    void deletePrograma(int id);
    void saveProgramaToFile(Programa programa, String filePath);

    void addCurso(Curso curso);
    Curso getCursoById(int id);
    List<Curso> getAllCursos();
    void updateCurso(Curso curso);
    void deleteCurso(int id);
    void saveCursoToFile(Curso curso, String filePath);

    void addInscripcion(Inscripcion inscripcion);
    Inscripcion getInscripcionById(int id);
    List<Inscripcion> getAllInscripciones();
    void updateInscripcion(Inscripcion inscripcion);
    void deleteInscripcion(int id);
    void saveInscripcionToFile(Inscripcion inscripcion, String filePath);
}