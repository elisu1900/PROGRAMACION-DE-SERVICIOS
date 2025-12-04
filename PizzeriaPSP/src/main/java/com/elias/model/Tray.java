package com.elias.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la bandeja compartida de pizzas
 */
public class Tray {
    private final List<String> pizzas;

    /**
     * constructor de la bandeja
     */
    public Tray() {
        this.pizzas = new ArrayList<>();
    }

    /**
     * pone una pizza en la bandeja
     */
    public synchronized void depositPizza() {
        pizzas.add("Pizza");
    }

    /**
     * Intenta coger una pizza de la bandeja
     *
     * @return true si hay pizza, false si no
     */
    public synchronized boolean takePizza() {
        if (!pizzas.isEmpty()) {
            pizzas.remove(0);
            return true;
        }
        return false;
    }

    /**
     *  Da el numero de pizzas en bandeja
     * @return numero de pizzas
     */
    public synchronized int getPizzaCount() {
        return pizzas.size();
    }
}