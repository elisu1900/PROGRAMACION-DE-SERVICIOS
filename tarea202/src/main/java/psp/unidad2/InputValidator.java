package psp.unidad2;

/**
 * Clase utilitaria para validar los argumentos de entrada del programa.
 * Verifica que los argumentos sean dos números válidos, positivos y en orden correcto.
 *
 * @author PSP Unidad 2
 * @version 1.0
 */
public class InputValidator {

    /**
     * Valida que los argumentos cumplan todos los requisitos necesarios:
     * debe haber exactamente dos argumentos, ambos deben ser números válidos,
     * ambos deben ser positivos y el primero debe ser menor o igual que el segundo.
     *
     * @param args Array de argumentos a validar
     * @return true si los argumentos son válidos, false en caso contrario
     */
    public static boolean validator(String[] args) {

        if (!twoArgs(args)) return false;

        Integer[] nums = parseNumbers(args);
        if (nums == null) return false;

        return isPositive(nums) && firstIsLower(nums);
    }

    /**
     * Verifica que se hayan proporcionado exactamente dos argumentos.
     *
     * @param args Array de argumentos
     * @return true si hay dos argumentos, false en caso contrario
     */
    private static boolean twoArgs(String[] args) {
        return args.length == 2;
    }

    /**
     * Intenta convertir los argumentos de String a Integer.
     *
     * @param args Array de cadenas a convertir
     * @return Array de Integer con los valores convertidos, o null si la conversión falla
     */
    private static Integer[] parseNumbers(String[] args) {
        try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            return new Integer[]{n1, n2};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Verifica que ambos números sean positivos (mayores o iguales a cero).
     *
     * @param nums Array con los dos números a verificar
     * @return true si ambos números son positivos, false en caso contrario
     */
    private static boolean isPositive(Integer[] nums) {
        return nums[0] >= 0 && nums[1] >= 0;
    }

    /**
     * Verifica que el primer número sea menor o igual que el segundo.
     *
     * @param nums Array con los dos números a comparar
     * @return true si el primer número es menor o igual que el segundo, false en caso contrario
     */
    private static boolean firstIsLower(Integer[] nums) {
        return nums[0] <= nums[1];
    }
}