package Modelo;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CargadorImagenes {
    
    public static void cargarFoto(JLabel label, String rutaDesdeCSV) {
        if (rutaDesdeCSV == null || rutaDesdeCSV.isEmpty()) {
            label.setIcon(null);
            label.setText("Sin imagen");
            return;
        }

        try {
            File archivo = new File(rutaDesdeCSV);
            
            if (archivo.exists()) {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
                label.setText(""); 
            } else {
                label.setIcon(null);
                label.setText("No encontrada");
                System.out.println("No se encontró el archivo en: " + archivo.getAbsolutePath());
            }
        } catch (Exception e) {
            label.setText("Error");
        }
    }
}