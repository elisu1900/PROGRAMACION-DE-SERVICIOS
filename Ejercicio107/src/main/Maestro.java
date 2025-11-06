package main;

import java.io.*;
import java.util.Scanner;

public class Maestro {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		try {
			String classpath = System.getProperty("java.class.path");
			ProcessBuilder pb = new ProcessBuilder("java","-cp",classpath, "main.Esclavo");


			pb.redirectErrorStream(true);
			Process procesoEsclavo = pb.start();

			PrintWriter enviarAlEsclavo = new PrintWriter(procesoEsclavo.getOutputStream(), true);

			BufferedReader recibirDelEsclavo = new BufferedReader(
					new InputStreamReader(procesoEsclavo.getInputStream()));

			System.out.println("****Maestro iniciado****");
			System.out.println("Escribe textos y el esclavo los procesará");
			System.out.println("Presiona Enter sin escribir nada para salir");
			System.out.println();

			String textoUsuario;
			while (true) {
				System.out.print("Introduce texto: ");
				textoUsuario = teclado.nextLine();

				if (textoUsuario.isEmpty()) {
					System.out.println("Saliendo...");
					break;
				}
				
				enviarAlEsclavo.println(textoUsuario);

				String respuesta = recibirDelEsclavo.readLine();
				
				if (respuesta != null) {
					System.out.println("Resultado: " + respuesta);
				} else {
					System.out.println("ERROR: No se recibió respuesta del esclavo");
					break;
				}
				System.out.println();
			}

			enviarAlEsclavo.close();
			recibirDelEsclavo.close();
			procesoEsclavo.destroy();
			teclado.close();

			System.out.println("Programa finalizado.");

		} catch (IOException e) {
			System.out.println("ERROR: No se pudo ejecutar el esclavo");
			System.out.println("Asegúrate de que Esclavo.class existe");
			e.printStackTrace();
		}
	}
}