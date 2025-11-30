package com.elias.model;

import com.elias.services.ShareService;

import static com.elias.services.ShareService.paseo;

public class Client implements Runnable{

    private int id;
    private int nPizza;
    private static Tray tray;

    /**
     * Constructor of Client receive the sharedTray and the id
     * @param tray the shared tray
     * @param id the id of the Client
     */
    public Client(Tray tray, int id) {
        this.tray = tray;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getnPizza() {
        return nPizza;
    }

    public void setnPizza(int nPizza) {
        this.nPizza = nPizza;
    }

    public Tray getTray() {
        return tray;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nPizza=" + nPizza +
                ", tray=" + tray +
                '}';
    }

    @Override
    public void run() {
        System.out.println("inicio");
        System.out.println("entra en la tienda");
        try {
            takePizza();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void takePizza() throws InterruptedException {
        if (tray.getnPizzas() <= 0){
            paseo(false, this);
        }
        else{
            tray.subtract();
        }
    }
}
