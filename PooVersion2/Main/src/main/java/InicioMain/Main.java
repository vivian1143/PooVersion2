package InicioMain;

import IGU.InscripcionProfesorIGU;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear el frame
        JFrame frame = new JFrame("Inscripción de Profesor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Agregar el panel de inscripción de profesor
        InscripcionProfesorIGU inscripcionProfesorIGU = new InscripcionProfesorIGU();
        frame.add(inscripcionProfesorIGU);

        // Hacer visible el frame
        frame.setVisible(true);
    }
}