package Modelo;

public class Entidad {
    private String id;
    private String nombre;
    private String cate;
    private double precio;
    private int cantidad;
    private String rutaImagen;

    public Entidad() {}

    public Entidad(String id, String nombre, String cate, double precio, int cantidad, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.cate = cate;
        this.precio = precio;
        this.cantidad = cantidad;
        this.rutaImagen = rutaImagen;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCate() { return cate; }
    public void setCate(String cate) { this.cate = cate; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}