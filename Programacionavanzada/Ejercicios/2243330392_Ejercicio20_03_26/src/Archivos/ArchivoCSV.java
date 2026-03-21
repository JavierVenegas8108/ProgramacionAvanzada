package Archivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Modelo.Entidad;

public class ArchivoCSV {
    private static final String RUTA = "productos.csv";

    public static void guardar(ArrayList<Entidad> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {
            for (Entidad e : lista) {
                pw.println(e.getId() + ";" + e.getNombre() + ";" + e.getCate() + ";" + 
                           e.getPrecio() + ";" + e.getCantidad());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Entidad> cargar() {
        ArrayList<Entidad> lista = new ArrayList<>();
        File file = new File(RUTA);
        if (!file.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                Entidad e = new Entidad();
                e.setId(d[0]); e.setNombre(d[1]); e.setCate(d[2]);
                e.setPrecio(Double.parseDouble(d[3]));
                e.setCantidad(Integer.parseInt(d[4]));
                lista.add(e);
            }
        } catch (Exception ex) {
            System.out.println("Archivo nuevo o vacío.");
        }
        return lista;
    }
}