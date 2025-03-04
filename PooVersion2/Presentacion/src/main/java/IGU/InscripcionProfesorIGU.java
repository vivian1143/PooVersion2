package IGU;

import Servicios.InscripcionesPersonas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InscripcionProfesorIGU extends JPanel {
    private InscripcionesPersonas inscripcionesPersonas;

    public InscripcionProfesorIGU() {
        inscripcionesPersonas = new InscripcionesPersonas();
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellidos:"));
        JTextField apellidosField = new JTextField();
        add(apellidosField);

        add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Tipo de contrato:"));
        JComboBox<String> contratoBox = new JComboBox<>(new String[]{"Tiempo Completo", "Medio Tiempo", "Catedrático"});
        add(contratoBox);

        JButton inscribirBtn = new JButton("Inscribir");
        add(inscribirBtn);

        inscribirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellidos = apellidosField.getText();
                String email = emailField.getText();
                String tipoContrato = (String) contratoBox.getSelectedItem();

                try {
                    inscripcionesPersonas.addProfesor(nombre, apellidos, email, tipoContrato);
                    JOptionPane.showMessageDialog(null, "Profesor inscrito exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al inscribir profesor: " + ex.getMessage());
                }
            }
        });
    }
}