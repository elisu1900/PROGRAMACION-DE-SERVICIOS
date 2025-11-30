package com.elias.services;

import com.elias.model.Client;
import com.elias.model.Tray;

import java.sql.Time;
import java.util.Random;

import static com.elias.model.Client.takePizza;

public class ShareService {

    public static Tray tray = new Tray();



    public static void paseo(boolean pizza, Client client) throws InterruptedException {
        Random rand = new Random();
        if (pizza){
            int delay = rand.nextInt(20000);
            Thread.sleep(delay);
            client.takePizza();
        }
    }
}
