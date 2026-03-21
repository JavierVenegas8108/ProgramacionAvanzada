package Modelo;

import java.io.*;
import java.util.ArrayList;

public class ArchivoServicio {
    private static final String CSV_FILE = "productos.csv";

    public static ArrayList<Entidad> importarCSV() {
        ArrayList<Entidad> lista = new ArrayList<>();
        File f = new File(CSV_FILE);

        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] d = linea.split(",");

                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    if (!d[0].trim().matches("\\d+")) continue; 
                }

                if (d.length >= 6) {  
                    Entidad e = new Entidad();
                    e.setId(d[0].trim());
                    e.setNombre(d[1].trim());
                    e.setCate(d[2].trim());
                    e.setPrecio(Double.parseDouble(d[3].trim()));
                    e.setCantidad(Integer.parseInt(d[4].trim()));
                    e.setRutaImagen(d[5].trim()); 
                    lista.add(e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}