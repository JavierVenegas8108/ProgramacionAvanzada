package Libreria;

import java.io.*;
import java.util.ArrayList;

public class Archivotxt {
    private String nombreArchivo;

    public Archivotxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardar(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(texto);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> cargar() {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) return lineas;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return lineas;
    }
}