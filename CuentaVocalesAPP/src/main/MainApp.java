package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public static final String[] VOCALES = { "a", "e", "i", "o", "u" };

	public static void main(String[] args) {
		
		try {
			String file = "./resources/el_quijote.txt";

			List<Process> procesos = new  ArrayList<>();
			List<String> rutas = new ArrayList<>();
			
			String classpath = System.getProperty("java.class.path");
			

			

			for (String vocal : VOCALES) {
				ProcessBuilder pb = new ProcessBuilder("java","-cp", classpath, "main.CuentaLetra", vocal, file);
				
				Process procesoEsclavo = pb.start();
				procesos.add(procesoEsclavo);
				
			}
			
			for (Process proceso : procesos) {
				long inicio = System.nanoTime();

				BufferedReader recibirDelEsclavo = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				String resultado = recibirDelEsclavo.readLine();
				rutas.add(resultado);
				
				int exitCode = proceso.waitFor();
				long fin = System.nanoTime();
				double duracionMs = (fin- inicio)/1_000_000.0;
				System.out.println("proceso terminado con codigo " + exitCode + " y ha tardado " + duracionMs + "ms");
			}
			int resultado = 0;

			for (String ruta : rutas) {
				BufferedReader br = new BufferedReader(new FileReader(ruta));
				String linea = br.readLine();
				resultado += Integer.parseInt(linea.trim());
				br.close();
			}
			System.out.println("el total de vocales es: " + resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
