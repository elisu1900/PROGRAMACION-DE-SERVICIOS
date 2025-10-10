package main;

import java.io.File;
import java.io.FileWriter;

public class GenerarArchivo {
	
	public static void main(String[] args) {
		String nombre = "ejercicio2.txt";
		String contenido = "Hola soy la persona X";
		File archivo = new File(nombre);
		
		
		try {
			if (archivo.createNewFile()) {
				System.out.println("archivo creado en:" + archivo.getAbsolutePath());
			}else {
				System.out.println("El archivo ya existe en:" + archivo.getAbsolutePath());
			}
		} catch (Exception e) {
		}
		try (FileWriter writer = new FileWriter(archivo)){
			writer.write(contenido);
		} catch (Exception e) {
		}
	}
}
