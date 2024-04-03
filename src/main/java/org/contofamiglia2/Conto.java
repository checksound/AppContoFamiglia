package org.contofamiglia2;

public class Conto {
    private int saldo;
    private boolean lowLevel;
    private int tryDeposito = 0;

    public Conto(int saldoIniziale) {
        this.saldo = saldoIniziale;
    }
    public synchronized int getSaldo() {
        return saldo;
    }
    public synchronized boolean  isLowLevel() {

        try {
            while (!lowLevel || tryDeposito != 0)
                wait();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tryDeposito++;
        return true;
    }

    public synchronized void deposita(int quantita) {
            tryDeposito--;
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
