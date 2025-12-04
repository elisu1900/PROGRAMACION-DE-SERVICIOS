package com.elias.model;

import java.util.Random;

/**
 * Clase de un cliente de la pizzeria.
 */
public class Client implements Runnable {
    private final int id;
    private final Tray tray;
    private final Random random;
    private int pizzasEaten;
    private static final int MAX_PIZZAS = 5;

    /**
     * Constructor del cliente
     *
     * @param id identificador
     * @param tray bandeja compartida
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
                System.out.println("Cliente " + id + " entra e intenta coger una pizza");

                if (tray.takePizza()) {
                    pizzasEaten++;
                    System.out.println("Cliente " + id + " coge una pizza (pizza número " +
                            pizzasEaten + ")");
                } else {
                    System.out.println("Cliente " + id + ": bandeja vacía");
                }

                System.out.println("Cliente " + id + " de paseo");

                int walkTime;
                if (pizzasEaten > 0) {
                    walkTime = 20000 + random.nextInt(10001);
                } else {
                    walkTime = 10000 + random.nextInt(5001);
                }

                Thread.sleep(walkTime);
                System.out.println("Cliente " + id + " termina paseo");

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Cliente " + id + " ha comido " + MAX_PIZZAS + " pizzas y se va");
    }
}