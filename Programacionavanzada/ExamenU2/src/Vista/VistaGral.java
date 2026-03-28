package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaGral extends JFrame {

    // Componentes de Control (Norte)
    public JComboBox<String> cbAsignatura, cbProfesor, cbGrupo;
    public JButton btnCargar, btnNuevo, btnGuardar, btnEliminar, btnSeleccionarCarpeta;
    public JPanel indicadorEstatus;

    // Contenedor de Pestañas
    public JTabbedPane tabsCentrales;

    // Instancias de las sub-vistas (Pestañas)
    public VProductoIntegrador panelProducto;
    public VRubrica panelRubrica;
    public VCotejo panelCotejo;

    public VistaGral() {
        // Aplicar Look & Feel Nimbus para mayor profesionalismo
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si falla, usa el default
        }

        setTitle("SAE-AE: Sistema Automatizado de Evaluación de Atributos de Egreso");
        setSize(1150, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        // --- PANEL NORTE: Selectores y Botones ---
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.setBorder(new EmptyBorder(10, 15, 10, 15));
        panelNorte.setBackground(new Color(25, 50, 100)); // Azul Marino

        // Izquierda: Selectores
        JPanel panelSelectores = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelSelectores.setOpaque(false);

        cbAsignatura = new JComboBox<>();
        cbAsignatura.setPreferredSize(new Dimension(200, 25));
        cbProfesor = new JComboBox<>();
        cbProfesor.setPreferredSize(new Dimension(200, 25));
        cbGrupo = new JComboBox<>();
        cbGrupo.setPreferredSize(new Dimension(80, 25));

        panelSelectores.add(crearEtiquetaBlanca("Asignatura:"));
        panelSelectores.add(cbAsignatura);
        panelSelectores.add(crearEtiquetaBlanca("Profesor:"));
        panelSelectores.add(cbProfesor);
        panelSelectores.add(crearEtiquetaBlanca("Grupo:"));
        panelSelectores.add(cbGrupo);

        // Derecha: Botones de Acción
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelAcciones.setOpaque(false);

        btnCargar = new JButton("Consultar");
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnSeleccionarCarpeta = new JButton("📁");
        btnSeleccionarCarpeta.setToolTipText("Seleccionar carpeta de archivos");

        // Semáforo de Estatus
        indicadorEstatus = new JPanel();
        indicadorEstatus.setPreferredSize(new Dimension(22, 22));
        indicadorEstatus.setBackground(Color.RED); 
        indicadorEstatus.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        panelAcciones.add(btnCargar);
        panelAcciones.add(btnNuevo);
        panelAcciones.add(btnGuardar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnSeleccionarCarpeta);
        panelAcciones.add(new JLabel(" ")); // Espaciador
        panelAcciones.add(indicadorEstatus);

        panelNorte.add(panelSelectores, BorderLayout.WEST);
        panelNorte.add(panelAcciones, BorderLayout.EAST);

        // --- PANEL CENTRAL: Pestañas ---
        tabsCentrales = new JTabbedPane();
        
        // Inicialización de las clases hijas
        panelProducto = new VProductoIntegrador();
        panelRubrica = new VRubrica();
        panelCotejo = new VCotejo();

        tabsCentrales.addTab("1. Producto Integrador", panelProducto);
        tabsCentrales.addTab("2. Rúbrica de Evaluación", panelRubrica);
        tabsCentrales.addTab("3. Lista de Cotejo", panelCotejo);

        // --- AGREGAR AL FRAME ---
        add(panelNorte, BorderLayout.NORTH);
        add(tabsCentrales, BorderLayout.CENTER);
    }

    // Método de apoyo para etiquetas estéticas
    private JLabel crearEtiquetaBlanca(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        return label;
    }

    // Método para que el Controlador cambie el semáforo (Requisito PDF)
    public void setEstatus(Color color) {
        indicadorEstatus.setBackground(color);
        indicadorEstatus.repaint();
    }
}