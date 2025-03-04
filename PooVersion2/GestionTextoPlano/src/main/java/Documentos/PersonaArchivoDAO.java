package Documentos;

import Modelos.Persona;
import Interfaces.IPersonaArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaArchivoDAO implements IPersonaArchivoDAO {
    private static final String FILE_PATH = "personas.txt";

    @Override
    public void addPersona(Persona persona) {
        List<Persona> personas = getAllPersonas();
        personas.add(persona);
        saveAllPersonas(personas);
    }

    @Override
    public Persona getPersonaById(Integer id) {
        List<Persona> personas = getAllPersonas();
        for (Persona persona : personas) {
            if (persona.getId().equals(id)) {
                return persona;
            }
        }
        return null;
    }

    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Persona persona = new Persona(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3]
                );
                personas.add(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public void updatePersona(Persona persona) {
        List<Persona> personas = getAllPersonas();
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId().equals(persona.getId())) {
                personas.set(i, persona);
                break;
            }
        }
        saveAllPersonas(personas);
    }

    @Override
    public void deletePersona(Integer id) {
        List<Persona> personas = getAllPersonas();
        personas.removeIf(persona -> persona.getId().equals(id));
        saveAllPersonas(personas);
    }

    private void saveAllPersonas(List<Persona> personas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Persona persona : personas) {
                writer.write(persona.getId() + "," + persona.getNombre() + "," + persona.getApellidos() + "," + persona.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}