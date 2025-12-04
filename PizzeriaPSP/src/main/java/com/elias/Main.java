package com.elias;

import java.util.ArrayList;
import java.util.List;
import com.elias.model.Client;
import com.elias.model.Tray;
import com.elias.model.PizzaMaker;
import com.elias.services.ShareService;
/**
 * Clase principal de la simulación de Pizzería Luigi's.
 * Gestiona la creación de hilos de pizzeros y clientes.
 *
 * @author Elias
 * @version 1.0
 */
public class Main{
    private static final int DEFAULT_PIZZA_MAKERS = 2;
    private static final int DEFAULT_CLIENTS = 5;

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos:
     * args[0] - número de pizzeros (por defecto 2)
     * args[1] - número de clientes (por defecto 5)
     */
    public static void main(String[] args) {
        int numPizzaMakers = DEFAULT_PIZZA_MAKERS;
        int numClients = DEFAULT_CLIENTS;

        if (args.length >= 1) {
            try {
                numPizzaMakers = Integer.parseInt(args[0]);
                if (numPizzaMakers < 1) {
                    System.err.println("El número de pizzeros debe ser mayor que 1");
                    System.err.println("Usando valor por defecto: " + DEFAULT_PIZZA_MAKERS);
                    numPizzaMakers = DEFAULT_PIZZA_MAKERS;
                }
            } catch (NumberFormatException e) {
                System.err.println("Parámetro inválido para pizzeros. Usando valor por defecto: "
                        + DEFAULT_PIZZA_MAKERS);
            }
        }

        if (args.length >= 2) {
            try {
                numClients = Integer.parseInt(args[1]);
                if (numClients < 1) {
                    System.err.println("El numero de clientes debe ser mayor que 1");
                    System.err.println("valor por defecto: " + DEFAULT_CLIENTS);
                    numClients = DEFAULT_CLIENTS;
                }
            } catch (NumberFormatException e) {
                System.err.println("Parámetro inválido para clientes. Usando valor por defecto: "
                        + DEFAULT_CLIENTS);
            }
        }

        System.out.println("   PIZZERÍA LUIGI'S - SIMULACIÓN   ");
        System.out.println("Pizzeros: " + numPizzaMakers);
        System.out.println("Clientes: " + numClients);

        Tray tray = new Tray();

        List<Thread> pizzaMakerThreads = new ArrayList<>();
        List<Thread> clientThreads = new ArrayList<>();
        List<PizzaMaker> pizzaMakers = new ArrayList<>();

        for (int i = 1; i <= numPizzaMakers; i++) {
            PizzaMaker pizzaMaker = new PizzaMaker(i, tray);
            pizzaMakers.add(pizzaMaker);
            Thread thread = new Thread(pizzaMaker);
            pizzaMakerThreads.add(thread);
            thread.start();
        }

        for (int i = 1; i <= numClients; i++) {
            Client client = new Client(i, tray);
            Thread thread = new Thread(client);
            clientThreads.add(thread);
            thread.start();
        }

        try {
            for (Thread thread : clientThreads) {
                thread.join();
            }

            System.out.println("\n=== Todos los clientes han terminado ===");
            System.out.println("Cerrando la pizzería...\n");

            for (PizzaMaker pizzaMaker : pizzaMakers) {
                pizzaMaker.stopWorking();
            }

            for (Thread thread : pizzaMakerThreads) {
                thread.interrupt();
            }

            for (Thread thread : pizzaMakerThreads) {
                thread.join();
            }

            System.out.println("\n=== Pizzería cerrada ===");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La simulación fue interrumpida");
        }
    }
}