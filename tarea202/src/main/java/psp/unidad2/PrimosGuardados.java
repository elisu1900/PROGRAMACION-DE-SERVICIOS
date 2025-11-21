package psp.unidad2;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona una lista compartida de números primos de forma segura
 * para entornos multihilo. Utiliza sincronización para evitar condiciones de carrera.
 *
 * @author PSP Unidad 2
 * @version 1.0
 */
public class PrimosGuardados {
    /**
     * Lista que almacena los números primos encontrados
     */
    List<Integer> primos = new ArrayList<>();

    /**
     * Añade un número primo a la lista de forma sincronizada.
     * Este método es thread-safe y puede ser llamado por múltiples hilos simultáneamente.
     *
     * @param numero Número primo a añadir a la lista
     */
    public synchronized void add(int numero) {
        primos.add(numero);
    }

    /**
     * Devuelve una lista ordenada de todos los números primos almacenados.
     *
     * @return Lista inmutable de números primos ordenados de menor a mayor
     */
    public List<Integer> show() {

        return primos.stream().sorted().toList();
    }
}