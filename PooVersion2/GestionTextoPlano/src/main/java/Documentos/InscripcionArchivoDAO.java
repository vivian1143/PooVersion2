package Documentos;

import Modelos.Inscripcion;
import Modelos.Estudiante;
import Modelos.Curso;
import Modelos.Programa;
import Interfaces.IInscripcionArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscripcionArchivoDAO implements IInscripcionArchivoDAO {
    private static final String FILE_PATH = "inscripciones.txt";

    @Override
    public void addInscripcion(Inscripcion inscripcion) {
        List<Inscripcion> lista = getAllInscripciones();
        lista.add(inscripcion);
        saveAllInscripciones(lista);
    }

    @Override
    public Inscripcion getInscripcionById(Integer id) {
        for (Inscripcion i : getAllInscripciones()) {
            if (i.getId() == (id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Inscripcion> getAllInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Integer cursoId = Integer.parseInt(data[1]);
                String cursoNombre = data[2];
                Integer programaId = Integer.parseInt(data[3]);
                String programaNombre = data[4];
                double programaDuracion = Double.parseDouble(data[5]);
                Date programaRegistro = new Date(Long.parseLong(data[6]));
                Programa programa = new Programa(programaId, programaNombre, programaDuracion, programaRegistro, null);
                boolean cursoActivo = Boolean.parseBoolean(data[7]);
                Curso curso = new Curso(cursoId, cursoNombre, programa, cursoActivo);

                Integer estudianteId = Integer.parseInt(data[8]);
                String estudianteNombre = data[9];
                String estudianteApellidos = data[10];
                String estudianteEmail = data[11];
                int estudianteCodigo = Integer.parseInt(data[12]);
                boolean estudianteActivo = Boolean.parseBoolean(data[13]);
                double estudiantePromedio = Double.parseDouble(data[14]);
                Estudiante estudiante = new Estudiante(estudianteId, estudianteNombre, estudianteApellidos, estudianteEmail, estudianteCodigo, programa, estudianteActivo, estudiantePromedio);

                Integer inscripcionId = Integer.parseInt(data[0]);
                int año = Integer.parseInt(data[15]);
                int semestre = Integer.parseInt(data[16]);
                Inscripcion inscripcion = new Inscripcion(inscripcionId, curso, año, semestre, estudiante);
                lista.add(inscripcion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateInscripcion(Inscripcion inscripcion) {
        List<Inscripcion> lista = getAllInscripciones();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == (inscripcion.getId())) {
                lista.set(i, inscripcion);
                break;
            }
        }
        saveAllInscripciones(lista);
    }

    @Override
    public void deleteInscripcion(Integer id) {
        List<Inscripcion> lista = getAllInscripciones();
        lista.removeIf(i -> i.getId() == (id));
        saveAllInscripciones(lista);
    }

    private void saveAllInscripciones(List<Inscripcion> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Inscripcion i : lista) {
                writer.write(i.getId() + "," + i.getCurso().getId() + "," + i.getCurso().getNombre() + "," +
                        i.getCurso().getPrograma().getId() + "," + i.getCurso().getPrograma().getNombre() + "," +
                        i.getCurso().getPrograma().getDuracion() + "," + i.getCurso().getPrograma().getRegistro().getTime() + "," +
                        i.getCurso().isActivo() + "," + i.getEstudiante().getId() + "," + i.getEstudiante().getNombre() + "," +
                        i.getEstudiante().getApellidos() + "," + i.getEstudiante().getEmail() + "," + i.getEstudiante().getCodigo() + "," +
                        i.getEstudiante().isActivo() + "," + i.getEstudiante().getPromedio() + "," + i.getAño() + "," + i.getSemestre());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}