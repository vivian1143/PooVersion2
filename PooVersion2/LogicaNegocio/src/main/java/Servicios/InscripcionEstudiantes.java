package Servicios;

import Modelos.Estudiante;
import Documentos.EstudianteArchivoDAO;

public class InscripcionEstudiantes {
    private EstudianteArchivoDAO estudianteArchivoDAO;

    public InscripcionEstudiantes() {
        this.estudianteArchivoDAO = new EstudianteArchivoDAO();
    }

    public void addEstudiante(String nombre, String apellidos, String email, int codigo, boolean activo, double promedio) {
        Estudiante estudiante = new Estudiante(null, nombre, apellidos, email, codigo, null, activo, promedio);
        estudianteArchivoDAO.addEstudiante(estudiante);
    }
}