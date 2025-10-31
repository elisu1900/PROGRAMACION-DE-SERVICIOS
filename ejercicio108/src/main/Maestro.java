package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Maestro {

	
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("los parametros  recibir son estrictamente 2");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		
		try {
			String classpath = System.getProperty("java.class.path");
			
			int nProcesos = Integer.parseInt(args[0]);
			int nGotas = Integer.parseInt(args[1]);
			int nGotasByProcess = Integer.parseInt(args[1]) / nProcesos;
			int resultado;
			
			List<Process> procesos = new  ArrayList<>();
			List<Integer> resultados = new ArrayList<>();
			
			
			for (int i = 0; i < nProcesos; i++) {
				
				ProcessBuilder pb = new ProcessBuilder("java","-cp", classpath, "main.Esclavo", String.valueOf(nGotasByProcess));
				
				Process procesoEsclavo = pb.start();
				procesos.add(procesoEsclavo);
			}
			
			int sumaTotal = 0;
			for (Process proceso : procesos) {
				
				long inicio = System.nanoTime();
				
				BufferedReader recibirDelEsclavo = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				resultado = Integer.parseInt(recibirDelEsclavo.readLine());
				resultados.add(resultado);
				sumaTotal += resultado;
				
				int exitCode = proceso.waitFor();
				long fin = System.nanoTime();
				double duracionMs = (fin- inicio)/1_000_000.0;
				System.out.println("proceso terminado con codigo: " + exitCode + "y tardó: " + duracionMs + "ms");
 

			}
			System.out.println(sumaTotal + "      " + nGotas);
			double pi = 4 * ((double) sumaTotal / nGotas);
			System.out.println("la estimacion de PI por el metodo de monte Carlo es de: " + pi);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
