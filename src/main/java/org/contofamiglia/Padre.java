package org.contofamiglia;

import java.util.Random;

public class Padre implements Runnable {
    private Conto conto;

    private Random rand = new Random();

    public Padre(Conto conto) {
        this.conto = conto;
    }

    public void run() {

        while(conto.isLowLevel()) {
            int quantita = rand.nextInt(100, 1000);
            System.out.println("Conto quantita: " + conto.getSaldo());
            System.out.println(Thread.currentThread().getName() +
                    " DEPOSITA <--- " + quantita + "");
            conto.deposita(quantita);
            SleepUtilities.nap();
            }
        }

}
