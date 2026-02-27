package Modelo;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class GestorArchivos {
    
    public List<Mpregunta> leerCSV(File archivo) {
        List<Mpregunta> listaCargada = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
               
                if (datos.length >= 3) {
                    String[] opciones = {datos[1], datos[2], "Opción C", "Opción D"};
                    listaCargada.add(new Mpregunta(datos[0], datos[1], opciones));
                }
            }
            if (listaCargada.size() < 5) {
                JOptionPane.showMessageDialog(null, "Error: El archivo debe tener al menos 5 registros.");
                return null;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al procesar el archivo o formato incorrecto.");
            return null;
        }
        return listaCargada;
    }
}