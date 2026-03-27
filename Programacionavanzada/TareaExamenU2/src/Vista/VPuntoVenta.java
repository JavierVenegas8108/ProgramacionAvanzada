package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VPuntoVenta extends JInternalFrame {
    public JComboBox<String> cbProductos;
    public JTextField txtCantidad, txtTotal;
    public JButton btnAgregar, btnPagar, btnTicket;
    public JTable tablaCarrito;
    public DefaultTableModel modeloCarrito;
    public JLabel lblFotoVenta;

    public VPuntoVenta() {
        super("SISTEMA DE COBRO v2.0", true, true, true, true);
        setSize(1100, 700);
        getContentPane().setBackground(new Color(241, 242, 246));
        setLayout(new BorderLayout(0, 0));

        // HEADER: DASHBOARD DE VENTA
        JPanel pnlNorte = new JPanel(new GridLayout(1, 2));
        pnlNorte.setBackground(new Color(45, 52, 54));
        pnlNorte.setPreferredSize(new Dimension(0, 120));
        pnlNorte.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Controles de entrada
        JPanel pnlInputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 25));
        pnlInputs.setOpaque(false);
        
        JLabel lblArt = new JLabel("ARTÍCULO:");
        lblArt.setForeground(Color.WHITE);
        lblArt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        cbProductos = new JComboBox<>(new String[]{"--- Seleccione un producto ---"});
        cbProductos.setPreferredSize(new Dimension(350, 35));
        
        txtCantidad = new JTextField("1", 4);
        txtCantidad.setPreferredSize(new Dimension(50, 35));
        txtCantidad.setHorizontalAlignment(JTextField.CENTER);
        
        btnAgregar = new JButton("AÑADIR AL CARRITO");
        btnAgregar.setBackground(new Color(0, 184, 148));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregar.setFocusPainted(false);
        
        pnlInputs.add(lblArt);
        pnlInputs.add(cbProductos);
        pnlInputs.add(new JLabel("<html><font color='white'>CANT:</font></html>"));
        pnlInputs.add(txtCantidad);
        pnlInputs.add(btnAgregar);

        // Display del Total
        JPanel pnlTotalContenedor = new JPanel(new BorderLayout());
        pnlTotalContenedor.setOpaque(false);
        
        txtTotal = new JTextField("0.00");
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("Monospaced", Font.BOLD, 55));
        txtTotal.setBackground(Color.BLACK);
        txtTotal.setForeground(new Color(85, 239, 196));
        txtTotal.setBorder(BorderFactory.createLineBorder(new Color(99, 110, 114)));
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);
        
        JLabel lblT = new JLabel("TOTAL A PAGAR  ");
        lblT.setForeground(new Color(178, 190, 195));
        lblT.setHorizontalAlignment(JLabel.RIGHT);

        pnlTotalContenedor.add(lblT, BorderLayout.NORTH);
        pnlTotalContenedor.add(txtTotal, BorderLayout.CENTER);

        pnlNorte.add(pnlInputs);
        pnlNorte.add(pnlTotalContenedor);

        // TABLA: CARRITO
        modeloCarrito = new DefaultTableModel(new String[]{"ID", "PRODUCTO", "CANTIDAD", "SUBTOTAL"}, 0);
        tablaCarrito = new JTable(modeloCarrito);
        tablaCarrito.setRowHeight(40);
        tablaCarrito.setSelectionBackground(new Color(116, 185, 255));
        tablaCarrito.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        JScrollPane sp = new JScrollPane(tablaCarrito);
        sp.setBorder(new EmptyBorder(20, 20, 20, 10));
        sp.getViewport().setBackground(Color.WHITE);

        // PANEL ACCIONES (DERECHA)
        JPanel pnlDerecho = new JPanel();
        pnlDerecho.setLayout(new BoxLayout(pnlDerecho, BoxLayout.Y_AXIS));
        pnlDerecho.setPreferredSize(new Dimension(240, 0));
        pnlDerecho.setBackground(Color.WHITE);
        pnlDerecho.setBorder(new EmptyBorder(20, 10, 20, 20));

        lblFotoVenta = new JLabel("PREVIEW", SwingConstants.CENTER);
        lblFotoVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFotoVenta.setPreferredSize(new Dimension(200, 200));
        lblFotoVenta.setMaximumSize(new Dimension(200, 200));
        lblFotoVenta.setBorder(BorderFactory.createLineBorder(new Color(223, 228, 234), 2));
        lblFotoVenta.setOpaque(true);
        lblFotoVenta.setBackground(new Color(241, 242, 246));
        
        btnPagar = new JButton("FINALIZAR VENTA");
        btnTicket = new JButton("IMPRIMIR TICKET");
        
        estilizarBotonAccion(btnPagar, new Color(45, 52, 54));
        estilizarBotonAccion(btnTicket, new Color(9, 132, 227));

        pnlDerecho.add(lblFotoVenta);
        pnlDerecho.add(Box.createRigidArea(new Dimension(0, 40)));
        pnlDerecho.add(btnPagar);
        pnlDerecho.add(Box.createRigidArea(new Dimension(0, 15)));
        pnlDerecho.add(btnTicket);

        add(pnlNorte, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(pnlDerecho, BorderLayout.EAST);
    }

    private void estilizarBotonAccion(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setMaximumSize(new Dimension(220, 60));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}