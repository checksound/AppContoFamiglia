package org.contofamiglia3;

import java.util.Random;

public class Figlio implements Runnable {

    private Conto conto;
    private Random rand = new Random();
    public Figlio(Conto conto) {
        this.conto = conto;
    }

    public void run() {
        while(true) {
            int quantita = rand.nextInt(100, 1000);
            System.out.println(Thread.currentThread().getName() + " cerca di prelevare " +
                    quantita);
            conto.prelava(quantita);
            System.out.println(Thread.currentThread().getName() + " PRELEVA ---> " +
                    quantita);

            SleepUtilities.nap();
        }
    }
}
