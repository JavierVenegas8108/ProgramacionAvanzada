package Modelo;

public class EvaluacionAlumno {
    private String nombre;
    private double criterio1; 
    private double criterio2; 
    private double criterio3; 
    private double promedio;

    public EvaluacionAlumno (String nombre, double c1, double c2, double c3, double promedio) {
        this.nombre = nombre;
        this.criterio1 = c1;
        this.criterio2 = c2;
        this.criterio3 = c3;
        this.promedio = promedio;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getCriterio1() { return criterio1; }
    public void setCriterio1(double criterio1) { this.criterio1 = criterio1; }

    public double getCriterio2() { return criterio2; }
    public void setCriterio2(double criterio2) { this.criterio2 = criterio2; }

    public double getCriterio3() { return criterio3; }
    public void setCriterio3(double criterio3) { this.criterio3 = criterio3; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
}