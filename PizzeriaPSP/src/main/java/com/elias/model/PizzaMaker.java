package com.elias.model;

import java.util.Random;

/**
 * Clase que representa un pizzero.
 */
public class PizzaMaker implements Runnable {
    private final int id;
    private final Tray tray;
    private final Random random;
    private volatile boolean working;

    /**
     * Constructor del pizzero
     *
     * @param id
     * @param tray
     */
    public PizzaMaker(int id, Tray tray) {
        this.id = id;
        this.tray = tray;
        this.random = new Random();
        this.working = true;
    }

    /**
     * Detiene el trabajo del pizzero
     */
    public void stopWorking() {
        this.working = false;
    }

    @Override
    public void run() {
        System.out.println("Pizzero " + id + " iniciado y listo para trabajar");

        while (working) {
            try {
                // Comienza a preparar una pizza
                System.out.println("Pizzero " + id + " comienza a preparar una pizza");

                // Tarda entre 5 y 10 segundos
                int preparationTime = 5000 + random.nextInt(5001);
                Thread.sleep(preparationTime);

                // Termina y coloca la pizza en la bandeja
                tray.depositPizza();
                int pizzasInTray = tray.getPizzaCount();
                System.out.println("Pizzero " + id + " termina una pizza y la coloca en la bandeja" +
                        "Pizzas en bandeja: " + pizzasInTray);

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Pizzero " + id + " termina su trabajo y se va a casa");
    }
}