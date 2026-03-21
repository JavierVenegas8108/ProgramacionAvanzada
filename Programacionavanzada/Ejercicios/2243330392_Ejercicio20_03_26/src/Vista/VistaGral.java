package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaGral extends JFrame {
    private JDesktopPane desktop;
    public JMenuItem itemGestion, itemVenta, itemSalir;

    public VistaGral() {
        setTitle("Sistema de ventas");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.GRAY);
        setContentPane(desktop);

        JMenuBar mb = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        itemGestion = new JMenuItem("Gestión de Inventario");
        itemVenta = new JMenuItem("Punto de Venta");
        itemSalir = new JMenuItem("Salir");

        menuArchivo.add(itemGestion);
        menuArchivo.add(itemVenta);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        mb.add(menuArchivo);
        setJMenuBar(mb);
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }
}