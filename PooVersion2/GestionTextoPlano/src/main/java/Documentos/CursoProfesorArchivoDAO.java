package Documentos;

import Modelos.CursoProfesor;
import Interfaces.ICursoProfesorArchivoDAO;

import java.io.*;
import java.util.ArrayList;
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
    public CursoProfesor getCursoProfesorById(double id) {
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
                CursoProfesor cp = new CursoProfesor(Double.parseDouble(data[0]), data[1], data[2]);
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
            if (lista.get(i).getId() == cursoProfesor.getId()) {
                lista.set(i, cursoProfesor);
                break;
            }
        }
        saveAllCursoProfesores(lista);
    }

    @Override
    public void deleteCursoProfesor(double id) {
        List<CursoProfesor> lista = getAllCursoProfesores();
        lista.removeIf(cp -> cp.getId() == id);
        saveAllCursoProfesores(lista);
    }

    private void saveAllCursoProfesores(List<CursoProfesor> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (CursoProfesor cp : lista) {
                writer.write(cp.getId() + "," + cp.getCurso() + "," + cp.getProfesor());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
