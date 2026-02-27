package Controlador;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modelo.Examen;
import Modelo.GestorArchivos;
import Modelo.Mpregunta;
import Vista.VistaExamen;

public class ControladorExamen {
    private VistaExamen vista;
    private Examen modelo;
    private GestorArchivos gestor;

    public ControladorExamen(VistaExamen vista, GestorArchivos gestor) {
        this.vista = vista;
        this.gestor = gestor;
        this.vista.itemAbrir.addActionListener(e -> cargarArchivo());
        this.vista.btnSiguiente.addActionListener(e -> flujoExamen());
    }

    private void cargarArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        if (fc.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
            var lista = gestor.leerCSV(fc.getSelectedFile());
            if (lista != null) {
                modelo = new Examen(lista);
                vista.lblEnunciado.setText("Archivo cargado. Presione Siguiente para iniciar.");
            }
        }
    }

    private void flujoExamen() {
        if (modelo == null) return;
        
        if (vista.menuOpciones.isEnabled()) {
            modelo.barajar();
            vista.menuOpciones.setEnabled(false);
        }

        if (vista.grupoOpciones.getSelection() == null && !vista.lblEnunciado.getText().contains("cargado")) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una opción.");
            return;
        }

        if (!vista.lblEnunciado.getText().contains("cargado")) modelo.siguiente();
        actualizarPantalla();
    }

    private void actualizarPantalla() {
    	Mpregunta p = modelo.obtenerActual();
        if (p != null) {
            vista.lblEnunciado.setText(p.getEnunciado());
            vista.grupoOpciones.clearSelection(); 
            for (int i = 0; i < 4; i++) {
                vista.rbOpciones[i].setText(p.getOpciones()[i]);
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Fin del examen.");
            System.exit(0);
        }
    }
}