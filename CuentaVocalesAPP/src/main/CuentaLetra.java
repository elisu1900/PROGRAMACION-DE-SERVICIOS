package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CuentaLetra {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("El numero de argumentos no es el aporpiado, deben ser 2 (vocal), (ruta)");
			System.exit(1);
		}
		String vocal = args[0].toLowerCase();
		String ruta = args[1];
		String rutaVocal = "resultado_" + vocal + ".txt";
		char vocalChar = vocal.charAt(0);
		int contador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(ruta));
				PrintWriter pw = new PrintWriter(rutaVocal)) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String lineaPreparada = linea.toLowerCase();

				char[] letras = lineaPreparada.toCharArray();
				for (char letra : letras) {
					if (letra == vocalChar) {
						contador++;
					}
				}

			}
			pw.println(String.valueOf(contador));
			System.out.println(rutaVocal);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
