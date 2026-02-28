package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Libreria.Archivotxt;
import Modelo.actCategoria;
import Vista.VistaCategoriaB;

public class ControladorCategoriaB implements ActionListener {
    private VistaCategoriaB vista;
    private ArrayList<actCategoria> listaCategorias;
    private Archivotxt manejadorArchivo;
    private final String NOMBRE_ARCHIVO = "categorias.txt";

    public ControladorCategoriaB(VistaCategoriaB vista) {
        this.vista = vista;
        this.listaCategorias = new ArrayList<>();
        this.manejadorArchivo = new Archivotxt(NOMBRE_ARCHIVO);
        
        this.vista.Bagregar.addActionListener(this);
        this.vista.Beliminar.addActionListener(this);
        this.vista.Bsalir.addActionListener(this);
        
        cargarDatosDesdeArchivo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.Bagregar) {
        	actCategoria nueva = new actCategoria(vista.Tid.getText(), vista.Tcategoria.getText());
            listaCategorias.add(nueva);
            guardarDatosEnArchivo();
            actualizarTextArea();
            limpiarCampos();
        }

        if (e.getSource() == vista.Beliminar) {
            String id = vista.Tid.getText();
            listaCategorias.removeIf(cat -> cat.getIdcategoria().equals(id));
            guardarDatosEnArchivo();
            actualizarTextArea();
            limpiarCampos();
        }

        if (e.getSource() == vista.Bsalir) {
            vista.dispose();
        }
    }

    private void limpiarCampos() {
        vista.Tid.setText("");
        vista.Tcategoria.setText("");
    }

    private void actualizarTextArea() {
        StringBuilder sb = new StringBuilder();
        for (actCategoria c : listaCategorias) {
            sb.append(c.toString()).append("\n");
        }
        vista.Tareacategoria.setText(sb.toString());
    }

    private void guardarDatosEnArchivo() {
        StringBuilder contenidoCSV = new StringBuilder();
        for (actCategoria c : listaCategorias) {
            contenidoCSV.append(c.toCSV()).append("\n");
        }
        manejadorArchivo.guardar(contenidoCSV.toString());
    }

    private void cargarDatosDesdeArchivo() {
        ArrayList<String> lineas = manejadorArchivo.cargar();
        for (String linea : lineas) {
        	actCategoria c = actCategoria.fromCSV(linea);
            if (c != null) listaCategorias.add(c);
        }
        actualizarTextArea();
    }
}