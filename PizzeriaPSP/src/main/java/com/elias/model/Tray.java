package com.elias.model;

public class Tray {

    private int nPizzas;

    public Tray() {
        this.nPizzas = 0;
    }

    /**
     * set the number of pizzas to a specific value
     * @param nPizzas number of pizzas
     */


    /**
     * return the number of pizza in the tray
     * @return number of pizzas
     */
    public int getnPizzas() {
        return nPizzas;
    }

    /**
     * the pizza maker add a pizza
     */
    public synchronized void add(){
        this.nPizzas++;
    }

    /**
     * the cliente take a pizza
     */
    public synchronized void subtract(){
        this.nPizzas--;
    }
}
