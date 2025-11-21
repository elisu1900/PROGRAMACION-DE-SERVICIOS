package main;

import java.util.Random;
import java.util.Scanner;

public class Esclavo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rand = new Random();

		while (sc.hasNextLine()) {

			String linea = sc.nextLine();
			
			if (linea.isEmpty()) {
				break;
			}

			int operacion = rand.nextInt(3);

			switch (operacion) {
			case 0 -> System.out.println(linea.toUpperCase());

			case 1 -> System.out.println(linea.toLowerCase());

			case 2 -> System.out.println(capitalizar(linea));

			default -> throw new IllegalArgumentException("Unexpected value: " + operacion);
			}
			
			System.out.flush();
		}
	}

	private static String capitalizar(String linea) {

		String[] palabras = linea.split(" ");
		StringBuilder resultado = new StringBuilder();

		for (String palabra : palabras) {
			if (palabra.length() > 0) {
				String capitalizada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
				resultado.append(capitalizada);
				resultado.append(" ");
			}
		}

		return resultado.toString().trim();
	}
}