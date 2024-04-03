package org.contofamiglia;

public class Conto {
    private int saldo;
    private boolean lowLevel;

    public Conto(int saldoIniziale) {
        this.saldo = saldoIniziale;
    }
    public synchronized int getSaldo() {
        return saldo;
    }
    public synchronized boolean  isLowLevel() {

        try {
            while (!lowLevel)
                wait();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public synchronized void deposita(int quantita) {
        lowLevel = false;
        notifyAll();
        saldo += quantita;
    }

    public synchronized void prelava(int quantita) {
        while (saldo < quantita) {
            try {
                lowLevel = true;

                notifyAll();

                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lowLevel = false;
        notifyAll();
        saldo -= quantita;
    }

}
