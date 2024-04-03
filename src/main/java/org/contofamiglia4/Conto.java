package org.contofamiglia4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conto {
    private int saldo;
    Lock lock = new ReentrantLock();
    Condition lowLevelCondition = lock.newCondition();
    Condition incrementLevelCondition = lock.newCondition();
    boolean lowLevel = false;

    public Conto(int saldoIniziale) {
        this.saldo = saldoIniziale;
    }
    public int getSaldo() {
        try {
            lock.lock();
            return saldo;
        } finally {
            lock.unlock();
        }
    }
    public boolean  isLowLevel() {

        try {
            lock.lock();
            while(!lowLevel)
                lowLevelCondition.await();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

        return true;
    }

    public void deposita(int quantita) {
        try {
            lock.lock();
            lowLevel = false;
            lowLevelCondition.signal();
            incrementLevelCondition.signal();

            saldo += quantita;
        } finally {
            lock.unlock();
        }
    }

    public void prelava(int quantita) {
        lock.lock();
        while (saldo < quantita) {
            try {
                lowLevel = true;

                lowLevelCondition.signal();

                incrementLevelCondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        lowLevel = false;
        saldo -= quantita;
        lock.unlock();
    }

}
