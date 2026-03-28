package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControladorDatos {
    private static final String FILE_PATH = "evaluaciones.json";
    private List<MEvaluacion> listaEvaluaciones;
    private Gson gson;

    public ControladorDatos() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.listaEvaluaciones = cargarTodo();
    }


    public void guardarOActualizar(MEvaluacion nueva) {
        int index = -1;
        for (int i = 0; i < listaEvaluaciones.size(); i++) {
            if (listaEvaluaciones.get(i).getId().equals(nueva.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            listaEvaluaciones.set(index, nueva);
        } else {
            listaEvaluaciones.add(nueva);
        }
        guardarEnArchivo();
    }

    public MEvaluacion buscarPorId(String id) {
        return listaEvaluaciones.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void eliminar(String id) {
        listaEvaluaciones.removeIf(e -> e.getId().equals(id));
        guardarEnArchivo();
    }


    private void guardarEnArchivo() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaEvaluaciones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<MEvaluacion> cargarTodo() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<MEvaluacion>>(){}.getType();
            List<MEvaluacion> datos = gson.fromJson(reader, listType);
            return (datos != null) ? datos : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}