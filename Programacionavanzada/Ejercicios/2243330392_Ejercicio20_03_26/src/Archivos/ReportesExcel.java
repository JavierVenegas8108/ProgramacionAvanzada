package Archivos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Modelo.Entidad;

public class ReportesExcel {

    public void generarReporteGeneral(List<Entidad> productos) {
        String nombreArchivo = "Inventario_General.xlsx";
        escribirExcel(productos, nombreArchivo, "Todos los Productos");
    }

    public void generarReportePorCategoria(List<Entidad> productos, String categoria) {
        List<Entidad> filtrados = new ArrayList<>();
        for (Entidad e : productos) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(categoria)) {
                filtrados.add(e);
            }
        }
        String nombreArchivo = "Reporte_" + categoria + ".xlsx";
        escribirExcel(filtrados, nombreArchivo, "Categoria " + categoria);
    }

    private void escribirExcel(List<Entidad> lista, String nombreArchivo, String nombreHoja) {
        try (XSSFWorkbook libro = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            XSSFSheet hoja = libro.createSheet(nombreHoja);

            String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría"};
            org.apache.poi.ss.usermodel.Row filaCabecera = hoja.createRow(0);
            for (int i = 0; i < columnas.length; i++) {
                filaCabecera.createCell(i).setCellValue(columnas[i]);
            }

            int rowNum = 1;
            for (Entidad p : lista) {
                org.apache.poi.ss.usermodel.Row fila = hoja.createRow(rowNum++);
                
                fila.createCell(0).setCellValue(String.valueOf(p.getId()));
                fila.createCell(1).setCellValue(String.valueOf(p.getNombre()));
                fila.createCell(2).setCellValue(p.getPrecio());
                fila.createCell(3).setCellValue(p.getCantidad());
                fila.createCell(4).setCellValue(p.getClass().getSimpleName());
            }

            try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream(nombreArchivo)) {
                libro.write(fileOut);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}