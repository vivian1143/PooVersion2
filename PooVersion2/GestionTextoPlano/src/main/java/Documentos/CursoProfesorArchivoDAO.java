package Documentos;

import Modelos.CursoProfesor;
import Modelos.Profesor;
import Modelos.Curso;
import Modelos.Programa;
import Interfaces.ICursoProfesorArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CursoProfesorArchivoDAO implements ICursoProfesorArchivoDAO {
    private static final String FILE_PATH = "curso_profesor.txt";

    @Override
    public void addCursoProfesor(CursoProfesor cursoProfesor) {
        List<CursoProfesor> lista = getAllCursoProfesores();
        lista.add(cursoProfesor);
        saveAllCursoProfesores(lista);
    }

    @Override
    public CursoProfesor getCursoProfesorById(Integer id) {
        List<CursoProfesor> lista = getAllCursoProfesores();
        for (CursoProfesor cp : lista) {
            if (cp.getId() == id) {
                return cp;
            }
        }
        return null;
    }

    @Override
    public List<CursoProfesor> getAllCursoProfesores() {
        List<CursoProfesor> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Integer profesorId = Integer.parseInt(data[0]);
                String profesorNombre = data[1];
                String profesorApellidos = data[2];
                String profesorEmail = data[3];
                String tipoContrato = data[4];
                Profesor profesor = new Profesor(profesorId, profesorNombre, profesorApellidos, profesorEmail, tipoContrato);

                Integer cursoId = Integer.parseInt(data[5]);
                String cursoNombre = data[6];
                Integer programaId = Integer.parseInt(data[7]);
                String programaNombre = data[8];
                double programaDuracion = Double.parseDouble(data[9]);
                Date programaRegistro = new Date(Long.parseLong(data[10]));
                Programa programa = new Programa(programaId, programaNombre, programaDuracion, programaRegistro, null);
                boolean cursoActivo = Boolean.parseBoolean(data[11]);
                Curso curso = new Curso(cursoId, cursoNombre, programa, cursoActivo);

                Integer cursoProfesorId = Integer.parseInt(data[12]);
                int año = Integer.parseInt(data[13]);
                int semestre = Integer.parseInt(data[14]);
                CursoProfesor cp = new CursoProfesor(cursoProfesorId, profesor, año, semestre, curso);
                lista.add(cp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateCursoProfesor(CursoProfesor cursoProfesor) {
        List<CursoProfesor> lista = getAllCursoProfesores();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == (cursoProfesor.getId())) {
                lista.set(i, cursoProfesor);
                break;
            }
        }
        saveAllCursoProfesores(lista);
    }

    @Override
    public void deleteCursoProfesor(Integer id) {
        List<CursoProfesor> lista = getAllCursoProfesores();
        lista.removeIf(cp -> cp.getId() == (id));
        saveAllCursoProfesores(lista);
    }

    private void saveAllCursoProfesores(List<CursoProfesor> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (CursoProfesor cp : lista) {
                writer.write(cp.getId() + "," + cp.getProfesor().getId() + "," + cp.getProfesor().getNombre() + "," +
                        cp.getProfesor().getApellidos() + "," + cp.getProfesor().getEmail() + "," + cp.getProfesor().getTipoContrato() + "," +
                        cp.getCurso().getId() + "," + cp.getCurso().getNombre() + "," + cp.getCurso().getPrograma().getId() + "," +
                        cp.getCurso().getPrograma().getNombre() + "," + cp.getCurso().getPrograma().getDuracion() + "," +
                        cp.getCurso().getPrograma().getRegistro().getTime() + "," + cp.getCurso().isActivo() + "," +
                        cp.getAño() + "," + cp.getSemestre());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}