package Modelo;
import java.io.*;
import java.util.ArrayList;

public class Tickets {
    public static void generarTicket(ArrayList<Entidad> carrito, double total) {
        String nombreArchivo = "Ticket_" + System.currentTimeMillis() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(nombreArchivo))) {
            out.println("********** TICKET DE VENTA **********");
            out.println("ID | Producto | Precio | Cant.");
            out.println("------------------------------------");
            for (Entidad e : carrito) {
                out.printf("%s | %s | $%.2f | %d\n", e.getId(), e.getNombre(), e.getPrecio(), e.getCantidad());
            }
            out.println("------------------------------------");
            out.printf("TOTAL A PAGAR: $%.2f\n", total);
            out.println("¡Gracias por su compra!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}