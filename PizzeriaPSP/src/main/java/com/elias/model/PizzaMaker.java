package com.elias.model;

import java.util.Random;

/**
 * Clase que representa un pizzero.
 */
public class PizzaMaker implements Runnable {
    private final int id;
    private final Tray tray;
    private final Random random;
    private boolean working;

    /**
     * Constructor del pizzero.
     *
     * @param id identificador
     * @param tray bandeja compartida
     */
    public PizzaMaker(int id, Tray tray) {
        this.id = id;
        this.tray = tray;
        this.random = new Random();
        this.working = true;
    }

    /**
     * deja de trabajar el pizzero
     */
    public void stopWorking() {
        this.working = false;
    }

    @Override
    public void run() {
        System.out.println("Pizzero " + id + " iniciado y listo para trabajar");

        while (working) {
            try {
                System.out.println("Pizzero " + id + " comienza a preparar una pizza");

                int preparationTime = 5000 + random.nextInt(5001);
                Thread.sleep(preparationTime);

                tray.depositPizza();
                int pizzasInTray = tray.getPizzaCount();
                System.out.println("Pizzero " + id + " termina una pizza " + "Pizzas en bandeja: " + pizzasInTray);

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Pizzero " + id + " termina su trabajo y se va");
    }
}