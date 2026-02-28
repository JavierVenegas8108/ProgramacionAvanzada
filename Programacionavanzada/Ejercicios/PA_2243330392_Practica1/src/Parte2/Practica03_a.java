package Parte2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Practica03_a extends JFrame implements ActionListener {

    // Objetos para el manejo de datos
    ListaInsumos listainsumo;
    ListaCategorias listacategorias;

    // Objetos de los controles
    private JComboBox ComboCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario;

    public static void main(String[] args) {
        new Practica03_a();
    }

    public Practica03_a() {
        super("Administración de Productos");
        
        // Inicialización de listas y datos según las imágenes
        this.inicializarcategorias();
        this.listainsumo = new ListaInsumos();

        // Configuración del Frame
        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null); // Layout absoluto como en la práctica
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        // --- Sección de Categoría ---
        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        // Se llena el combo con el arreglo de categorías
        ComboCategoria = new JComboBox(this.listacategorias.CategoriasArreglo());
        ComboCategoria.setEditable(false);
        ComboCategoria.setBounds(91, 66, 160, 20);
        ComboCategoria.addActionListener(this);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(ComboCategoria);

        // --- Sección de ID ---
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        this.Tid = new JTextField(10);
        this.Tid.setEditable(false); 
        this.Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        // --- Sección de Insumo ---
        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        this.Tinsumo = new JTextField(20);
        this.Tinsumo.setEditable(false);
        this.Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);

        // --- Botones ---
        this.Bagregar = new JButton("Agregar");
        this.Bagregar.setBounds(20, 104, 111, 20);
        this.Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        this.Beliminar = new JButton("Eliminar");
        this.Beliminar.setBounds(153, 104, 111, 20);
        this.Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        this.Bsalir = new JButton("Salir");
        this.Bsalir.setBounds(274, 104, 79, 20);
        this.Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        // --- Área de Texto con Scroll ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);

        this.areaProductos = new JTextArea(10, 40);
        this.areaProductos.setEditable(false);
        scrollPane.setViewportView(areaProductos);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Método para precargar las categorías
    public void inicializarcategorias() {
        this.listacategorias = new ListaCategorias();
        this.listacategorias.agregarCategoria(new Categoria("01", "Materiales"));
        this.listacategorias.agregarCategoria(new Categoria("02", "Mano de Obra"));
        this.listacategorias.agregarCategoria(new Categoria("03", "Maquinaria y Equipo"));
        this.listacategorias.agregarCategoria(new Categoria("04", "Servicios"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Al seleccionar una categoría, habilitamos los campos
        if (e.getSource() == ComboCategoria) {
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
        }

        // Lógica del botón Agregar
        if (e.getSource() == Bagregar) {
            Categoria cat = (Categoria) ComboCategoria.getSelectedItem();
            Insumo nuevoInsumo = new Insumo(Tid.getText(), Tinsumo.getText(), cat.getIdcategoria());
            
            if (listainsumo.agregarInsumo(nuevoInsumo)) {
                areaProductos.setText(listainsumo.toString()); // Actualiza el área de texto
                Tid.setText("");
                Tinsumo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El ID ya existe");
            }
        }

        // Lógica del botón Eliminar
        if (e.getSource() == Beliminar) {
            if (listainsumo.eliminarInsumoPorId(Tid.getText())) {
                areaProductos.setText(listainsumo.toString());
                Tid.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "ID no encontrado");
            }
        }

        if (e.getSource() == Bsalir) {
            System.exit(0);
        }
    }
}