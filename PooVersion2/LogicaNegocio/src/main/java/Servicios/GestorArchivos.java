package Servicios;

import java.io.FileWriter;
import java.io.IOException;

public class GestorArchivos {
    public static void guardarEnArchivo(String archivo, String contenido) {
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(contenido + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
