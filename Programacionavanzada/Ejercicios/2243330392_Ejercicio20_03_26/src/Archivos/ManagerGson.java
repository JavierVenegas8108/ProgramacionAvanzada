package Archivos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Tickets;

public class ManagerGson {
    private Gson gson;

    public ManagerGson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardarTicketIndividual(Tickets ticket) {
        String nombreArchivo = ticket.getFolio() + ".json";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(ticket, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarEnHistorial(Tickets nuevoTicket) {
        List<Tickets> historial = cargarHistorial();
        historial.add(nuevoTicket);
        try (FileWriter writer = new FileWriter("historial_tickets.json")) {
            gson.toJson(historial, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Tickets> cargarHistorial() {
        File file = new File("historial_tickets.json");
        if (!file.exists()) return new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<List<Tickets>>(){}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}