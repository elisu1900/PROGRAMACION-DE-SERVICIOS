package main;

public class Main {

	public static void main(String[] args) {
		
		if (args.length <= 2) {
			args = new String[] {"parametro1", "parametro2"};
			for (int i = 0; i < args.length; i++) {
			    System.out.println("Parámetro " + (i + 1) + ": " + args[i]);
			}
		}
		else {
			for (int i = 0; i < args.length; i++) {
			    System.out.println("Parámetro " + (i + 1) + ": " + args[i]);
			}
		}
	}
}