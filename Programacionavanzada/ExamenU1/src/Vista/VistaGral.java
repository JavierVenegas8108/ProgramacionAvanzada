package Vista;
import javax.swing.*;
import java.awt.*;

public class VistaGral extends JFrame {
    private JDesktopPane desktop;
    private JMenuItem itemGestion, itemVenta;

    public VistaGral() {
        setTitle("Sistema Profesional MDI");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.GRAY);
        setContentPane(desktop);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemGestion = new JMenuItem("Gestion Inventario");
        itemVenta = new JMenuItem("Punto de Venta");
        JMenuItem itemSalir = new JMenuItem("Salir");

        itemSalir.addActionListener(e -> System.exit(0));
        menu.add(itemGestion); menu.add(itemVenta); menu.addSeparator(); menu.add(itemSalir);
        bar.add(menu); setJMenuBar(bar);
    }

    public JDesktopPane getDesktop() { return desktop; }
    public JMenuItem getItemGestion() { return itemGestion; }
    public JMenuItem getItemVenta() { return itemVenta; }
}