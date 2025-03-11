package Factory;

import Modelos.Curso;
import Modelos.Estudiante;
import Modelos.Inscripcion;

public class InscripcionFactory {
    public static Inscripcion crearInscripcion(int id, Curso curso, int año, int semestre, Estudiante estudiante) {
        return new Inscripcion(id, curso, año, semestre, estudiante);
    }
}
