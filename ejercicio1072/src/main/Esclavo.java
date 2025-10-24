package main;


import java.util.Scanner;

public class Esclavo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sumaTotal = 0;
        String linea;

        while (scanner.hasNextLine() && !(linea = scanner.nextLine()).isEmpty()) {

            try {
                double numero = Double.parseDouble(linea);
                sumaTotal += numero;
            } catch (NumberFormatException e) {
                System.err.println("Error parseando número: " + linea);
            }
        }

        System.out.println(sumaTotal);
        System.out.flush();

        scanner.close();
    }
}
