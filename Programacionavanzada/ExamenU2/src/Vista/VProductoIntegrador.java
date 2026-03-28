package Vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VProductoIntegrador extends JPanel {

    public JTextField txtNombreProyecto, txtFecha, txtActividad;
    public JTextArea txtObservaciones;

    public VProductoIntegrador() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 15));
        panelForm.setOpaque(false);
        
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Datos del Producto Integrador", 
            TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14)
        ));

        txtNombreProyecto = new JTextField();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaActual = hoy.format(formato);
        txtFecha = new JTextField(fechaActual);
        txtFecha.setEditable(false);
        txtActividad = new JTextField();

        panelForm.add(new JLabel("Nombre del Proyecto:"));
        panelForm.add(txtNombreProyecto);
        panelForm.add(new JLabel("Fecha de Evaluación:"));
        panelForm.add(txtFecha);
        panelForm.add(new JLabel("Actividad / Tema:"));
        panelForm.add(txtActividad);

        JPanel panelObs = new JPanel(new BorderLayout(5, 5));
        panelObs.setOpaque(false);
        panelObs.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Observaciones Académicas", 
            TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14)
        ));

        txtObservaciones = new JTextArea(8, 20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtObservaciones);

        panelObs.add(scroll, BorderLayout.CENTER);

        add(panelForm, BorderLayout.NORTH);
        add(panelObs, BorderLayout.CENTER);
    }
}