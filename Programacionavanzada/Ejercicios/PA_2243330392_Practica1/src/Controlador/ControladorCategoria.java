package Controlador;

import Modelo.Categoria;
import Modelo.Archivotxt;
import Vista.VistaCategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorCategoria implements ActionListener {
    private VistaCategoria vista;
    private Archivotxt dao;
    private ArrayList<Categoria> lista;

    public ControladorCategoria(VistaCategoria vista) {
        this.vista = vista;
        this.dao = new Archivotxt();
        // Al iniciar, cargamos los datos que ya existan en el TXT
        this.lista = dao.cargarArchivo();
        
        this.vista.Bagregar.addActionListener(this);
        this.vista.Beliminar.addActionListener(this);
        this.vista.Bsalir.addActionListener(this);
        
        // Refrescamos la vista inicial con los datos cargados
        actualizarLista();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.Bagregar) {
            Categoria c = new Categoria(vista.Tid.getText(), vista.Tcategoria.getText());
            lista.add(c);
            dao.guardarArchivo(lista); // Persistencia inmediata
            actualizarLista();
            limpiarCampos();
        }

        if (e.getSource() == vista.Beliminar) {
            String idEliminar = vista.Tid.getText();
            lista.removeIf(cat -> cat.getIdcategoria().equals(idEliminar));
            dao.guardarArchivo(lista); // Actualizamos el archivo tras borrar
            actualizarLista();
            limpiarCampos();
        }

        if (e.getSource() == vista.Bsalir) {
            vista.dispose();
        }
    }

    private void actualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Categoria c : lista) {
            sb.append(c.getIdcategoria()).append(" - ").append(c.getCategoria()).append("\n");
        }
        vista.Tareacategoria.setText(sb.toString());
    }

    private void limpiarCampos() {
        vista.Tid.setText("");
        vista.Tcategoria.setText("");
    }
}