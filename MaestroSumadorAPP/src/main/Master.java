package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Master {

	public static final int CORES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("los argumentos deben ser estrictamente 2");
			System.exit(1);
		}

		try {
			int numeroMenor = Math.min(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			int numeroMayor = Math.max(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			int diferencia;
			List<Process> procesos = new ArrayList<>();
			Process procesoEsclavo;
			String jarPath = new File(Master.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getPath();

			diferencia = numeroMayor - numeroMenor;

			if (diferencia < 25) {
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", jarPath, "main.Slave",
						String.valueOf(numeroMenor), String.valueOf(numeroMayor));
				pb.redirectErrorStream(true);
				procesoEsclavo = pb.start();
				procesos.add(procesoEsclavo);

			} else if (diferencia >= 25 && diferencia <= 100) {
				int division = diferencia / 2;
				for (int i = 0; i < 2; i++) {

					int inicio = numeroMenor + (i * division);
					int fin;
					if (i == 1) {
						fin = numeroMayor;
					} else {
						fin = numeroMenor + ((i + 1) * division) - 1;
					}

					ProcessBuilder pb = new ProcessBuilder("java", "-cp", jarPath, "main.Slave", String.valueOf(inicio),
							String.valueOf(fin));

					pb.redirectErrorStream(true);
					procesoEsclavo = pb.start();
					procesos.add(procesoEsclavo);
				}
			} else {
				int division = diferencia / CORES;

				for (int i = 0; i < CORES; i++) {
					int inicio = numeroMenor + (i * division);
					int fin;
					if (i == CORES - 1) {
						fin = numeroMayor;
					} else {
						fin = numeroMenor + ((i + 1) * division) - 1;
					}
					ProcessBuilder pb = new ProcessBuilder("java", "-cp", jarPath, "main.Slave", String.valueOf(inicio),
							String.valueOf(fin));

					pb.redirectErrorStream(true);
					procesoEsclavo = pb.start();
					procesos.add(procesoEsclavo);
				}
			}

			int totalGeneral = 0; 

			for (Process proceso : procesos) {
			    BufferedReader recibirDelEsclavo = new BufferedReader(
			        new InputStreamReader(proceso.getInputStream()));

			    String linea;
			    while ((linea = recibirDelEsclavo.readLine()) != null) {
			        System.out.println("Esclavo devuelve: " + linea);  
			        totalGeneral += Integer.parseInt(linea);  
			    }

			    proceso.waitFor();
			    recibirDelEsclavo.close();
			}

			// Al final muestras el total
			System.out.println("──────────────────────────");
			System.out.println("TOTAL: " + totalGeneral);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
