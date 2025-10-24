package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class Maestro {


	public static void main(String[] args) throws IOException {

		try {
			if (args.length != 2) {
				System.err.println("Debn ser mas 2 argumentos");
				System.exit(1);
			}	

			Double min = Double.parseDouble(args[0]);
			Double max = Double.parseDouble(args[1]);

			if (min > max) {
				System.err.println("El primer argumento de ser menor al segundo");
				System.exit(1);
			}
				

			ArrayList<Double> numeros = generarNumerosAleatorios(min, max, 1000);

			String classpath = System.getProperty("java.class.path");
			ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", classpath, "main.Esclavo");

			processBuilder.redirectErrorStream(true);

			Process procesoEsclavo = processBuilder.start();
			System.out.println("el Proceso esclavo iniciado");

			try (BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(procesoEsclavo.getOutputStream()));
					BufferedReader lector = new BufferedReader(new InputStreamReader(procesoEsclavo.getInputStream()))) {
				
				for (Double numero : numeros) {
					escritor.write(String.valueOf(numero));
					escritor.newLine();
					escritor.flush();
				}
				escritor.newLine();
			    escritor.flush();
				
				String respuesta = lector.readLine();
				System.out.println("La suma de todos los numeros es: " + respuesta);
			
			} finally {
				procesoEsclavo.destroy();
				System.out.println("El Proceso esclavo terminado");
			}

		} catch (NumberFormatException e ) {
			System.err.println("Deben de ser argumentos numericos");
		}

	}

	private static ArrayList<Double> generarNumerosAleatorios(double min, double max, int cantidad) {

		Random random = new Random();
		ArrayList<Double> numeros = new ArrayList<Double>();
		for (int i = 1; i <= cantidad; i++) {
			double numero = random.nextDouble() * (max - min+1) + min;
			numeros.add(numero);
			System.out.println(numero);
		}
		System.out.println();
		return numeros;
	}


}