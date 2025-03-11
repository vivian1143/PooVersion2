package Factory;

import Modelos.Estudiante;
import Modelos.Programa;

public class EstudianteFactory {
    public static Estudiante crearEstudiante(Integer id, String nombre, String apellidos, String email, int codigo, Programa programa, boolean activo, double promedio) {
        return new Estudiante(id, nombre, apellidos, email, codigo, programa, activo, promedio);
    }
}

