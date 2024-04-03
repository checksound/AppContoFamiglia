package org.contofamiglia3;

public class Conto {
    private int saldo;
    boolean lowLevel = false;
    private Object lowLevelObj = new Object();

    public Conto(int saldoIniziale) {
        this.saldo = saldoIniziale;
    }
    public synchronized int getSaldo() {
        return saldo;
    }
    public boolean  isLowLevel() {

        try {
            synchronized(lowLevelObj) {
               while(!lowLevel)
                    lowLevelObj.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public synchronized void deposita(int quantita) {
            notifyAll();
            saldo += quantita;
    }

    public synchronized void prelava(int quantita) {
        while (saldo < quantita) {
            try {
                lowLevel = true;
                synchronized(lowLevelObj) {
                    lowLevelObj.notify();
                }
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lowLevel = false;
        saldo -= quantita;
    }

}
