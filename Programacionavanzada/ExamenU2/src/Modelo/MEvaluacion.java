package Modelo;

import java.util.ArrayList;
import java.util.List;

public class MEvaluacion {
    private String id;
    private String asignatura;
    private String profesor;
    private String grupo;
    
    private String nombreProyecto;
    private String fecha;
    private String observaciones;
    
    private List<EvaluacionAlumno> alumnos; 
    private List<MRequisitos> requisitos;

    public MEvaluacion() {
        this.alumnos = new ArrayList<>();
        this.requisitos = new ArrayList<>();
    }

    public MEvaluacion(String asignatura, String profesor, String grupo) {
        this();
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.grupo = grupo;
        this.id = asignatura + "_" + profesor + "_" + grupo;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }

    public String getProfesor() { return profesor; }
    public void setProfesor(String profesor) { this.profesor = profesor; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getNombreProyecto() { return nombreProyecto; }
    public void setNombreProyecto(String nombreProyecto) { this.nombreProyecto = nombreProyecto; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<EvaluacionAlumno> getAlumnos() { return alumnos; }
    public void setAlumnos(List<EvaluacionAlumno> alumnos) { this.alumnos = alumnos; }

    public List<MRequisitos> getRequisitos() { return requisitos; }
    public void setRequisitos(List<MRequisitos> requisitos) { this.requisitos = requisitos; }
}