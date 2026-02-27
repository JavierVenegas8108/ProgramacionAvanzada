package Modelo;
public class Mpregunta {
    private String enunciado;
    private String respuestaCorrecta;
    private String[] opciones;

    public Mpregunta(String enunciado, String respuestaCorrecta, String[] opciones) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.opciones = opciones;
    }

 
    public String getEnunciado() { return enunciado; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public String[] getOpciones() { return opciones; }
}