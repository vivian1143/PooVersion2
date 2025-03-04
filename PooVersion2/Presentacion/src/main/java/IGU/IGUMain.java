package IGU;

import javax.swing.*;
import java.awt.*;

public class IGUMain extends JFrame {
    public IGUMain() {
        setTitle("Sistema de Inscripción");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Estudiante", new InscripcionEstudianteIGU());
        tabbedPane.addTab("Profesor", new InscripcionProfesorIGU());
        tabbedPane.addTab("Facultad", new InscripcionFacultadIGU());

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IGUMain().setVisible(true));
    }
}