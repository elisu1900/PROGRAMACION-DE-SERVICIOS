package main;

public class MainApp {

	public static void main(String[] args) {
		
		if (args.length <= 2) {
			System.out.println("no has introducido suficientes argmentos");
		}
		else {
			for (int i = 0; i < args.length; i++) {
			    System.out.println("Parámetro " + (i + 1) + ": " + args[i]);
			}
		}
	}
}
