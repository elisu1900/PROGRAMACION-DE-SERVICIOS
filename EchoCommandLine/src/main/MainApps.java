package main;

public class MainApps {

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("No se metieron parametros");
		}
		else {
			for (int i = 0; i < args.length; i++) {
			    System.out.println("Parámetro " + (i + 1) + ": " + args[i]);
			}
		}
	}
}
