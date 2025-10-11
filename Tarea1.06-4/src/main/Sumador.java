package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sumador {

    public static void main(String[] args) {
        File archivoEntrada = new File("numeros.txt");
        File archivoSalida = new File("resultado.txt");
        
        try {
            System.out.println("=== CREANDO ARCHIVO DE ENTRADA ===");
            crearArchivoEntrada(archivoEntrada);
            
            System.out.println("\n=== CONFIGURANDO PROCESO SUMADOR ===");
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "sumador.jar");
            
            pb.redirectInput(archivoEntrada);
            pb.redirectOutput(archivoSalida);
            
            
            System.out.println("Entrada redirigida desde: " + archivoEntrada.getAbsolutePath());
            System.out.println("Salida redirigida hacia: " + archivoSalida.getAbsolutePath());
            
            System.out.println("\n=== EJECUTANDO SUMADOR ===");
            Process proceso = pb.start();
            int exitCode = proceso.waitFor();
            System.out.println("Proceso terminado con código: " + exitCode);
            
            System.out.println("\n=== RESULTADO ===");
            mostrarContenidoArchivo(archivoEntrada, "ENTRADA");
            mostrarContenidoArchivo(archivoSalida, "SALIDA");
            
            verificarResultado(archivoSalida);
            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Crea el archivo de entrada con números de prueba
     */
    private static void crearArchivoEntrada(File archivo) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("10\n");
            writer.write("20\n");
            writer.write("30\n");
            writer.write("40\n");
            writer.write("\n"); 
            
            System.out.println("Archivo de entrada creado: " + archivo.getName());
            System.out.println("Números a sumar: 10.5, 20.3, 15.7, 4.5");
            System.out.println("Resultado esperado: 51.0");
        }
    }
    
    /**
     * Muestra el contenido de un archivo
     */
    private static void mostrarContenidoArchivo(File archivo, String tipo) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(archivo.getPath())));
            System.out.println(contenido.trim());
            System.out.println("Fin");
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si el resultado es correcto
     */
    private static void verificarResultado(File archivoSalida) {
        try {
            String resultado = new String(Files.readAllBytes(Paths.get(archivoSalida.getPath()))).trim();
            double suma = Double.parseDouble(resultado);
            double esperado = 51.0;
            
            System.out.println("\nVERIFICACIÓN");
            System.out.println("Resultado obtenido: " + suma);
            System.out.println("Resultado esperado: " + esperado);
            
            if (Math.abs(suma - esperado) < 0.01) {
                System.out.println("✓ ¡CORRECTO! La suma es correcta.");
            } else {
                System.out.println("✗ ERROR: La suma no coincide.");
            }
            
        } catch (Exception e) {
            System.err.println("Error al verificar resultado: " + e.getMessage());
        }
    }
}