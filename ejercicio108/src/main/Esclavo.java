package main;

import java.util.Random;
import java.util.Scanner;

public class Esclavo {

	public static void main(String[] args) {
		 String gotas = args[0];
		 int ngotas = Integer.parseInt(gotas);
		 
		 int puntosDentro = monteCarlo(ngotas);
		 
		 System.out.println(puntosDentro);
	     System.out.flush();
		 
		 }
	
	private static int monteCarlo(int gotas) {
		int puntoDentroDelCirculo = 0;
		Random rand = new Random();
		
		for (int i = 0; i < gotas; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            
            double distancia = x * x + y * y;

            if (distancia <= 1) {
            	puntoDentroDelCirculo++;
            }
            
		}
		return puntoDentroDelCirculo;
	}
	}
