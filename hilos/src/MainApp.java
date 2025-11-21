import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp {
    public static List<Integer> primos = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {

        List<MiHilo> hilos = new ArrayList<>();
        Integer numeroHilos = 4;
        Integer inicio = 100000;
        Integer fin = 2000000;
        Integer tamaño = (fin - inicio) / numeroHilos;
        long tiempoInicio = System.currentTimeMillis();

        for (int i = 0; i < numeroHilos; i++) {
            int inicioHilo = inicio + (i * tamaño);
            int finHilo = (i == numeroHilos - 1) ? fin : inicio + ((i + 1) * tamaño);

            MiHilo hilo = new MiHilo("hilo" + i, inicioHilo, finHilo);
            hilo.start();
            hilos.add(hilo);
        }
        for (MiHilo hilo : hilos) {
            hilo.join();
        }

        long tiempoFin = System.currentTimeMillis();
        long total = tiempoFin - tiempoInicio;


        System.out.println("numeros primos:");
        System.out.println("=================================");
        for (Integer primo : primos) {
            System.out.println(primo);
        }
        System.out.println("el programa ha tardado: " + total + "ms");
    }


}
