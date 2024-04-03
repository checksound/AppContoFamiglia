package org.contofamiglia2;

import org.contofamiglia.SleepUtilities;

import java.util.Random;

public class Genitore implements Runnable {
    private Conto conto;
    private Random rand = new Random();
    public Genitore(Conto conto) {
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
