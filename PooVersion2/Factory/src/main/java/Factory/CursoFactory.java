package Factory;

import Modelos.Curso;
import Modelos.Programa;

public class CursoFactory {
    public static Curso crearCurso(Integer id, String nombre, Programa programa, boolean activo) {
        return new Curso(id, nombre, programa, activo);
    }
}
