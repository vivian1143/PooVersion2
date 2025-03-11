package InicioMain;

import IGU.InscripcionProfesorIGU;
import IGU.InscripcionEstudianteIGU;
import Factory.ConexionFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConexionFactory.ejecutarScriptBD();

        JFrame frame = new JFrame("Inscripción");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear el panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Agregar el panel de inscripción de profesor
        InscripcionProfesorIGU inscripcionProfesorIGU = new InscripcionProfesorIGU();
        tabbedPane.addTab("Inscripción de Profesor", inscripcionProfesorIGU);

        // Agregar el panel de inscripción de estudiante
        InscripcionEstudianteIGU inscripcionEstudianteIGU = new InscripcionEstudianteIGU();
        tabbedPane.addTab("Inscripción de Estudiante", inscripcionEstudianteIGU);

        // Agregar el panel de pestañas al frame
        frame.add(tabbedPane);

        // Hacer visible el frame
        frame.setVisible(true);
    }
}