package main;

import java.util.Scanner;

public class Slave {

	public static void main(String[] args) {

		int inicio = Integer.parseInt(args[0]);
		int fin = Integer.parseInt(args[1]);
		int diferencia = fin - inicio;
		int resultado = 0;
		for (int i = inicio; i <= fin; i++) {
			resultado += i;
		}
		System.out.println(resultado);
		System.out.flush();
	}
}
