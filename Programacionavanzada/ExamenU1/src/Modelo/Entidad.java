package Modelo;

public class Entidad {
    private String nombre, id, desc, cate;
    private double precio;
    private int cantidad;

    public Entidad() {}

    public String getNombre() {
    	return nombre; }
    public void setNombre(String nombre) {
    	this.nombre = nombre; }
    public String getId() {
    	return id; }
    public void setId(String id) { 
    	this.id = id; }
    public String getDesc() { 
    	return desc; }
    public void setDesc(String desc) {
    	this.desc = desc; }
    public String getCate() { 
    	return cate; }
    public void setCate(String cate) {
    	this.cate = cate; }
    public double getPrecio() {
    	return precio; }
    public void setPrecio(double precio) {
    	this.precio = precio; }
    public int getCantidad() {
    	return cantidad; }
    public void setCantidad(int cantidad) {
    	this.cantidad = cantidad; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + cate + "," + precio + "," + cantidad;
    }
}