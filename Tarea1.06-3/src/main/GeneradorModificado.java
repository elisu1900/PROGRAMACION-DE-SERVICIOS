package main;

import java.io.File;
import java.io.FileWriter;

public class GeneradorModificado {

		public static void main(String[] args) {

			String nombre = (args.length > 0) ? args[0] : "default.txt";
			String contenido = "Este archivo se generó con el nombre: " + nombre;
			File archivo = new File(nombre);
			
			try {
				if (archivo.createNewFile()) {
					System.out.println("Archivo creado en: " + archivo.getAbsolutePath());
				} else {
					System.out.println("El archivo ya existe en: " + archivo.getAbsolutePath());
				}
			} catch (Exception e) {
				System.err.println("Error al crear el archivo: " + e.getMessage());
				e.printStackTrace();
			}
			
			try (FileWriter writer = new FileWriter(archivo)) {
				writer.write(contenido);
				System.out.println("Contenido escrito correctamente en: " + nombre);
			} catch (Exception e) {
				System.err.println("Error al escribir en el archivo: " + e.getMessage());
				e.printStackTrace();
			}
		}
	
}
