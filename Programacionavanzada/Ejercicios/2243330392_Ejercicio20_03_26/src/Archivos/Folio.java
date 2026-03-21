package Archivos;
import java.io.*;
import java.util.Scanner;

public class Folio {

	private static final String CONTADOR = "Folio.txt";
	
	public static String SiguienteFolio() {
		int folio = 0;
		File archivo = new File(CONTADOR);
		if (archivo.exists()) {
			try (Scanner sc = new Scanner(archivo)) {
				if (sc.hasNextInt()) {
					folio = sc.nextInt();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int nuevoFolio = folio + 1;
		try(PrintWriter salida = new PrintWriter(new FileWriter(CONTADOR))){
			salida.println(nuevoFolio);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return String.format("T-%03d", nuevoFolio);
	}
}
