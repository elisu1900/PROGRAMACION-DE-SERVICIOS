package com.elias.model;

import java.util.Random;
import com.elias.model.Tray;
import com.elias.services.ShareService;
/**
 * Clase que representa un cliente de la pizzería.
 * Implementa Runnable para ejecutarse en un hilo separado.
 */
public class Client implements Runnable {
    private final int id;
    private final Tray tray;
    private final Random random;
    private int pizzasEaten;
    private static final int MAX_PIZZAS = 5;

    /**
     * Constructor del cliente.
     *
     * @param id identificador único del cliente
     * @param tray bandeja compartida de pizzas
     */
    public Client(int id, Tray tray) {
        this.id = id;
        this.tray = tray;
        this.random = new Random();
        this.pizzasEaten = 0;
    }

    @Override
    public void run() {
        System.out.println("Cliente " + id + " iniciado");

        while (pizzasEaten < MAX_PIZZAS) {
            try {
                // Entra en la tienda
                System.out.println("Cliente " + id + " entra en la tienda e intenta tomar una pizza");

                // Intenta tomar una pizza
                if (tray.takePizza()) {
                    pizzasEaten++;
                    System.out.println("Cliente " + id + " toma una pizza (pizza número " +
                            pizzasEaten + " de " + MAX_PIZZAS + ")");
                } else {
                    System.out.println("Cliente " + id + " no puede tomar una pizza (bandeja vacía)");
                }

                // Comienza un paseo
                System.out.println("Cliente " + id + " comienza un paseo");

                // Tiempo del paseo depende de si ha comido
                int walkTime;
                if (pizzasEaten > 0) {
                    // Entre 20 y 30 segundos si ha comido
                    walkTime = 20000 + random.nextInt(10001);
                } else {
                    // Entre 10 y 15 segundos si no ha comido
                    walkTime = 10000 + random.nextInt(5001);
                }

                Thread.sleep(walkTime);
                System.out.println("Cliente " + id + " termina el paseo");

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Cliente " + id + " ha comido " + MAX_PIZZAS +
                " pizzas y se va satisfecho a casa");
    }
}