package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VProductos extends JInternalFrame {
    public JTextField txtId, txtNombre, txtPrecioVenta, txtStock;
    public JTextArea txtDescripcion;
    public JComboBox<String> cbCategoria;
    public JButton btnGuardar, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JLabel lblImagen; 
    public JTextArea txtDetallesEspeciales;

    public VProductos() {
        super(".: GESTIÓN DE INVENTARIO :.", true, true, true, true);
        setSize(1150, 700);
        getContentPane().setBackground(new Color(30, 39, 46)); // Deep Dark
        setLayout(new BorderLayout(20, 20));
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); // Quitar borde superior nativo
        setBorder(BorderFactory.createLineBorder(new Color(72, 84, 96), 1));

        // PANEL CENTRAL: TABLA
        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setOpaque(false);
        pnlCentro.setBorder(new EmptyBorder(10, 20, 10, 10));
        
        modelo = new DefaultTableModel(new String[]{"CÓDIGO", "DESCRIPCIÓN", "CATEGORÍA", "EXISTENCIA", "PRECIO"}, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(35);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(72, 84, 96));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setGridColor(new Color(47, 53, 66));
        
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(87, 96, 111)));
        pnlCentro.add(scrollTabla, BorderLayout.CENTER);

        // PANEL DERECHO: FORMULARIO
        JPanel pnlDerecho = new JPanel(new GridBagLayout());
        pnlDerecho.setPreferredSize(new Dimension(350, 0));
        pnlDerecho.setBackground(new Color(47, 53, 66)); // Ebony Clay
        pnlDerecho.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Estilo de inputs
        Font fuenteLabel = new Font("Segoe UI", Font.BOLD, 12);
        Color colorTexto = new Color(210, 218, 226);

        txtId = new JTextField(); txtId.setEditable(false); txtId.setBackground(new Color(30, 39, 46)); txtId.setForeground(Color.GRAY);
        txtNombre = crearInputModerno();
        cbCategoria = new JComboBox<>(new String[]{"Seleccionar...", "Abarrotes", "Bebidas", "Lácteos y Huevo", "Frutas y Verduras", "Carnes y Pescado", "Salchichonería", "Panadería", "Limpieza", "Cuidado Personal", "Snacks", "Mascotas"});
        txtPrecioVenta = crearInputModerno();
        txtStock = crearInputModerno();

        String[] etiquetas = {"ID SISTEMA:", "PRODUCTO:", "GRUPO:", "PRECIO ($):", "STOCK:"};
        Component[] componentes = {txtId, txtNombre, cbCategoria, txtPrecioVenta, txtStock};

        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridy = i; gbc.gridx = 0;
            JLabel lbl = new JLabel(etiquetas[i]);
            lbl.setForeground(colorTexto);
            lbl.setFont(fuenteLabel);
            pnlDerecho.add(lbl, gbc);
            gbc.gridx = 1;
            pnlDerecho.add(componentes[i], gbc);
        }

        btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setBackground(new Color(5, 196, 107)); // Minty Green
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnLimpiar = new JButton("NUEVO / LIMPIAR");
        btnLimpiar.setBackground(new Color(61, 61, 61));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.insets = new Insets(30, 5, 5, 5);
        pnlDerecho.add(btnGuardar, gbc);
        gbc.gridy = 6; gbc.insets = new Insets(5, 5, 5, 5);
        pnlDerecho.add(btnLimpiar, gbc);

        // PANEL INFERIOR: DETALLES
        JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 15));
        pnlInferior.setPreferredSize(new Dimension(0, 220));
        pnlInferior.setBackground(new Color(30, 39, 46));

        lblImagen = new JLabel("SIN IMAGEN", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(180, 180));
        lblImagen.setOpaque(true);
        lblImagen.setBackground(new Color(47, 53, 66));
        lblImagen.setForeground(new Color(128, 142, 155));
        lblImagen.setBorder(BorderFactory.createDashedBorder(new Color(87, 96, 111), 2, 2));

        txtDetallesEspeciales = new JTextArea(7, 55);
        txtDetallesEspeciales.setBackground(new Color(47, 53, 66));
        txtDetallesEspeciales.setForeground(new Color(255, 192, 72)); 
        txtDetallesEspeciales.setEditable(false);
        txtDetallesEspeciales.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtDetallesEspeciales.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(87, 96, 111)), 
            " ESPECIFICACIONES TÉCNICAS ", TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));

        pnlInferior.add(lblImagen);
        pnlInferior.add(new JScrollPane(txtDetallesEspeciales));

        add(pnlCentro, BorderLayout.CENTER);
        add(pnlDerecho, BorderLayout.EAST);
        add(pnlInferior, BorderLayout.SOUTH);
    }

    private JTextField crearInputModerno() {
        JTextField tf = new JTextField();
        tf.setBackground(new Color(30, 39, 46));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(87, 96, 111)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return tf;
    }
}