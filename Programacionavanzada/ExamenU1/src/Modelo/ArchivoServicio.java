package Modelo;
import java.io.*;
import java.util.ArrayList;

public class ArchivoServicio {
    private static final String CSV_FILE = "datos.csv";

    public static void exportarCSV(ArrayList<Entidad> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            for (Entidad e : lista) pw.println(e.toString());
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static ArrayList<Entidad> importarCSV() {
        ArrayList<Entidad> lista = new ArrayList<>();
        File f = new File(CSV_FILE);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                Entidad e = new Entidad();
                e.setId(d[0]); e.setNombre(d[1]); e.setCate(d[2]);
                e.setPrecio(Double.parseDouble(d[3])); e.setCantidad(Integer.parseInt(d[4]));
                lista.add(e);
            }
        } catch (Exception ex) { }
        return lista;
    }

    public static void imprimirTicket(ArrayList<Entidad> carrito, double total) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("TicketVenta.txt"))) {
            pw.println("======= TICKET DE VENTA =======");
            for (Entidad e : carrito) {
                pw.println(e.getNombre() + " x" + e.getCantidad() + " - $" + (e.getPrecio()*e.getCantidad()));
            }
            pw.println("-------------------------------");
            pw.println("TOTAL: $" + total);
            pw.println("===============================");
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}