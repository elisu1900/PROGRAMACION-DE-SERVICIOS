package psp.unidad2;

/**
 * Clase que representa un hilo de ejecución para calcular números primos
 * en un rango específico. Implementa Runnable para permitir la ejecución concurrente.
 *
 * @author PSP Unidad 2
 * @version 1.0
 */
public class Hilo implements Runnable {

    /**
     * Valor inicial del rango a analizar
     */
    private int inicio;

    /**
     * Valor final del rango a analizar
     */
    private int fin;

    /**
     * Estructura compartida donde se almacenan los números primos encontrados
     */
    private PrimosGuardados guardados;

    /**
     * Constructor que inicializa el hilo con el rango de números a analizar
     * y la estructura compartida para almacenar resultados.
     *
     * @param inicioHilo Primer número del rango a analizar
     * @param finHilo    Último número del rango a analizar
     * @param guardados  Objeto compartido para almacenar los números primos
     */
    public Hilo(int inicioHilo, int finHilo, PrimosGuardados guardados) {
        this.inicio = inicioHilo;
        this.fin = finHilo;
        this.guardados = guardados;
    }

    /**
     * Ejecuta el algoritmo de búsqueda de números primos en el rango asignado.
     * Para cada número, verifica si es primo dividiendo entre todos los números
     * desde 2 hasta la raíz cuadrada del número.
     */
    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (i <= 1) {
                continue;
            }

            if (i == 2) {
                guardados.add(i);
                continue;
            }
            boolean esprimo = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    esprimo = false;
                    break;
                }
            }
            if (esprimo) guardados.add(i);
        }
    }
}