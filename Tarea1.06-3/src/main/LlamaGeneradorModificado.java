package main;

import java.io.IOException;

public class LlamaGeneradorModificado {

	public static void main(String[] args) {
ProcessBuilder pb = new ProcessBuilder();
        
        try {
            System.out.println("\nPROCESO 1");
            pb.command("java", "-jar", "GeneradorModificado.jar", "archivo1.txt");
            Process p1 = pb.start();
            int exitCode1 = p1.waitFor();
            System.out.println("Proceso 1 terminado con codigo: " + exitCode1);
            
            System.out.println("\nPROCESO 2");
            pb.command("java", "-jar", "GeneradorModificado.jar", "ejercicio2.txt");
            Process p2 = pb.start();
            int exitCode2 = p2.waitFor();
            System.out.println("Proceso 2 terminado con codigo: " + exitCode2);
            
            System.out.println("\n PROCESO 3");
            pb.command("java", "-jar", "GeneradorModificado.jar");
            Process p3 = pb.start();
            int exitCode3 = p3.waitFor();
            System.out.println("Proceso 3 terminado con codigo: " + exitCode3);
            
            System.out.println("\n✓ Todos los procesos completados exitosamente");
            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
            e.printStackTrace();
        }
	}

}
