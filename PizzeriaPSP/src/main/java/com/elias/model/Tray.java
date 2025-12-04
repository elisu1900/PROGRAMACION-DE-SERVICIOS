package com.elias.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la bandeja compartida de pizzas
 */
public class Tray {
    private final List<String> pizzas;

    /**
     * Constructor de la bandeja
     */
    public Tray() {
        this.pizzas = new ArrayList<>();
    }

    /**
     * Deposita una pizza en la bandeja.
     */
    public synchronized void depositPizza() {
        pizzas.add("Pizza");
    }

    /**
     * Intenta tomar una pizza de la bandeja.
     *
     * @return true si había pizza disponible, false en caso contrario
     */
    public synchronized boolean takePizza() {
        if (!pizzas.isEmpty()) {
            pizzas.remove(0);
            return true;
        }
        return false;
    }

    /**
     *  Da el numero de pizzas en la bandeja.
     * @return número de pizzas
     */
    public synchronized int getPizzaCount() {
        return pizzas.size();
    }
}