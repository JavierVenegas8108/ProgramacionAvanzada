package Parte2;

import javax.swing.*;
import java.awt.*;

public class Practica03_b extends JFrame {

    private JPanel panelFormulario;
    private JLabel labelId, labelCategoria;
    private JTextField Tid, Tcategoria;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea Tareacategoria;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        new Practica03_b();
    }

    public Practica03_b() {
        super("Administracion de Categorias");
        
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        setContentPane(panelFormulario);

        labelId = new JLabel("ID:");
        labelId.setBounds(10, 10, 30, 20);
        panelFormulario.add(labelId);

        labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10, 40, 80, 20);
        panelFormulario.add(labelCategoria);

        Tid = new JTextField();
        Tid.setBounds(100, 10, 150, 20);
        Tid.setEditable(false);
        panelFormulario.add(Tid);

        Tcategoria = new JTextField();
        Tcategoria.setBounds(100, 40, 150, 20);
        Tcategoria.setEditable(false);
        panelFormulario.add(Tcategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(10, 70, 90, 25);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(110, 70, 90, 25);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(210, 70, 90, 25);
        panelFormulario.add(Bsalir);

        Tareacategoria = new JTextArea();
        Tareacategoria.setFont(new Font("Monospaced", Font.PLAIN, 12));
        Tareacategoria.setEditable(false);
        
        scrollPane = new JScrollPane(Tareacategoria);
        scrollPane.setBounds(10, 105, 300, 150);
        panelFormulario.add(scrollPane);

        setSize(335, 305);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}