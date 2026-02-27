package Modelo;
import java.util.Collections;
import java.util.List;

public class Examen {
    private List<Mpregunta> listaPreguntas; // Almacena los objetos del archivo [cite: 8]
    private int indiceActual = 0; // Control del índice actual [cite: 10]

    public Examen(List<Mpregunta> preguntas) {
        this.listaPreguntas = preguntas;
    }

    public void barajar() { 
        Collections.shuffle(listaPreguntas); // Método para mezclar el orden [cite: 9]
    }

    public Mpregunta obtenerActual() {
        if (indiceActual < listaPreguntas.size()) return listaPreguntas.get(indiceActual);
        return null;
    }

    public void siguiente() { indiceActual++; }
}