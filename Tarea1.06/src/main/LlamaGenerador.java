package main;

import java.io.IOException;

public class LlamaGenerador {

    public static void main(String[] args) {
        String[] comando = {"java", "-jar", "GenerarArchivo.jar"};
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            Process p = pb.start();
            int exitCode = p.waitFor(); 
            System.out.println("Proceso terminado con código: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}