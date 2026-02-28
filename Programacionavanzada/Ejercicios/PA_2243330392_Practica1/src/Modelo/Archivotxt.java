package Modelo;

import java.io.*;
import java.util.ArrayList;

public class Archivotxt {
    private final String NOMBRE_ARCHIVO = "categorias.txt";

    public void guardarArchivo(ArrayList<Categoria> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Categoria c : lista) {
                // Guardamos los datos separados por un punto y coma para facilitar la lectura
                bw.write(c.getIdcategoria() + ";" + c.getCategoria());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public ArrayList<Categoria> cargarArchivo() {
        ArrayList<Categoria> lista = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);
        
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    lista.add(new Categoria(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
        return lista;
    }
}