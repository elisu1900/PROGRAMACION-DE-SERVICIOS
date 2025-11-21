package psp.unidad2;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * Aplicación que calcula números primos en un rango dado utilizando múltiples hilos.
 * Distribuye el trabajo entre los procesadores disponibles para optimizar el rendimiento.
 *
 * @author PSP Unidad 2
 * @version 1.0
 */
public class CalculaPrimosCompartidosApp {

    /**
     * Método principal que coordina el cálculo paralelo de números primos.
     * Valida los argumentos de entrada, divide el rango en segmentos y asigna
     * cada segmento a un hilo diferente.
     *
     * @param args Array de argumentos: [inicio, fin] del rango a analizar
     */
    public static void main(String[] args) {

        if (!InputValidator.validator(args)) {
            System.err.println("Ha habido un problema con los argumentos dados");
            System.exit(1);
        }
        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);
        int numeroHilos = Runtime.getRuntime().availableProcessors();

        List<Thread> threads = new ArrayList<>();
        Integer size = (fin - inicio) / numeroHilos;
        PrimosGuardados listaCompartida = new PrimosGuardados();

        for (int i = 0; i < numeroHilos; i++) {
            int inicioHilo = inicio + (i * size);
            int finHilo = (i == numeroHilos - 1) ? fin : inicio + ((i + 1) * size);

            Hilo hilo = new Hilo(inicioHilo, finHilo, listaCompartida);
            Thread thread = new Thread(hilo);
            thread.start();
            threads.add(thread);
        }
        for (Thread hilo : threads) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Un hilo ha sido interrumpido");
            }
        }
        System.out.println(listaCompartida.show());

    }
}