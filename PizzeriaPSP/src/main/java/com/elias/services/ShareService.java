package com.elias.services;

import com.elias.model.Client;
import com.elias.model.PizzaMaker;
import com.elias.model.Tray;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona la creación y coordinación de hilos.
 */
public class ShareService {
    private final Tray tray;
    private final List<Thread> pizzaMakerThreads;
    private final List<Thread> clientThreads;
    private final List<PizzaMaker> pizzaMakers;

    /**
     * Constructor
     */
    public ShareService() {
        this.tray = new Tray();
        this.pizzaMakerThreads = new ArrayList<>();
        this.clientThreads = new ArrayList<>();
        this.pizzaMakers = new ArrayList<>();
    }

    /**
     * Crea y arranca los hilos de pizzeros y clientes.
     *
     * @param numPizzaMakers numero de pizzeros
     * @param numClients número de clientes
     */
    public void startSimulation(int numPizzaMakers, int numClients) {
        // Crear pizzeros
        for (int i = 1; i <= numPizzaMakers; i++) {
            PizzaMaker pizzaMaker = new PizzaMaker(i, tray);
            pizzaMakers.add(pizzaMaker);
            Thread thread = new Thread(pizzaMaker);
            pizzaMakerThreads.add(thread);
            thread.start();
        }

        // Crear clientes
        for (int i = 1; i <= numClients; i++) {
            Client client = new Client(i, tray);
            Thread thread = new Thread(client);
            clientThreads.add(thread);
            thread.start();
        }
    }

    /**
     * Espera a que todos los clientes terminen y luego detiene los pizzeros.
     */
    public void waitForCompletion() {
        try {
            // Esperar a que todos los clientes terminen
            for (Thread thread : clientThreads) {
                thread.join();
            }

            System.out.println("\n=== Todos los clientes han terminado ===");
            System.out.println("Cerrando la pizzería...\n");

            // Detener a los pizzeros
            for (PizzaMaker pizzaMaker : pizzaMakers) {
                pizzaMaker.stopWorking();
            }

            // Interrumpir los hilos de los pizzeros
            for (Thread thread : pizzaMakerThreads) {
                thread.interrupt();
            }

            // Esperar a que los pizzeros terminen
            for (Thread thread : pizzaMakerThreads) {
                thread.join();
            }

            System.out.println("\n=== Pizzería cerrada ===");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}