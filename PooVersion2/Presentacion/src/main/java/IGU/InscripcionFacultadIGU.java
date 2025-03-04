package IGU;

import javax.swing.*;
import java.awt.*;

public class InscripcionFacultadIGU extends JPanel {
    public InscripcionFacultadIGU() {
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Nombre de la Facultad:"));
        JTextField facultadField = new JTextField();
        add(facultadField);

        add(new JLabel("Decano:"));
        JComboBox<String> decanoBox = new JComboBox<>(new String[]{"Dr. Pérez", "Dra. Gómez", "Dr. Ramírez"});
        add(decanoBox);

        JButton registrarBtn = new JButton("Registrar");
        add(registrarBtn);
    }
}
